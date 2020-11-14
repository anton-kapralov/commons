package kae.math;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static com.google.common.truth.Truth.assertThat;
import static kae.math.Rational.ZERO;
import static org.junit.Assert.assertThrows;

/** */
@RunWith(Theories.class)
public class RationalTest {

  @Test
  public void constructor_throwsIllegalArgumentException_whenDenominatorIsNull() {
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new Rational(0, 0));

    assertThat(exception).hasMessageThat().isEqualTo("Denominator can't be 0.");
  }

  @Test
  public void constructor_negatesNominator_whenDenominatorIsBelow0() {
    Rational rational = new Rational(1, -2);

    assertThat(rational.nominator()).isEqualTo(-1);
    assertThat(rational.denominator()).isEqualTo(2);
  }

  @Test
  public void constructor_makesDenominatorEqual1_whenNominatorIs0() {
    Rational rational = new Rational(0, 4);

    assertThat(rational.denominator()).isEqualTo(1);
  }

  @Test
  public void constructor_reducesFraction() {
    Rational rational = new Rational(2, 4);

    assertThat(rational.nominator()).isEqualTo(1);
    assertThat(rational.denominator()).isEqualTo(2);
  }

  @Test
  public void constructor_reducesFraction_whenNegative() {
    Rational rational = new Rational(-2, 4);

    assertThat(rational.nominator()).isEqualTo(-1);
    assertThat(rational.denominator()).isEqualTo(2);
  }

  @Test
  public void negate_returnsNegative_whenPositive() {
    Rational negated = new Rational(1, 2).negate();

    assertThat(negated).isLessThan(ZERO);
  }

  @Test
  public void reciprocal_returnsZero_whenZero() {
    Rational reciprocal = ZERO.reciprocal();

    assertThat(reciprocal).isEqualTo(ZERO);
  }

  @Test
  public void reciprocal_returnsFlippedNominatorAndDenominator() {
    Rational reciprocal = new Rational(1, 2).reciprocal();

    assertThat(reciprocal.nominator()).isEqualTo(2);
    assertThat(reciprocal.denominator()).isEqualTo(1);
  }

  @DataPoints("sum")
  public static final ImmutableList<Rational[]> valuesForSum =
      ImmutableList.of(
          new Rational[] {new Rational(1, 12), new Rational(1, 18), new Rational(5, 36)},
          new Rational[] {new Rational(1, 2), new Rational(1, 2), new Rational(1, 1)},
          new Rational[] {new Rational(1, 6), new Rational(1, 3), new Rational(1, 2)},
          new Rational[] {new Rational(1, 2), new Rational(1, 3), new Rational(5, 6)});

  @Theory
  public void add_calculatesCorrectly(@FromDataPoints("sum") Rational[] values) {
    assertThat(values[0].add(values[1])).isEqualTo(values[2]);
  }

  @DataPoints("subtraction")
  public static final ImmutableList<Rational[]> valuesForSubtraction =
      ImmutableList.of(
          new Rational[] {new Rational(1, 12), new Rational(1, 18), new Rational(1, 36)},
          new Rational[] {new Rational(1, 2), new Rational(1, 2), new Rational(0, 1)},
          new Rational[] {new Rational(1, 6), new Rational(1, 3), new Rational(-1, 6)},
          new Rational[] {new Rational(1, 2), new Rational(1, 3), new Rational(1, 6)});

  @Theory
  public void subtract_calculatesCorrectly(@FromDataPoints("subtraction") Rational[] values) {
    assertThat(values[0].subtract(values[1])).isEqualTo(values[2]);
  }

  @DataPoints("multiplication")
  public static final ImmutableList<Rational[]> valuesForMultiplication =
      ImmutableList.of(
          new Rational[] {new Rational(1, 12), new Rational(1, 18), new Rational(1, 216)},
          new Rational[] {new Rational(1, 2), new Rational(1, 2), new Rational(1, 4)},
          new Rational[] {new Rational(1, 6), new Rational(1, 3), new Rational(1, 18)},
          new Rational[] {new Rational(1, 2), new Rational(1, 3), new Rational(1, 6)});

  @Theory
  public void multiply_calculatesCorrectly(@FromDataPoints("multiplication") Rational[] values) {
    assertThat(values[0].multiply(values[1])).isEqualTo(values[2]);
  }

  @DataPoints("division")
  public static final ImmutableList<Rational[]> valuesForDivision =
      ImmutableList.of(
          new Rational[] {new Rational(1, 12), new Rational(1, 18), new Rational(3, 2)},
          new Rational[] {new Rational(1, 2), new Rational(1, 2), new Rational(1, 1)},
          new Rational[] {new Rational(1, 6), new Rational(1, 3), new Rational(1, 2)},
          new Rational[] {new Rational(1, 2), new Rational(1, 3), new Rational(3, 2)});

  @Theory
  public void divide_calculatesCorrectly(@FromDataPoints("division") Rational[] values) {
    assertThat(values[0].divide(values[1])).isEqualTo(values[2]);
  }

  @Test
  public void compareTo_calculatesCorrectly_whenDifferentDenominators() {
    assertThat(new Rational(1, 2).compareTo(new Rational(1, 3))).isGreaterThan(0);
  }

  @Test
  public void compareTo_calculatesCorrectly_whenOneIsNegative() {
    assertThat(new Rational(-1, 2).compareTo(new Rational(1, 3))).isLessThan(0);
  }

  @Test
  public void toString_returnsSingleInteger_whenDenominatorIs1() {
    assertThat(new Rational(2, 1).toString()).isEqualTo("2");
  }

  @Test
  public void toString_includesNominatorAndDenominator() {
    assertThat(new Rational(1, 2).toString()).isEqualTo("1/2");
  }
}
