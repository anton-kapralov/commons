package kae.util;

/**
 * Pair
 *
 * @author Kapralov A.
 * 15.04.2015 16:40
 */
public class Pair<First, Second> {

  public First first;
  public Second second;

  Pair(First first, Second second) {
    this.first = first;
    this.second = second;
  }
}
