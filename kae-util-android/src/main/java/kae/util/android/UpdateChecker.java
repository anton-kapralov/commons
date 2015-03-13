package kae.util.android;

import java.io.IOException;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class UpdateChecker {

  private static final String TAG = "UpdateChecker";

  private GetMarketVersionTask getMarketVersionTask;

  private final Context ctx;

  public UpdateChecker(Context ctx) {
    this.ctx = ctx;
    getMarketVersionTask = new GetMarketVersionTask(ctx);
  }

  private void onAsyncUpdateCheckerPostExecute() {
    final String marketVersionName = getMarketVersionTask.getMarketVersionName();
    if (marketVersionName != null) {
      checkVersion(ctx, marketVersionName);
    }
  }

  public void asyncCheck() {
    AsyncTaskExecutor asyncUpdateChecker = new AsyncTaskExecutor(getMarketVersionTask,
        new AsyncTaskExecutorListener() {
          @Override
          public void onPreExecute() {
          }

          @Override
          public void onPostExecute() {
            onAsyncUpdateCheckerPostExecute();
          }

          @Override
          public void onCancelled() {
          }
        });
    asyncUpdateChecker.execute();
  }

  public void check() throws IOException {
    String marketVersionName = AndroidUtils.getMarketVersionName(ctx);
    Log.d(TAG, String.format("VersionAvailble %s", marketVersionName));

    if (marketVersionName != null) {
      checkVersion(ctx, marketVersionName);
    }
  }

  private void checkVersion(Context ctx, String marketVersionName) {
    String existingVersionName = AndroidUtils.getApplicationVersion(ctx);

    Log.d(TAG, String.format("VersionMarket %s", marketVersionName));
    Log.d(TAG, String.format("VersionActual %s", existingVersionName));
    if (!marketVersionName.equals(existingVersionName)) {
      askForUpdate(ctx, marketVersionName, existingVersionName);
    }
  }

  private void askForUpdate(final Context ctx, String marketVersionName,
      String existingVersionName) {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
    alertDialog.setTitle("Доступно обновление");
    alertDialog.setMessage(String.format(
        "Вы используете версию: %s\n" +
            "Доступна версия: %s\n" +
            "Перейти в Google Play, чтобы обновить?",
        existingVersionName,
        marketVersionName));
    alertDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
        String appID = ctx.getPackageName();
        try {
          ctx.startActivity(
              new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appID)));
        } catch (android.content.ActivityNotFoundException anfe) {
          ctx.startActivity(new Intent(Intent.ACTION_VIEW,
              Uri.parse("http://play.google.com/store/apps/details?id" + appID)));
        }
      }
    });
    alertDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
      }
    });
    alertDialog.show();
  }

}
