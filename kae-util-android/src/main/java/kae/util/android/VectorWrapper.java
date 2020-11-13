/*
 *
 *
 * Kapralov A.
 * 14.09.12
 */

package kae.util.android;

import java.io.Serializable;
import java.util.Vector;

/** @author A. Kapralov 14.09.12 6:01 */
public class VectorWrapper implements Serializable {

  public final Vector value;

  public VectorWrapper(Vector value) {
    this.value = value;
  }
}
