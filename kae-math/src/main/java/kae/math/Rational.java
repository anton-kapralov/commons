package kae.math;

import static java.lang.Math.abs;
import static kae.math.MoreMath.gcd;
import static kae.math.MoreMath.lcm;

/**
 * Represents a number that can be expressed as the quotient or fraction p/q of two integers, a
 * numerator p and a non-zero denominator q.
 *
 * <p>Immutable.
 *
 * @author Anton Kapralov.
 */
public final class Rational implements Comparable<Rational> {

  public static final Rational ZERO = valueOf(0);
  public static final Rational ONE = valueOf(1);

  private final long p;
  private final long q;

  public Rational(long p, long q) {
    if (q == 0) {
      throw new IllegalArgumentException("Denominator can't be 0.");
    }

    if (q < 0) {
      q = -q;
      p = -p;
    }

    if (p == 0) {
      this.p = 0;
      this.q = 1;
      return;
    }

    long gcd = gcd(abs(p), q);
    this.p = p / gcd;
    this.q = q / gcd;
  }

  public long nominator() {
    return p;
  }

  public long denominator() {
    return q;
  }

  public Rational negate() {
    return new Rational(-p, q);
  }

  public Rational reciprocal() {
    if (p == 0) {
      return ZERO;
    }

    return new Rational(p > 0 ? q : -q, abs(p));
  }

  public Rational add(Rational val) {
    long nq = lcm(q, val.q);
    long np = p * (nq / q) + val.p * (nq / val.q);
    return new Rational(np, nq);
  }

  public Rational subtract(Rational val) {
    long nq = lcm(q, val.q);
    long np = p * (nq / q) - val.p * (nq / val.q);
    return new Rational(np, nq);
  }

  public Rational multiply(Rational val) {
    return new Rational(p * val.p, q * val.q);
  }

  public Rational divide(Rational val) {
    return new Rational(p * val.q, q * val.p);
  }

  @Override
  public int compareTo(Rational val) {
    long nq = lcm(q, val.q);
    return Long.compare(p * (nq / q), val.p * (nq / val.q));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Rational rational = (Rational) o;

    if (p != rational.p) return false;
    return q == rational.q;
  }

  @Override
  public int hashCode() {
    int result = (int) (p ^ (p >>> 32));
    result = 31 * result + (int) (q ^ (q >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return q == 1 ? String.valueOf(p) : (p + "/" + q);
  }

  public static Rational valueOf(long n) {
    return new Rational(n, 1);
  }
}
