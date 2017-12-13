/*
 *
 *
 * Kapralov A.
 * 31.08.12
 */

package kae.util.android;

import android.os.AsyncTask;

/**
 * @author A. Kapralov
 * 31.08.12 14:51
 */
public class AsyncTaskExecutor extends AsyncTask<Void, Void, Void> {

  private final CancellableTask task;

  private final AsyncTaskExecutorListener listener;

  public AsyncTaskExecutor(CancellableTask task) {
    this(task, null);
  }

  public AsyncTaskExecutor(CancellableTask task, AsyncTaskExecutorListener listener) {
    this.task = task;
    this.listener = listener;
  }

  @Override
  protected void onPreExecute() {
    if (listener != null) {
      listener.onPreExecute();
    }
  }

  @Override
  protected Void doInBackground(Void... voids) {
    task.run();
    return null;
  }

  @Override
  protected void onPostExecute(Void aVoid) {
    if (!task.isCancelled() && listener != null) {
      listener.onPostExecute();
    }
  }

  public void cancelTask() {
    cancel(false);
    task.cancel();
  }

  @Override
  protected void onCancelled() {
    super.onCancelled();
    if (listener != null) {
      listener.onCancelled();
    }
  }

}
