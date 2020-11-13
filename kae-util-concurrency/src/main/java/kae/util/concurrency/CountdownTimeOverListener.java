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

public interface CountdownTimeOverListener {

  void timeOver(final CountdownTimer timer);
}
