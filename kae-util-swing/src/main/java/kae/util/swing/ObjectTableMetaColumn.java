/*
 *
 *
 * Kapralov A.
 * 26.11.11
 */

package kae.util.swing;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/** @author A. Kapralov 26.11.11 14:22 */
public interface ObjectTableMetaColumn<ObjectClass, ObjectController> {

  Class<?> getValueClass();

  String getName();

  Object getValue(ObjectClass o);

  int getPreferredWidth();

  TableCellRenderer getTableCellRenderer();

  boolean isEditable();

  TableCellEditor getTableCellEditor();

  ObjectClass setValue(ObjectController controller, ObjectClass o, Object value);
}
