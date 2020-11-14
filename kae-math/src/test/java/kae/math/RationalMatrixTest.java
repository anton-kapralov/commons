package kae.math;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static kae.math.Rational.ONE;
import static kae.math.Rational.ZERO;
import static org.junit.Assert.assertThrows;

/** Tests {@link RationalMatrix}. */
public class RationalMatrixTest {

  @Test
  public void add_throwsIllegalArgumentException_whenDifferentSize() {
    RationalMatrix left = RationalMatrix.identity(2);
    RationalMatrix right = RationalMatrix.identity(3);

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> left.add(right));

    assertThat(exception).hasMessageThat().isEqualTo("Matrices must be of equal size.");
  }

  @Test
  public void add_returnsCorrectly_when2x2() {
    RationalMatrix left = RationalMatrix.identity(2);
    RationalMatrix right =
        new RationalMatrix(
            new Rational[][] {
              {new Rational(1, 2), Rational.valueOf(1)},
              {new Rational(1, 3), new Rational(1, 4)},
            });

    RationalMatrix sum = left.add(right);

    assertThat(sum)
        .isEqualTo(
            new RationalMatrix(
                new Rational[][] {
                  {new Rational(3, 2), Rational.valueOf(1)},
                  {new Rational(1, 3), new Rational(5, 4)}
                }));
  }

  @Test
  public void add_returnsCorrectly_when2x3() {
    RationalMatrix left = new RationalMatrix(
        new Rational[][] {
            {Rational.valueOf(1), Rational.valueOf(1), Rational.valueOf(1)},
            {Rational.valueOf(1), Rational.valueOf(1), Rational.valueOf(1)},
        });
    RationalMatrix right =
        new RationalMatrix(
            new Rational[][] {
                {new Rational(1, 2), Rational.valueOf(1), Rational.valueOf(2)},
                {new Rational(1, 3), new Rational(1, 4), new Rational(1, 5)},
            });

    RationalMatrix sum = left.add(right);

    assertThat(sum)
        .isEqualTo(
            new RationalMatrix(
                new Rational[][] {
                    {new Rational(3, 2), Rational.valueOf(2), Rational.valueOf(3)},
                    {new Rational(4, 3), new Rational(5, 4), new Rational(6, 5)}
                }));
  }

  @Test
  public void subtract_throwsIllegalArgumentException_whenDifferentSize() {
    RationalMatrix left = RationalMatrix.identity(2);
    RationalMatrix right = RationalMatrix.identity(3);

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> left.subtract(right));

    assertThat(exception).hasMessageThat().isEqualTo("Matrices must be of equal size.");
  }

  @Test
  public void subtract_returnsCorrectly_when2x2() {
    RationalMatrix left = RationalMatrix.identity(2);
    RationalMatrix right =
        new RationalMatrix(
            new Rational[][] {
              {new Rational(1, 2), Rational.valueOf(1)},
              {new Rational(1, 3), new Rational(1, 4)},
            });

    RationalMatrix difference = left.subtract(right);

    assertThat(difference)
        .isEqualTo(
            new RationalMatrix(
                new Rational[][] {
                  {new Rational(1, 2), Rational.valueOf(-1)},
                  {new Rational(-1, 3), new Rational(3, 4)}
                }));
  }

  @Test
  public void subtract_returnsCorrectly_when2x3() {
    RationalMatrix left = new RationalMatrix(
        new Rational[][] {
            {Rational.valueOf(1), Rational.valueOf(1), Rational.valueOf(1)},
            {Rational.valueOf(1), Rational.valueOf(1), Rational.valueOf(1)},
        });
    RationalMatrix right =
        new RationalMatrix(
            new Rational[][] {
              {new Rational(1, 2), Rational.valueOf(1), Rational.valueOf(2)},
              {new Rational(1, 3), new Rational(1, 4), new Rational(1, 5)},
            });

    RationalMatrix result = left.subtract(right);

    assertThat(result)
        .isEqualTo(
            new RationalMatrix(
                new Rational[][] {
                  {new Rational(1, 2), Rational.valueOf(0), Rational.valueOf(-1)},
                  {new Rational(2, 3), new Rational(3, 4), new Rational(4, 5)}
                }));
  }

  @Test
  public void multiply_throwsIllegalArgumentException_whenIncompatibleSize() {
    RationalMatrix left = RationalMatrix.identity(2);
    RationalMatrix right = RationalMatrix.identity(3);

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> left.multiply(right));

    assertThat(exception).hasMessageThat().isEqualTo("Left matrix's width must be equal right's matrix height.");
  }

  @Test
  public void multiply_returnsCorrectly_when3x2And2x4() {
    RationalMatrix left =
        new RationalMatrix(
            new Rational[][] {
              {Rational.valueOf(1), Rational.valueOf(2)},
              {Rational.valueOf(3), Rational.valueOf(4)},
              {Rational.valueOf(5), Rational.valueOf(6)},
            });
    RationalMatrix right =
        new RationalMatrix(
            new Rational[][] {
              {Rational.valueOf(7), Rational.valueOf(8), Rational.valueOf(9), Rational.valueOf(10)},
              {Rational.valueOf(11), Rational.valueOf(12), Rational.valueOf(13), Rational.valueOf(14)},
            });

    RationalMatrix product = left.multiply(right);

    assertThat(product)
        .isEqualTo(
            new RationalMatrix(
                new Rational[][] {
                    {Rational.valueOf(29), Rational.valueOf(32), Rational.valueOf(35), Rational.valueOf(38)},
                    {Rational.valueOf(65), Rational.valueOf(72), Rational.valueOf(79), Rational.valueOf(86)},
                    {Rational.valueOf(101), Rational.valueOf(112), Rational.valueOf(123), Rational.valueOf(134)},
                }));
  }

  @Test
  public void minor_returnsCorrectly_when2x2() {
    RationalMatrix matrix =
        new RationalMatrix(
            new Rational[][] {
              {Rational.valueOf(4), Rational.valueOf(7)},
              {Rational.valueOf(2), Rational.valueOf(6)},
            });

    RationalMatrix minor = matrix.minor(0, 1);

    assertThat(minor)
        .isEqualTo(
            new RationalMatrix(
                new Rational[][] {
                  {Rational.valueOf(2)},
                }));
  }

  @Test
  public void minor_returnsCorrectly_when3x3() {
    RationalMatrix matrix =
        new RationalMatrix(
            new Rational[][] {
              {Rational.valueOf(1), Rational.valueOf(2), Rational.valueOf(3)},
              {Rational.valueOf(4), Rational.valueOf(5), Rational.valueOf(6)},
              {Rational.valueOf(7), Rational.valueOf(8), Rational.valueOf(9)},
            });

    RationalMatrix minor = matrix.minor(1, 2);

    assertThat(minor)
        .isEqualTo(
            new RationalMatrix(
                new Rational[][] {
                  {Rational.valueOf(1), Rational.valueOf(2)},
                  {Rational.valueOf(7), Rational.valueOf(8)},
                }));
  }

  @Test
  public void determinant_throwsIllegalArgumentException_whenNotSquare() {
    RationalMatrix matrix =
        new RationalMatrix(
            new Rational[][] {
              {ONE, ZERO, ZERO, ZERO},
              {ZERO, ONE, ZERO, ZERO},
              {ZERO, ZERO, ONE, ZERO},
            });

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, matrix::determinant);

    assertThat(exception)
        .hasMessageThat()
        .isEqualTo("Matrix must be square to calculate its determinant.");
  }

  @Test
  public void determinant_returnsOnlyValue_when1x1() {
    RationalMatrix matrix =
        new RationalMatrix(
            new Rational[][] {
              {Rational.valueOf(5)},
            });

    assertThat(matrix.determinant()).isEqualTo(Rational.valueOf(5));
  }

  @Test
  public void determinant_returnsCorrectly_when2x2() {
    RationalMatrix matrix =
        new RationalMatrix(
            new Rational[][] {
              {Rational.valueOf(3), Rational.valueOf(8)},
              {Rational.valueOf(4), Rational.valueOf(6)},
            });

    assertThat(matrix.determinant()).isEqualTo(Rational.valueOf(-14));
  }

  @Test
  public void determinant_returns1_whenIdentity4x4() {
    RationalMatrix matrix = RationalMatrix.identity(4);

    assertThat(matrix.determinant()).isEqualTo(ONE);
  }

  @Test
  public void inverse_returnsCorrectly_when2x2() {
    RationalMatrix matrix =
        new RationalMatrix(
            new Rational[][] {
              {Rational.valueOf(4), Rational.valueOf(7)},
              {Rational.valueOf(2), Rational.valueOf(6)},
            });

    RationalMatrix inverted = matrix.inverse();

    assertThat(inverted)
        .isEqualTo(
            new RationalMatrix(
                new Rational[][] {
                  {new Rational(6, 10), new Rational(-7, 10)},
                  {new Rational(-2, 10), new Rational(4, 10)},
                }));
  }

  @Test
  public void inverse_returnsCorrectly_when3x3() {
    RationalMatrix matrix =
        new RationalMatrix(
            new Rational[][] {
              {Rational.valueOf(3), Rational.valueOf(0), Rational.valueOf(2)},
              {Rational.valueOf(2), Rational.valueOf(0), Rational.valueOf(-2)},
              {Rational.valueOf(0), Rational.valueOf(1), Rational.valueOf(1)},
            });

    RationalMatrix inverted = matrix.inverse();

    assertThat(inverted)
        .isEqualTo(
            new RationalMatrix(
                new Rational[][] {
                  {new Rational(2, 10), new Rational(2, 10), Rational.valueOf(0)},
                  {new Rational(-2, 10), new Rational(3, 10), Rational.valueOf(1)},
                  {new Rational(2, 10), new Rational(-3, 10), Rational.valueOf(0)},
                }));
  }

  @Test
  public void toString_returnsAligned_whenIntegers() {
    assertThat(
            new RationalMatrix(
                    new Rational[][] {
                      {Rational.valueOf(4), Rational.valueOf(7)},
                      {Rational.valueOf(2), Rational.valueOf(6)},
                    })
                .toString())
        .isEqualTo("4 7\n2 6");
  }
}
