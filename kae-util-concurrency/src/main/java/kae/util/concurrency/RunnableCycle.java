/*
 * RunnableCycle.java
 *
 *
 *  Kapralov A.
 *  11.11.2008
 */

package kae.util.concurrency;

import net.jcip.annotations.NotThreadSafe;

/**
 * @author Kapralov A.
 */
@NotThreadSafe
public abstract class RunnableCycle implements Runnable {

  private final Object lock = new Object();

  private volatile boolean active = false;

  private volatile long timeout;

  public RunnableCycle() {}

  public RunnableCycle(long timeout) {
    this.timeout = timeout;
  }

  public boolean isActive() {
    return active;
  }

  /**
   * Возвращает таймаут.
   *
   * @return таймаут.
   */
  public long getTimeout() {
    return timeout;
  }

  public void setTimeout(long timeout) {
    this.timeout = timeout;
  }

  public void start() {
    if (active) {
      return;
    }

    active = true;
    new Thread(this).start();
  }

  public void stop() {
    if (!active) {
      return;
    }

    active = false;
    synchronized (lock) {
      lock.notify();
    }
  }

  public void run() {
    while (active) {
      iterate();
      if (timeout > 0) {
        idle();
      }
    }
  }

  private void idle() {
    synchronized (lock) {
      try {
        lock.wait(timeout);
      } catch (InterruptedException e) {
        // restore interrupted status
        Thread.currentThread().interrupt();
      }
    }
  }

  protected abstract void iterate();

}
