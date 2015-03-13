/*
 * RunnableCycle.java
 * 
 *
 *  Kapralov A.
 *  11.11.2008
 */

package kae.util;

/**
 * @author Kapralov A.
 */
public abstract class RunnableCycle implements Runnable {

  private final Object lock = new Object();

  private volatile boolean active;

  private long timeout;

  public RunnableCycle() {
    active = false;
    timeout = 0;
  }

  public RunnableCycle(long timeout) {
    this();
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

    new Thread(this).start();
    active = true;
  }

  public void stop() {
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
        // Do nothing.
      }
    }
  }

  protected abstract void iterate();

}
