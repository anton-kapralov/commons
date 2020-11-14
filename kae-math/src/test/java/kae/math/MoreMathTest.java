package kae.math;

import com.google.common.collect.ImmutableList;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static com.google.common.truth.Truth.assertThat;
import static kae.math.MoreMath.gcd;
import static kae.math.MoreMath.lcm;

/** Tests {@link MoreMath}. */
@RunWith(Theories.class)
public class MoreMathTest {

  @DataPoints("gcd")
  public static final ImmutableList<int[]> valuesForGcd =
      ImmutableList.of(new int[] {2, 4, 2}, new int[] {1, 3, 1}, new int[] {12, 18, 6});

  @DataPoints("lcm")
  public static final ImmutableList<int[]> valuesForLcm =
      ImmutableList.of(new int[] {2, 4, 4}, new int[] {1, 3, 3}, new int[] {12, 18, 36});

  @Theory
  public void gcd_calculatesCorrectly(@FromDataPoints("gcd") int[] values) {
    assertThat(gcd(values[0], values[1])).isEqualTo(values[2]);
  }

  @Theory
  public void lcm_calculatesCorrectly(@FromDataPoints("lcm") int[] values) {
    assertThat(lcm(values[0], values[1])).isEqualTo(values[2]);
  }
}
