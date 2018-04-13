/*
 * RunnableCycle.java
 *
 *
 *  Kapralov A.
 *  11.11.2008
 */

package kae.util.concurrency;

import net.jcip.annotations.GuardedBy;

/**
 * @author Kapralov A.
 */
public abstract class RunnableCycle {

  private final LoopThenIdleRunner loopThenIdleRunner;

  @GuardedBy("this")
  private Thread thread;

  public RunnableCycle() {
    this(0);
  }

  public RunnableCycle(long timeout) {
    loopThenIdleRunner = new LoopThenIdleRunner(timeout);
  }

  public long getTimeout() {
    return loopThenIdleRunner.timeout;
  }

  public synchronized boolean isActive() {
    return thread != null && !thread.isInterrupted();
  }

  public synchronized void start() {
    if (isActive()) {
      return;
    }

    thread = new Thread(loopThenIdleRunner);
    thread.start();
  }

  public synchronized void stop() {
    if (!isActive()) {
      return;
    }

    thread.interrupt();
    thread = null;
  }

  protected abstract void loop();

  private final class LoopThenIdleRunner implements Runnable {

    private final long timeout;

    private LoopThenIdleRunner(long timeout) {
      this.timeout = timeout;
    }

    public void run() {
      while (!Thread.currentThread().isInterrupted()) {
        loop();
        if (timeout > 0) {
          idle();
        }
      }
    }

    private void idle() {
      try {
        Thread.sleep(timeout);
      } catch (InterruptedException e) {
        // restore interrupted status
        Thread.currentThread().interrupt();
      }
    }
  }

}
