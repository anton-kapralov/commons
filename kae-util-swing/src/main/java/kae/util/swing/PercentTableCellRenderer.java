/*
 *
 *
 * Kapralov A.
 * 26.11.11
 */

package kae.util.swing;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.Locale;

/**
 * @author A. Kapralov 26.11.11 14:11
 */
public class PercentTableCellRenderer extends DefaultTableCellRenderer {

  public PercentTableCellRenderer() {
    super();
    setHorizontalAlignment(SwingConstants.RIGHT);
  }

  @Override
  protected void setValue(Object value) {
    if (value != null && value instanceof Number) {
      final double doubleValue = ((Number) value).doubleValue();
      value = String.format(Locale.US, "%01.2f%%", doubleValue);
    }
    super.setValue(value);
  }

}
