/*
 * CountdownTickedListener.java
 *
 * Интерфейс CountdownTickedListener - слушатель "тиков" таймера
 * обратного отсчета.
 *
 *  Kapralov A.
 *  06.11.2008
 */

package kae.util.concurrency;

public interface CountdownTickedListener {

  void ticked(final CountdownTimer timer);

}
