/*
 * 
 * 
 * Kapralov A.
 * 10.04.12
 */

package kae.util.swing;

import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.*;

/**
 * @author A. Kapralov 10.04.12 12:03
 */
public class DoubleClickDefaultCellEditor extends DefaultCellEditor {

  public DoubleClickDefaultCellEditor(final JTextField textField) {
    super(textField);
  }

  public DoubleClickDefaultCellEditor(JCheckBox checkBox) {
    super(checkBox);
  }

  public DoubleClickDefaultCellEditor(JComboBox comboBox) {
    super(comboBox);
  }

  @Override
  public boolean isCellEditable(EventObject anEvent) {
    return anEvent instanceof MouseEvent &&
        ((MouseEvent) anEvent).getClickCount() >= 2;
  }
}
