/*
 * 
 * 
 * Kapralov A.
 * 04.08.11
 */

package kae.util;

import java.util.Collection;

/**
 * @author A. Kapralov 04.08.11 22:56
 */
public class ObjectUtils {

  public static boolean equals(Object o1, Object o2) {
    return o1 == o2 || o1 != null && o2 != null && o1.equals(o2);
  }

  public static boolean equals(float f1, float f2, float precision) {
    return Math.abs(f1 - f2) < precision;
  }

  public static int toPrimitiveInteger(Integer src, int defaultValue) {
    return src != null ? src : defaultValue;
  }

  public static int toPrimitiveInteger(Integer src) {
    return toPrimitiveInteger(src, 0);
  }

  public static float toPrimitiveFloat(Float src, float defaultValue) {
    return src != null ? src : defaultValue;
  }

  public static float toPrimitiveFloat(Float src) {
    return toPrimitiveFloat(src, 0f);
  }

  public static boolean toPrimitiveBoolean(Boolean src, boolean defaultValue) {
    return src != null ? src : defaultValue;
  }

  public static boolean toPrimitiveBoolean(Boolean src) {
    return toPrimitiveBoolean(src, false);
  }

  public static int[] toPrimitiveIntegerArray(Collection<Integer> integerCollection) {
    final int[] integerArray;
    if (integerCollection != null) {
      integerArray = new int[integerCollection.size()];
      int i = 0;
      for (Integer integer : integerCollection) {
        integerArray[i++] = integer;
      }

    } else {
      integerArray = new int[0];
    }

    return integerArray;
  }


  public static String nullSafe(String s) {
    return s != null ? s : "";
  }

}
