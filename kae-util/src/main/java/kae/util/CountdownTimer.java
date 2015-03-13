/*
 * CountdownTimer.java
 * 
 * Класс CountdownTimer - таймер обратного отсчета.
 *
 *  Kapralov A.
 *  06.11.2008
 */

package kae.util;

import java.util.LinkedList;
import java.util.List;

/**
 * Таймер обратного отсчета.
 *
 * @author Kapralov A.
 */
public class CountdownTimer extends RunnableCycle {

  /**
   * Таймаут по умолчанию.
   */
  private static final long DEFAULT_TIME_OUT = 100;

  /**
   * Оставшееся время.
   */
  private long timeRemaining;

  /**
   * Слушатель тиков.
   */
  private transient final List<CountdownTickedListener> countdownTickedListeners =
      new LinkedList<CountdownTickedListener>();
  /**
   * Слушатель окончания таймера.
   */
  private transient final List<CountdownTimeOverListener> countdownTimeOverListeners =
      new LinkedList<CountdownTimeOverListener>();

  /**
   * Конструктор по умолчанию.
   */
  public CountdownTimer() {
    this(DEFAULT_TIME_OUT);
  }

  /**
   * Инициализирующий конструктор.
   *
   * @param _timeOut таймаут.
   */
  public CountdownTimer(long _timeOut) {
    super(_timeOut);
  }

  public CountdownTimer(long timeRemaining, long timeout) {
    super(timeout);
    this.timeRemaining = timeRemaining;
  }

  /**
   * Возвращает оставшееся время.
   *
   * @return оставшееся время.
   */
  public long getTimeRemaining() {
    return timeRemaining;
  }

  /**
   * Устанавливает оставшееся время.
   *
   * @param _timeRemaining оставшееся время.
   */
  public void setTimeRemaining(long _timeRemaining) {
    timeRemaining = _timeRemaining;
  }

  protected void iterate() {
    if (timeRemaining <= 0) {
      stop();
      fireTimeOver();
    } else {
      timeRemaining -= getTimeout();
      fireTicked();
    }
  }

  //<editor-fold defaultstate="collapsed" desc=" Поддержка события 'Счетчик сработал' ">

  /**
   * Registers CountdownTickedListener to receive events.
   *
   * @param _listener The listener to register.
   */
  public void addCountdownTickedListener(CountdownTickedListener _listener) {
    countdownTickedListeners.add(_listener);
  }

  /**
   * Removes CountdownTickedListener from the list of listeners.
   *
   * @param _listener The listener to remove.
   */
  public void removeCountdownTickedListener(CountdownTickedListener _listener) {
    countdownTickedListeners.remove(_listener);
  }

  /**
   * Notifies all registered listeners about the event.
   */
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

  //</editor-fold>

  //<editor-fold defaultstate="collapsed" desc=" Поддержка события 'Счетчик завершился' ">

  /**
   * Registers CountdownTimeOverListener to receive events.
   *
   * @param _listener The listener to register.
   */
  public void addCountdownTimeOverListener(CountdownTimeOverListener _listener) {
    countdownTimeOverListeners.add(_listener);
  }

  /**
   * Removes CountdownTimeOverListener from the list of listeners.
   *
   * @param _listener The listener to remove.
   */
  public void removeCountdownTimeOverListener(CountdownTimeOverListener _listener) {
    countdownTimeOverListeners.remove(_listener);
  }

  /**
   * Notifies all registered listeners about the event.
   */
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

  //</editor-fold>

}
