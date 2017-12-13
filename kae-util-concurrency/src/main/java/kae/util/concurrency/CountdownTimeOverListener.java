/*
 * CountdownTimeOverListener.java
 *
 * Интерфейс CountdownTimeOverListener - слушатель окончания
 * таймера обратного отсчета.
 *
 *  Kapralov A.
 *  06.11.2008
 */

package kae.util.concurrency;

/**
 * Слушатель окончания таймера обратного отсчета.
 *
 * @author Kapralov A.
 */
public interface CountdownTimeOverListener {

  /**
   * "Время вышло"
   *
   * @param _source таймер обратного отсчета, на котором сработало
   *                это событие.
   */
  void timeOver(final CountdownTimer _source);

}
