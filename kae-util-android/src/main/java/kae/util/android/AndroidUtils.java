/*
 *
 *
 * Kapralov A.
 * 18.04.13
 */

package kae.util.android;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.BatteryManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TabHost;
import kae.util.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @author A. Kapralov 18.04.13 10:56 */
public class AndroidUtils {

  public static final String TAG = "kae.util.AndroidUtils";

  public static DeviceId getDeviceId(Context context) {
    TelephonyManager telephonyManager =
        (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    if (telephonyManager != null) {
      final String deviceId = telephonyManager.getDeviceId();
      if (deviceId != null && !deviceId.isEmpty()) {
        return new DeviceId(DeviceIdType.IMEI, deviceId);
      }

      final String subscriberId = telephonyManager.getSubscriberId();
      if (subscriberId != null && !subscriberId.isEmpty()) {
        return new DeviceId(DeviceIdType.IMSI, subscriberId);
      }
    }

    String androidId =
        Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    if (androidId != null && !androidId.isEmpty()) {
      return new DeviceId(DeviceIdType.ANDROID_ID, androidId);
    }

    return new DeviceId(DeviceIdType.UUID, UUID.randomUUID().toString());
  }

  public static String getImei(Context context) {
    TelephonyManager telephonyManager =
        (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    return telephonyManager != null ? telephonyManager.getDeviceId() : "NO IMEI";
  }

  public static String getImsi(Context context) {
    TelephonyManager telephonyManager =
        (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    return telephonyManager != null ? telephonyManager.getSubscriberId() : "NO IMSI";
  }

  public static String getStringPreference(Context context, String preferenceName, String key) {
    SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, 0);
    return sharedPreferences.getString(key, null);
  }

  public static long getLongPreference(Context context, String preferenceName, String key) {
    SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, 0);
    return sharedPreferences.getLong(key, 0);
  }

  public static boolean getBooleanPreference(Context context, String preferenceName, String key) {
    SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, 0);
    return sharedPreferences.getBoolean(key, false);
  }

  public static void savePreference(
      Context context, String preferenceName, String key, boolean value) {
    SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, 0);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putBoolean(key, value);
    editor.commit();
  }

  public static void savePreference(
      Context context, String preferenceName, String key, long value) {
    SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, 0);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putLong(key, value);
    editor.commit();
  }

  public static void savePreference(Context context, String preferenceName, String key, int value) {
    SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, 0);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putInt(key, value);
    editor.commit();
  }

  public static void savePreference(
      Context context, String preferenceName, String key, String value) {
    SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, 0);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(key, value);
    editor.commit();
  }

  public static void showNotification(
      Context context,
      int id,
      int icon,
      String text,
      PendingIntent contentIntent,
      String contentTitle,
      String contentText,
      int flags) {
    showNotification(
        context, id, icon, text, contentIntent, contentTitle, contentText, flags, null);
  }

  public static void showNotification(
      Context context,
      int id,
      int icon,
      String text,
      PendingIntent contentIntent,
      String contentTitle,
      String contentText,
      int flags,
      Uri soundUri) {
    Notification notification = new Notification(icon, text, System.currentTimeMillis());
    notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);

    if (flags != 0) {
      notification.flags = flags;
    }

    if (soundUri != null) {
      notification.sound = soundUri;
    }

    showNotification(context, id, notification);
  }

  public static void showNotification(Context context, int id, Notification notification) {
    NotificationManager notificationManager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(id, notification);
  }

  public static void showNotification(
      Context context, String tag, int id, Notification notification) {
    NotificationManager notificationManager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(tag, id, notification);
  }

  public static void cancelNotification(Context context, int notificationId) {
    NotificationManager mNotificationManager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    mNotificationManager.cancel(notificationId);
  }

  public static void cancelNotification(Context context, String tag, int notificationId) {
    NotificationManager mNotificationManager =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    mNotificationManager.cancel(tag, notificationId);
  }

  public static BatteryInfo getBatteryInfo(Context context) {
    IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    Intent batteryStatus = context.registerReceiver(null, ifilter);
    int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
    boolean isCharging =
        status == BatteryManager.BATTERY_STATUS_CHARGING
            || status == BatteryManager.BATTERY_STATUS_FULL;

    int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
    int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

    float batteryPct = (level / (float) scale) * 100 /* % */;

    return new BatteryInfo((int) batteryPct, isCharging);
  }

  public static String getMarketVersionName(final Context ctx) throws IOException {
    final String appID = ctx.getPackageName();
    final String url = "https://play.google.com/store/apps/details?id=" + appID;
    Log.d(TAG, String.format("URL: %s", url));
    String html = RestUtils.getString(url, "UTF-8");
    Pattern pattern = Pattern.compile("softwareVersion\">[^<]*</");
    Matcher matcher = pattern.matcher(html);
    if (matcher.find()) {
      final String group = matcher.group(0);
      return group.substring(group.indexOf(">") + 1, group.indexOf("<")).trim();
    } else {
      return null;
    }
  }

  public static String getApplicationVersion(Context ctx) {
    try {
      return ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionName;
    } catch (PackageManager.NameNotFoundException e) {
      Log.d(TAG, "", e);
      return null;
    }
  }

  public static void copyAssetFileToInternalStorage(Context context, String fileName)
      throws IOException {
    AssetManager assetManager = context.getAssets();
    InputStream in = null;
    OutputStream out = null;
    File file = new File(context.getFilesDir(), fileName);
    try {
      in = assetManager.open(fileName);
      out = context.openFileOutput(file.getName(), Context.MODE_WORLD_READABLE);

      IOUtils.transfuse(in, out);
      out.flush();
    } finally {
      if (in != null) {
        in.close();
      }
      if (out != null) {
        out.close();
      }
    }
  }

  public static String getAssetFileContent(Context context, String fileName, String encoding)
      throws IOException {
    AssetManager assetManager = context.getAssets();
    InputStream in = null;
    try {
      in = assetManager.open(fileName);

      return IOUtils.readStream(in, encoding);
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }

  public static Drawable resizeDrawable(Context context, int resId, int width, int height) {
    return resizeDrawable(context, context.getResources().getDrawable(resId), width, height);
  }

  public static Drawable resizeDrawable(Context context, Drawable origin, int width, int height) {
    Bitmap bitmap = ((BitmapDrawable) origin).getBitmap();
    return new BitmapDrawable(
        context.getResources(), Bitmap.createScaledBitmap(bitmap, width, height, true));
  }

  public static TabHost.TabSpec newTab(
      TabHost tabHost, String tag, String label, int tabContentId) {
    TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
    tabSpec.setIndicator(label);
    tabSpec.setContent(tabContentId);
    return tabSpec;
  }

  public static TabHost.TabSpec newTab(
      TabHost tabHost, String tag, String label, Drawable icon, int tabContentId) {
    TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
    if (icon != null) {
      tabSpec.setIndicator(label, icon);
    } else {
      tabSpec.setIndicator(label);
    }
    tabSpec.setContent(tabContentId);
    return tabSpec;
  }
}
