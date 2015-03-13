/*
 * MathUtils.java
 *
 * Класс MathUtils - набор статических методов для работы с числами.
 *
 *  Kapralov A.
 *  07.11.2008
 */

package kae.util;

/**
 * @author Kapralov A.
 */
public class MathUtils {

  /**
   * Количество байтов в килобайте.
   */
  private static final long BYTES_IN_KB = 1024;
  /**
   * Количество байтов в мегабайте.
   */
  private static final long BYTES_IN_MB = 1048576;

  /**
   * Округляет дробную часть числа.
   *
   * @param _fraction  число для округления.
   * @param _precision точность:
   *                   <ul>
   *                   <li>10   - до десятых </li>
   *                   <li>100  - до сотых </li>
   *                   <li>1000 - до тысячных </li>
   *                   <li>и т. д.</li>
   *                   </ul>
   * @return округленное число.
   */
  public static float roundFraction(float _fraction, int _precision) {
    _fraction *= _precision;
    _fraction -= _fraction % (int) _fraction;
    _fraction /= _precision;
    return _fraction;
  }

  /**
   * Округляет дробную часть числа.
   *
   * @param _fraction  число для округления.
   * @param _precision точность:
   *                   <ul>
   *                   <li>10   - до десятых </li>
   *                   <li>100  - до сотых </li>
   *                   <li>1000 - до тысячных </li>
   *                   <li>и т. д.</li>
   *                   </ul>
   * @return округленное число.
   */
  public static double roundFraction(double _fraction, int _precision) {
    _fraction *= _precision;
    _fraction -= _fraction % (int) _fraction;
    _fraction /= _precision;
    return _fraction;
  }

  /**
   * Преобразовывает количество байтов в строку.
   *
   * @param _dataSize количество байтов.
   * @return cтрока вида "x МБ y КБ" или "x Б".
   */
  public static String getDataSizeString(long _dataSize) {
    String dataSizeString;
    if (_dataSize > BYTES_IN_MB) {
      float dataSize = (float) _dataSize / BYTES_IN_MB;
      dataSizeString = String.valueOf(roundFraction(dataSize, 100)) + " МБ";
    } else if (_dataSize > BYTES_IN_KB) {
      float dataSize = (float) _dataSize / BYTES_IN_KB;
      dataSizeString = String.valueOf(roundFraction(dataSize, 100)) + " КБ";
    } else {
      dataSizeString = String.valueOf(_dataSize) + " Б";
    }
    return dataSizeString;
  }

}
