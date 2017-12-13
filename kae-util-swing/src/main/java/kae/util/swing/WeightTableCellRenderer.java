/*
 *
 *
 * Kapralov A.
 * 26.11.11
 */

package kae.util.swing;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author A. Kapralov 26.11.11 14:11
 */
public class WeightTableCellRenderer extends DefaultTableCellRenderer {

  public WeightTableCellRenderer() {
    super();
    setHorizontalAlignment(SwingConstants.RIGHT);
  }

  @Override
  protected void setValue(Object value) {
    if (value != null && value instanceof Number) {
      value = String.format("%1.2f кг", ((Number) value).doubleValue()).replace(',', '.');
    }
    super.setValue(value);
  }

}
