/*
 *
 *
 * Kapralov A.
 * 14.04.13
 */

package kae.util.android;

/**
 * @author A. Kapralov
 * 14.04.13 20:31
 */
public class PhoneNumberUtils {

  public static final int NUMBER_LENGTH = 10;

  // Не будет работать во всех странах.
  public static boolean compare(String number, String anotherNumber) {
    return (number != null && anotherNumber != null) && trim(number).equals(trim(anotherNumber));
  }

  private static String trim(String number) {
    final String digitsOnly = number.replaceAll("[^0-9]", "");
    if (digitsOnly.length() > NUMBER_LENGTH) {
      return digitsOnly.substring(digitsOnly.length() - NUMBER_LENGTH);
    } else {
      return digitsOnly;
    }
  }
}
