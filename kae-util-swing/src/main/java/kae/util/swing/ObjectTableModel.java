/*
 *
 *
 * Kapralov A.
 * 10.12.11
 */

package kae.util.swing;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/** @author A. Kapralov 10.12.11 17:48 */
public abstract class ObjectTableModel<ObjectClass, ObjectController> extends AbstractTableModel {

  protected ObjectController controller;
  protected final ObjectTableMetaColumn<ObjectClass, ObjectController>[] metaColumns;
  protected ArrayList<ObjectClass> objects;

  public ObjectTableModel(
      ObjectController controller,
      ObjectTableMetaColumn<ObjectClass, ObjectController>[] metaColumns,
      ArrayList<ObjectClass> objects) {
    this.controller = controller;
    this.metaColumns = metaColumns;
    this.objects = objects;
  }

  public void setController(ObjectController controller) {
    this.controller = controller;
  }

  public ObjectTableMetaColumn[] getMetaColumns() {
    return metaColumns;
  }

  @Override
  public int getRowCount() {
    return objects.size();
  }

  @Override
  public int getColumnCount() {
    return metaColumns.length;
  }

  @Override
  public String getColumnName(int columnIndex) {
    return metaColumns[columnIndex].getName();
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return metaColumns[columnIndex].getValueClass();
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    return metaColumns[columnIndex].getValue(objects.get(rowIndex));
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return metaColumns[columnIndex].isEditable();
  }

  @Override
  public void setValueAt(Object value, int rowIndex, int columnIndex) {
    ObjectTableMetaColumn<ObjectClass, ObjectController> metaColumn = metaColumns[columnIndex];
    ObjectClass updatedObject = metaColumn.setValue(controller, objects.get(rowIndex), value);
    if (updatedObject != null) {
      objects.set(rowIndex, updatedObject);
      fireTableCellUpdated(rowIndex, columnIndex);
    } else {
      objects.remove(rowIndex);
      fireTableRowsDeleted(rowIndex, rowIndex);
    }
  }

  protected void updateContent(ArrayList<ObjectClass> objects) {
    this.objects = objects;

    fireTableChanged(new TableModelEvent(this));
  }

  public int size() {
    return objects.size();
  }

  public ObjectClass getAt(int rowIndex) {
    return objects.get(rowIndex);
  }

  public ObjectClass set(int index, ObjectClass element) {
    final ObjectClass result = objects.set(index, element);
    fireTableRowsUpdated(index, index);
    return result;
  }

  public boolean add(ObjectClass element) {
    boolean result = objects.add(element);
    if (result) {
      int index = objects.size() - 1;
      fireTableRowsInserted(index, index);
    }
    return result;
  }

  public void add(int index, ObjectClass element) {
    objects.add(index, element);
    fireTableRowsInserted(index, index);
  }

  public ObjectClass remove(int index) {
    ObjectClass removedObject = objects.remove(index);
    fireTableRowsDeleted(index, index);
    return removedObject;
  }

  public boolean remove(ObjectClass element) {
    boolean result = objects.remove(element);
    if (result) {
      fireTableChanged(new TableModelEvent(this));
    }
    return result;
  }
}
