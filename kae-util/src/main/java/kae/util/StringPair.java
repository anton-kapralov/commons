/*
 * StringPair.java
 *
 * Класс StringPair - пара строк.
 *
 * Kapralov A.
 * 14.08.2008
 */

package kae.util;

/**
 * Пара строк.
 *
 * @author Kapralov A.
 */
public class StringPair {

  /** Первая строка. */
  public String first;
  /** Вторая строка. */
  public String second;

  /** Конструктор по умолчанию. */
  public StringPair() {
    first = null;
    second = null;
  }

  /**
   * Инициализирующий конструктор.
   *
   * @param _first первая строка.
   * @param _second вторая строка.
   */
  public StringPair(String _first, String _second) {
    first = _first;
    second = _second;
  }
}
