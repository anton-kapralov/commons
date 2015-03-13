/*
 * 
 * 
 * Kapralov A.
 * 03.05.13
 */

package kae.util.android;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kae.util.StreamUtils;

/**
 * @author A. Kapralov
 *         03.05.13 19:17
 */
public class RestUtils {

  public static JSONObject getJSON(String url) throws IOException, JSONException {
    return getJSON(url, null);
  }

  public static JSONObject getJSON(String url, String encoding) throws IOException, JSONException {
    final String string = getString(url, encoding);
    if (string != null) {
      return new JSONObject(string);
    } else {
      return null;
    }
  }

  public static JSONArray getJSONArray(String url) throws IOException, JSONException {
    return getJSONArray(url, null);
  }

  public static JSONArray getJSONArray(String url, String encoding) throws IOException, JSONException {
    final String string = getString(url, encoding);
    if (string != null) {
      return new JSONArray(string);
    } else {
      return null;
    }
  }

  public static void getVoid(String url) throws IOException {
    HttpClient httpclient = new DefaultHttpClient();
    httpclient.execute(new HttpGet(url));
  }

  public static String getString(String url) throws IOException {
    return getString(url, null);
  }

  public static String getString(String url, String encoding) throws IOException {
    String result = null;
    HttpClient httpclient = new DefaultHttpClient();
    // Prepare a request object
    HttpGet httpget = new HttpGet(url);
    // Execute the request
    HttpResponse response;
    response = httpclient.execute(httpget);
    // Get the response entity
    HttpEntity entity = response.getEntity();
    // If response entity is not null
    if (entity != null) {
      InputStream in = null;
      try {
        // get entity contents and convert it to string
        in = entity.getContent();
        if (encoding != null) {
          result = StreamUtils.readStream(in, encoding);
        } else {
          result = StreamUtils.readStream(in);
        }
        // Closing the input stream will trigger connection release
      } finally {
        if (in != null) {
          in.close();
        }
      }
    }
    // Return the result
    return result;
  }

  public static long getLong(String url) throws IOException {
    final String res = getString(url);
    try {
      return Long.parseLong(res);
    } catch (NumberFormatException e) {
      throw new IOException(String.format("Unable to get 'long' value from %s (got %s)", url, res));
    }
  }

  public static void post(String url) throws IOException {
    HttpClient httpclient = new DefaultHttpClient();
    httpclient.execute(new HttpPost(url));
  }

}
