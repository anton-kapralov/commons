package kae.math;

import java.util.Arrays;

import static java.lang.System.arraycopy;
import static kae.math.Rational.ONE;
import static kae.math.Rational.ZERO;

/**
 * Represents a matrix of rational numbers.
 *
 * <p>Immutable.
 *
 * @author Anton Kapralov.
 */
public class RationalMatrix {

  private final Rational[][] values;
  private final int n;
  private final int m;

  public RationalMatrix(Rational[][] values) {
    n = values.length;

    if (n == 0) {
      m = 0;
      this.values = new Rational[0][];
      return;
    }

    m = values[0].length;

    this.values = new Rational[n][m];

    for (int i = 0; i < n; i++) {
      arraycopy(values[i], 0, this.values[i], 0, m);
    }
  }

  public RationalMatrix add(RationalMatrix val) {
    if (n != val.n || m != val.m) {
      throw new IllegalArgumentException("Matrices must be of equal size.");
    }

    Rational[][] sum = new Rational[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        sum[i][j] = values[i][j].add(val.values[i][j]);
      }
    }

    return new RationalMatrix(sum);
  }

  public RationalMatrix subtract(RationalMatrix val) {
    if (n != val.n || m != val.m) {
      throw new IllegalArgumentException("Matrices must be of equal size.");
    }

    Rational[][] difference = new Rational[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        difference[i][j] = values[i][j].subtract(val.values[i][j]);
      }
    }

    return new RationalMatrix(difference);
  }

  public RationalMatrix multiply(RationalMatrix val) {
    if (m != val.n) {
      throw new IllegalArgumentException("Left matrix's width must be equal right's matrix height.");
    }

    Rational[][] product = new Rational[n][val.m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < val.m; j++) {
        Rational sum = ZERO;
        for (int k = 0; k < m; k++) {
          sum = sum.add(values[i][k].multiply(val.values[k][j]));
        }
        product[i][j] = sum;
      }
    }

    return new RationalMatrix(product);
  }

  public RationalMatrix minor(int row, int col) {
    Rational[][] minorValues = new Rational[n - 1][m - 1];

    for (int i = 0; i < n; i++) {
      if (i == row) {
        continue;
      }

      for (int j = 0; j < m; j++) {
        if (j == col) {
          continue;
        }

        minorValues[i < row ? i : i - 1][j < col ? j : j - 1] = values[i][j];
      }
    }

    return new RationalMatrix(minorValues);
  }

  public Rational determinant() {
    if (n != m) {
      throw new IllegalArgumentException("Matrix must be square to calculate its determinant.");
    }

    if (n == 1) {
      return values[0][0];
    }

    if (n == 2) {
      return values[0][0].multiply(values[1][1]).subtract(values[0][1].multiply(values[1][0]));
    }

    Rational determinant = ZERO;
    for (int i = 0; i < n; i++) {
      Rational minorDeterminant = minor(0, i).determinant();
      determinant =
          determinant.add(
              values[0][i].multiply(i % 2 == 0 ? minorDeterminant : minorDeterminant.negate()));
    }
    return determinant;
  }

  public RationalMatrix inverse() {
    if (n != m) {
      throw new IllegalArgumentException("Matrix must be square to be inverted.");
    }

    Rational[][] inverted = new Rational[n][n];

    if (n == 1) {
      inverted[0][0] = values[0][0].reciprocal();
      return new RationalMatrix(inverted);
    }

    Rational multiplier = determinant().reciprocal();
    if (n == 2) {
      inverted[0][0] = values[1][1].multiply(multiplier);
      inverted[1][1] = values[0][0].multiply(multiplier);
      inverted[0][1] = values[0][1].negate().multiply(multiplier);
      inverted[1][0] = values[1][0].negate().multiply(multiplier);
      return new RationalMatrix(inverted);
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        Rational minorDeterminant = minor(i, j).determinant();
        inverted[i][j] = (i + j) % 2 == 0 ? minorDeterminant : minorDeterminant.negate();
      }
    }

    for (int i = 0; i < n; i++) {
      inverted[i][i] = inverted[i][i].multiply(multiplier);
      for (int j = i + 1; j < n; j++) {
        swap(inverted, i, j, j, i);
        inverted[i][j] = inverted[i][j].multiply(multiplier);
        inverted[j][i] = inverted[j][i].multiply(multiplier);
      }
    }

    return new RationalMatrix(inverted);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RationalMatrix that = (RationalMatrix) o;

    return Arrays.deepEquals(values, that.values);
  }

  @Override
  public int hashCode() {
    return Arrays.deepHashCode(values);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        sb.append(values[i][j]);
        if (j + 1 < m) {
          sb.append(" ");
        }
      }
      if (i + 1 < n) {
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  private static void swap(Rational[][] m, int row1, int col1, int row2, int col2) {
    Rational tmp = m[row1][col1];
    m[row1][col1] = m[row2][col2];
    m[row2][col2] = tmp;
  }

  public static RationalMatrix identity(int size) {
    Rational[][] values = new Rational[size][size];

    for (int i = 0; i < size; i++) {
      Arrays.fill(values[i], ZERO);
      values[i][i] = ONE;
    }

    return new RationalMatrix(values);
  }
}
