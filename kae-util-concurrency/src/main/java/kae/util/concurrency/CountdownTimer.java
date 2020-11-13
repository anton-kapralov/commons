/*
 * CountdownTimer.java
 *
 * Класс CountdownTimer - таймер обратного отсчета.
 *
 *  Kapralov A.
 *  06.11.2008
 */

package kae.util.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Countdown timer.
 *
 * @author Kapralov A.
 */
public class CountdownTimer extends RunnableCycle {

  private static final long DEFAULT_TIME_OUT = 100;

  private final AtomicLong timeRemaining;

  public CountdownTimer() {
    this(DEFAULT_TIME_OUT);
  }

  public CountdownTimer(long timeOut) {
    this(0, timeOut);
  }

  public CountdownTimer(long timeRemaining, long timeout) {
    super(timeout);
    this.timeRemaining = new AtomicLong(timeRemaining);
  }

  public long getTimeRemaining() {
    return timeRemaining.get();
  }

  public void setTimeRemaining(long timeRemaining) {
    this.timeRemaining.set(timeRemaining);
  }

  protected void loop() {
    long timeRemainingLocal;
    do {
      timeRemainingLocal = timeRemaining.get();
      if (timeRemainingLocal <= 0) {
        stop();
        fireTimeOver();
        return;
      }
    } while (!this.timeRemaining.compareAndSet(
        timeRemainingLocal, timeRemainingLocal - getTimeout()));

    fireTicked();
  }

  // <editor-fold defaultstate="collapsed" desc=" Event 'Countdown ticked' ">

  private final transient List<CountdownTickedListener> countdownTickedListeners =
      new ArrayList<>(8);

  /**
   * Registers CountdownTickedListener to receive events.
   *
   * @param listener The listener to register.
   */
  public void addCountdownTickedListener(CountdownTickedListener listener) {
    synchronized (countdownTickedListeners) {
      countdownTickedListeners.add(listener);
    }
  }

  /**
   * Removes CountdownTickedListener from the list of listeners.
   *
   * @param listener The listener to remove.
   */
  public void removeCountdownTickedListener(CountdownTickedListener listener) {
    synchronized (countdownTickedListeners) {
      countdownTickedListeners.remove(listener);
    }
  }

  /** Notifies all registered listeners about the event. */
  private void fireTicked() {
    CountdownTickedListener[] listenersCopy;
    synchronized (countdownTickedListeners) {
      listenersCopy = new CountdownTickedListener[countdownTickedListeners.size()];
      countdownTickedListeners.toArray(listenersCopy);
    }

    for (CountdownTickedListener listener : listenersCopy) {
      listener.ticked(this);
    }
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc=" Event 'Countdown time is over' ">

  private final transient List<CountdownTimeOverListener> countdownTimeOverListeners =
      new ArrayList<>(8);

  /**
   * Registers CountdownTimeOverListener to receive events.
   *
   * @param listener The listener to register.
   */
  public void addCountdownTimeOverListener(CountdownTimeOverListener listener) {
    synchronized (countdownTimeOverListeners) {
      countdownTimeOverListeners.add(listener);
    }
  }

  /**
   * Removes CountdownTimeOverListener from the list of listeners.
   *
   * @param listener The listener to remove.
   */
  public void removeCountdownTimeOverListener(CountdownTimeOverListener listener) {
    synchronized (countdownTimeOverListeners) {
      countdownTimeOverListeners.remove(listener);
    }
  }

  /** Notifies all registered listeners about the event. */
  private void fireTimeOver() {
    CountdownTimeOverListener[] listenersCopy;
    synchronized (countdownTimeOverListeners) {
      listenersCopy = new CountdownTimeOverListener[countdownTimeOverListeners.size()];
      countdownTimeOverListeners.toArray(listenersCopy);
    }

    for (CountdownTimeOverListener listener : listenersCopy) {
      listener.timeOver(this);
    }
  }

  // </editor-fold>

}
