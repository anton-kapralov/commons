/*
 *
 *
 * Kapralov A.
 * 27.01.15
 */

package kae.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author A. Kapralov
 * 27.01.15 21:25
 */
public class JsonUtils {

  public static JSONObject nullableJSONObject(JSONObject o, String key) throws JSONException {
    if (o.has(key) && !o.isNull(key)) {
      return o.getJSONObject(key);
    } else {
      return null;
    }
  }

  public static JSONArray nullableJSONArray(JSONObject o, String key) throws JSONException {
    if (o.has(key) && !o.isNull(key)) {
      return o.getJSONArray(key);
    } else {
      return null;
    }
  }

  public static String getString(JSONObject o, String key) throws JSONException {
    if (o.has(key) && !o.isNull(key)) {
      return o.getString(key);
    } else {
      return null;
    }
  }

  public static int getInt(JSONObject o, String key) throws JSONException {
    if (o.has(key) && !o.isNull(key)) {
      Object object = o.get(key);

      try {
        return object instanceof Number ? ((Number) object).intValue() : Integer.parseInt((String) object);
      } catch (NumberFormatException e) {
        return 0;
      }
    } else {
      return 0;
    }
  }

  public static float getFloat(JSONObject o, String key) throws JSONException {
    if (o.has(key) && !o.isNull(key)) {
      Object object = o.get(key);

      try {
        return object instanceof Number ? ((Number) object).floatValue() : Float.parseFloat((String) object);
      } catch (NumberFormatException e) {
        return 0;
      }
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
