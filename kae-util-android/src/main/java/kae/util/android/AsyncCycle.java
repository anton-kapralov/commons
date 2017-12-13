/*
 *
 *
 * Kapralov A.
 * 03.05.13
 */

package kae.util.android;

/**
 * @author A. Kapralov
 * 03.05.13 20:43
 */
public abstract class AsyncCycle {

  private final Object lock = new Object();

  private Thread asyncTask = null;

  private volatile boolean active = false;

  private volatile long timeout;

  private final boolean waitBeforeIteration;

  public AsyncCycle() {
    this(0);
  }

  protected AsyncCycle(long timeout) {
    this(timeout, false);
  }

  protected AsyncCycle(long timeout, boolean waitBeforeIteration) {
    this.timeout = timeout;
    this.waitBeforeIteration = waitBeforeIteration;
  }

  public boolean isActive() {
    return active;
  }

  public void setTimeout(long timeout) {
    this.timeout = timeout;
  }

  public void start() {
    if (active) {
      stop();
    }

    active = true;
    if (waitBeforeIteration) {
      asyncTask = new PostponedAsyncTaskImpl();
    } else {
      asyncTask = new AsyncTaskImpl();
    }
    asyncTask.start();
  }

  public void stop() {
    if (!active) {
      return;
    }

    active = false;
    synchronized (lock) {
      lock.notify();
    }
    asyncTask = null;
  }

  protected abstract void iterate();

  private class AsyncTaskImpl extends Thread {

    @Override
    public void run() {
      while (active) {
        iterate();
        if (timeout > 0) {
          synchronized (lock) {
            try {
              lock.wait(timeout);
            } catch (InterruptedException e) {
              // Do nothing.
            }
          }
        }
      }
    }

  }

  private class PostponedAsyncTaskImpl extends Thread {

    @Override
    public void run() {
      while (active) {
        if (timeout > 0) {
          synchronized (lock) {
            try {
              lock.wait(timeout);
            } catch (InterruptedException e) {
              // Do nothing.
            }
          }
        }

        if (active) {
          iterate();
        }
      }
    }

  }
}
