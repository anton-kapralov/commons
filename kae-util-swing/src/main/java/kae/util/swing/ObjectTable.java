/*
 *
 *
 * Kapralov A.
 * 21.12.11
 */

package kae.util.swing;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * @author A. Kapralov 21.12.11 14:44
 */
public class ObjectTable extends JTable {

  public ObjectTable(ObjectTableModel model) {
    super(model);
    initializeColumns(model.getMetaColumns());
  }

  private void initializeColumns(ObjectTableMetaColumn[] metaColumns) {
    TableColumnModel columnModel = getColumnModel();
    DoubleClickDefaultCellEditor defaultCellEditor = null;
    for (int i = 0; i < getColumnCount(); i++) {
      TableColumn tableColumn = columnModel.getColumn(i);
      ObjectTableMetaColumn metaColumn = metaColumns[i];
      int preferredWidth = metaColumn.getPreferredWidth();
      if (preferredWidth > 0) {
        tableColumn.setPreferredWidth(preferredWidth);
      }
      TableCellRenderer tableCellRenderer = metaColumn.getTableCellRenderer();
      if (tableCellRenderer != null) {
        tableColumn.setCellRenderer(tableCellRenderer);
      }

      if (metaColumn.isEditable()) {
        TableCellEditor customEditor = metaColumn.getTableCellEditor();
        if (customEditor != null) {
          tableColumn.setCellEditor(customEditor);
        } else {
          if (defaultCellEditor == null) {
            defaultCellEditor = new DoubleClickDefaultCellEditor(new JTextField());
          }
          tableColumn.setCellEditor(defaultCellEditor);
        }
      }

    }
  }

}
