/*
 *
 *
 * Kapralov A.
 * 31.08.12
 */

package kae.util.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import kae.util.StringUtils;

/** @author A. Kapralov 31.08.12 17:06 */
public class UiUtils {

  public static AlertDialog showAlertDialog(
      Context context, int titleId, int iconId, String message) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle(titleId);
    builder.setIcon(iconId);
    builder.setMessage(message);

    AlertDialog alert = builder.create();
    alert.show();

    return alert;
  }

  public static AlertDialog showYesNoDialog(
      Context context,
      int titleId,
      int iconId,
      String message,
      DialogInterface.OnClickListener yesListener,
      DialogInterface.OnClickListener noListener) {
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setTitle(titleId);
    builder.setIcon(iconId);
    builder.setMessage(message);
    builder.setPositiveButton("Да", yesListener);
    builder.setNegativeButton("Нет", noListener);

    AlertDialog alert = builder.create();
    alert.show();

    return alert;
  }

  public static float getFloatFromEditText(Activity activity, int id) {
    EditText editText = ((EditText) activity.findViewById(id));
    String stringValue = editText.getEditableText().toString();
    if (StringUtils.isNotEmpty(stringValue)) {
      return Float.valueOf(stringValue);
    } else {
      return 0;
    }
  }

  public static int getIntFromEditText(Activity activity, int id) {
    EditText editText = ((EditText) activity.findViewById(id));
    return getIntFromEditText(editText);
  }

  public static int getIntFromEditText(EditText editText) {
    String stringValue = editText.getEditableText().toString();
    if (StringUtils.isNotEmpty(stringValue)) {
      return Integer.valueOf(stringValue);
    } else {
      return 0;
    }
  }
}
