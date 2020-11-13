package kae.util.android;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class ResourceUtils {

  public static Drawable getDrawableByName(Context context, String name) {
    int drawableResource = getDrawableIdByName(context, name);
    return context.getResources().getDrawable(drawableResource);
  }

  public static int getDrawableIdByName(Context context, String name) {
    int drawableResource =
        context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    if (drawableResource == 0) {
      throw new RuntimeException("Can't find drawable with name: " + name);
    }
    return drawableResource;
  }
}
