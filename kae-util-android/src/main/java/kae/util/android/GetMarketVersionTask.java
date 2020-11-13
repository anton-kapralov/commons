/*
 *
 *
 * Kapralov A.
 * 29.07.13
 */

package kae.util.android;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

/** @author A. Kapralov 29.07.13 13:07 */
public class GetMarketVersionTask implements CancellableTask {

  private final Context ctx;
  private boolean failed = false;
  private boolean cancelled = false;
  private String failureMessage;
  private String marketVersionName;

  public GetMarketVersionTask(Context ctx) {
    this.ctx = ctx;
  }

  @Override
  public boolean cancel() {
    cancelled = true;
    return true;
  }

  @Override
  public boolean isCancelled() {
    return cancelled;
  }

  @Override
  public String getFailureMessage() {
    return failureMessage;
  }

  @Override
  public boolean hasFailed() {
    return failed;
  }

  public String getMarketVersionName() {
    return marketVersionName;
  }

  @Override
  public void run() {
    try {
      marketVersionName = AndroidUtils.getMarketVersionName(ctx);
    } catch (IOException e) {
      failed = true;
      failureMessage = e.getLocalizedMessage();
    } catch (Exception e) {
      Log.e(GetMarketVersionTask.class.getName(), "Failed!", e);
    }
  }
}
