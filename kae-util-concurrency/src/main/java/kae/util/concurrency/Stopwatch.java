/*
 *
 *
 * Kapralov A.
 * 21.02.15
 */

package kae.util.concurrency;

import net.jcip.annotations.GuardedBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author A. Kapralov
 * 21.02.15 15:38
 */
public class Stopwatch {

  private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

  private final long tickTimeout;

  private final Object lock = new Object();
  @GuardedBy("lock")
  private long elapsed;
  @GuardedBy("lock")
  private long baseTime;
  @GuardedBy("lock")
  private ScheduledFuture<?> scheduledFuture;

  public Stopwatch(long tickTimeout) {
    if (tickTimeout <= 0) {
      throw new IllegalArgumentException("tickTimeout need to be positive value");
    }
    this.tickTimeout = tickTimeout;
  }

  public void start() {
    synchronized (lock) {
      startInternal();
    }
    fireStarted();
  }

  private void startInternal() {
    baseTime = System.currentTimeMillis();
    schedule();
  }

  public void stop() {
    synchronized (lock) {
      stopInternal();
    }
    fireStopped();
  }

  private void stopInternal() {
    cancel();
  }

  public void reset() {
    synchronized (lock) {
      resetInternal();
    }
    fireReset();
  }

  private void resetInternal() {
    cancel();
    elapsed = 0;
  }

  public long getElapsed() {
    synchronized (lock) {
      return getElapsedInternal();
    }
  }

  private long getElapsedInternal() {
    return elapsed;
  }

  private void schedule() {
    scheduledFuture = executorService.scheduleAtFixedRate(this::tick, 0, tickTimeout, TimeUnit.MILLISECONDS);
  }

  private void tick() {
    long elapsedLocal;
    long currentTimeMillis = System.currentTimeMillis();
    synchronized (lock) {
      elapsed += (currentTimeMillis - baseTime);
      baseTime = currentTimeMillis;
      elapsedLocal = elapsed;
    }
    fireTicked(elapsedLocal);
  }

  private void cancel() {
    if (scheduledFuture != null) {
      scheduledFuture.cancel(true);
      scheduledFuture = null;
    }
  }

  private transient final List<StopwatchListener> listeners = new ArrayList<>(8);

  public void addListener(StopwatchListener listener) {
    synchronized (listeners) {
      listeners.add(listener);
    }
  }

  public void removeListener(StopwatchListener listener) {
    synchronized (listeners) {
      listeners.remove(listener);
    }
  }

  private StopwatchListener[] getListenersCopy() {
    StopwatchListener[] listenersCopy;
    synchronized (listeners) {
      listenersCopy = listeners.toArray(new StopwatchListener[0]);
    }
    return listenersCopy;
  }

  private void fireStarted() {
    for (StopwatchListener listener : getListenersCopy()) {
      listener.started();
    }
  }

  private void fireStopped() {
    for (StopwatchListener listener : getListenersCopy()) {
      listener.stopped();
    }
  }

  private void fireReset() {
    for (StopwatchListener listener : getListenersCopy()) {
      listener.reset();
    }
  }

  private void fireTicked(long time) {
    for (StopwatchListener listener : getListenersCopy()) {
      listener.ticked(time);
    }
  }

}
