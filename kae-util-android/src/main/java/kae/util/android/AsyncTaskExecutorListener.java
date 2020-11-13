/*
 *
 *
 * Kapralov A.
 * 31.08.12
 */

package kae.util.android;

/** @author A. Kapralov 31.08.12 15:08 */
public interface AsyncTaskExecutorListener {

  void onPreExecute();

  void onPostExecute();

  void onCancelled();
}
