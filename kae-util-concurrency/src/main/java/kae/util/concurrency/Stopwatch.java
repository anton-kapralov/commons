/*
 *
 *
 * Kapralov A.
 * 21.02.15
 */

package kae.util.concurrency;

import net.jcip.annotations.NotThreadSafe;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author A. Kapralov
 * 21.02.15 15:38
 */
@NotThreadSafe
public class Stopwatch {

  private final ExecutorService executorService = Executors.newSingleThreadExecutor();

  private final Object lock = new Object();

  private volatile long time;

  private volatile long elapsedTime;
  private volatile long anchorTime;

  private volatile boolean active;

  private final long timeout;

  public Stopwatch(long tickTimeout) {
    if (tickTimeout <= 0) {
      throw new IllegalArgumentException("tickTimeout need to be positive value");
    }
    timeout = tickTimeout;
    reset();

    run();
  }

  public boolean isActive() {
    return active;
  }

  public void start() {
    active = true;
    synchronized (this) {
      anchorTime = System.currentTimeMillis();
    }
    run();
    fireStarted();
  }

  public void stop() {
    active = false;
    await();
    synchronized (this) {
      elapsedTime = time;
    }
    fireStopped();
  }

  public void reset() {
    active = false;
    await();
    synchronized (this) {
      time = 0;
      elapsedTime = 0;
    }
    fireReset();
  }

  private void execute() {
    while (active) {
      idle();
      synchronized (this) {
        time = elapsedTime + (System.currentTimeMillis() - anchorTime);
      }
      fireTicked(time);
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

  public void close() {
    if (active) {
      stop();
    }

    synchronized (lock) {
      lock.notify();
    }

    await();
  }

  private void run() {
    executorService.execute(new Runnable() {
      @Override
      public void run() {
        execute();
      }
    });
  }

  private void await() {
    try {
      executorService.awaitTermination(10 * timeout, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private transient List<StopwatchListener> listeners =
      new LinkedList<StopwatchListener>();

  public void addListener(StopwatchListener listener) {
    listeners.add(listener);
  }

  public void removeListener(StopwatchListener listener) {
    listeners.remove(listener);
  }

  private void fireStarted() {
    for (StopwatchListener listener : listeners) {
      listener.started();
    }
  }

  private void fireStopped() {
    for (StopwatchListener listener : listeners) {
      listener.stopped();
    }
  }

  private void fireReset() {
    for (StopwatchListener listener : listeners) {
      listener.reset();
    }
  }

  private void fireTicked(long time) {
    for (StopwatchListener listener : listeners) {
      listener.ticked(time);
    }
  }

}
