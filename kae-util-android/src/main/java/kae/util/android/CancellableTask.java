/*
 *
 *
 * Kapralov A.
 * 28.08.12
 */

package kae.util.android;

/** @author A. Kapralov 28.08.12 20:37 */
public interface CancellableTask extends Runnable {

  boolean cancel();

  boolean isCancelled();

  String getFailureMessage();

  boolean hasFailed();
}
