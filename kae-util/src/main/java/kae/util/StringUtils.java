/*
 * StringUtils.java
 *
 * A. Kapralov
 * 04.11.2009 18:45:26
 */

package kae.util;

/** @author A. Kapralov */
public class StringUtils {

  public static boolean isEmpty(String string) {
    return string == null || string.length() == 0;
  }

  public static boolean isNotEmpty(String string) {
    return string != null && string.length() != 0;
  }

  public static String[] split(String source, char splitter) {
    int wordCount = 0;
    int wordLength = 0;

    for (int i = 0; i < source.length(); ++i) {
      if (source.charAt(i) == splitter && wordLength > 0) {
        wordCount++;
        wordLength = 0;
      } else {
        wordLength++;
      }
    }

    if (wordLength > 0) {
      wordCount++;
    }

    String[] result = new String[wordCount];
    int wordIdx = 0;
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < source.length(); ++i) {
      if (source.charAt(i) == splitter && buf.length() > 0) {
        result[wordIdx++] = buf.toString();
        buf = new StringBuffer();
      } else {
        buf.append(source.charAt(i));
      }
    }
    if (buf.length() > 0) {
      result[wordIdx] = buf.toString();
    }

    return result;
  }
}
