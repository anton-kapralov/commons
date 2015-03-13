/*
 * CountdownTickedListener.java
 * 
 * Интерфейс CountdownTickedListener - слушатель "тиков" таймера
 * обратного отсчета.
 *
 *  Kapralov A.
 *  06.11.2008
 */

package kae.util;

/**
 * Cлушатель "тиков" таймера обратного отсчета.
 *
 * @author Kapralov A.
 */
public interface CountdownTickedListener {

  /**
   * "Тик".
   *
   * @param _source таймер обратного отсчета, на котором сработало
   *                это событие.
   */
  void ticked(final CountdownTimer _source);

}
