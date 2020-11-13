/*
 *
 *
 * Kapralov A.
 * 19.09.13
 */

package kae.util.android;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;

/** @author A. Kapralov 19.09.13 18:53 */
public class CyclicNotifier {

  private final Context context;
  private final Uri sound;
  private MediaPlayer mediaPlayer;
  private final Vibrator vibrator;
  private final long[] acceptOrderVibratePattern;

  public CyclicNotifier(Context context, Uri sound, long[] vibratePattern) {
    this.context = context;
    this.sound = sound;

    vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    acceptOrderVibratePattern = vibratePattern;
  }

  public synchronized void start() {
    if (mediaPlayer == null) {
      mediaPlayer = MediaPlayer.create(context, sound);
      if (mediaPlayer != null) {
        mediaPlayer.setLooping(true);
        if (!mediaPlayer.isPlaying()) {
          mediaPlayer.start();
        } else {
          mediaPlayer.seekTo(0);
        }
      }
    }

    if (vibrator != null) {
      vibrator.vibrate(acceptOrderVibratePattern, 0);
    }
  }

  public synchronized void cancel() {
    if (mediaPlayer != null) {
      mediaPlayer.stop();
      mediaPlayer.release();
      mediaPlayer = null;
    }

    if (vibrator != null) {
      vibrator.cancel();
    }
  }
}
