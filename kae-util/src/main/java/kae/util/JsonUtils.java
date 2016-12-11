/*
 *
 *
 * Kapralov A.
 * 27.01.15
 */

package kae.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author A. Kapralov
 *         27.01.15 21:25
 */
public class JsonUtils {

  public static String getString(JSONObject o, String key) throws JSONException {
    if (o.has(key) && !o.isNull(key)) {
      return o.getString(key);
    } else {
      return null;
    }
  }

  public static int getInt(JSONObject o, String key) throws JSONException {
    if (o.has(key) && !o.isNull(key)) {
      return o.getInt(key);
    } else {
      return 0;
    }
  }

  public static Date getDate(JSONObject o, String key, DateFormat dateFormat) {
    final String property;
    try {
      property = getString(o, key);
      if (property != null && property.length() > 0) {
        return dateFormat.parse(property);
      } else {
        return null;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }
}
