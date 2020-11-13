/*
 *
 *
 * Kapralov A.
 * 21.02.15
 */

package kae.util.concurrency;

/** @author A. Kapralov 21.02.15 15:39 */
public interface StopwatchListener {

  void started();

  void stopped();

  void reset();

  void ticked(long time);
}
