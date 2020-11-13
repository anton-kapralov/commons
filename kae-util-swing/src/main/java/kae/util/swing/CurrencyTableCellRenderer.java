/*
 *
 *
 * Kapralov A.
 * 26.11.11
 */

package kae.util.swing;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.NumberFormat;

/** @author A. Kapralov 26.11.11 14:11 */
public class CurrencyTableCellRenderer extends DefaultTableCellRenderer {

  public CurrencyTableCellRenderer() {
    super();
    setHorizontalAlignment(SwingConstants.RIGHT);
  }

  @Override
  protected void setValue(Object value) {
    if (value != null && value instanceof Number) {
      NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
      value = currencyFormat.format(((Number) value).doubleValue());
    }
    super.setValue(value);
  }
}
