package kae.math;

/** More math functions. */
public final class MoreMath {

  /** Calculates the greatest common divisor of given {@code a} and {@code b}. */
  public static long gcd(long a, long b) {
    return b == 0 ? a : gcd(b, a % b);
  }

  /** Calculates the lowest common multiple of given {@code a} and {@code b}. */
  public static long lcm(long a, long b) {
    return a * b / gcd(a, b);
  }

  private MoreMath() {}
}
