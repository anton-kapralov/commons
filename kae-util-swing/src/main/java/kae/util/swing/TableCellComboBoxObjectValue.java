/*
 *
 *
 * Kapralov A.
 * 11.01.12
 */

package kae.util.swing;

/**
 * @author A. Kapralov 11.01.12 10:28
 */
public class TableCellComboBoxObjectValue<T> {

  private T value;

  private String name;

  public TableCellComboBoxObjectValue(T value, String name) {
    this.value = value;
    this.name = name;
  }

  public T getValue() {
    return value;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    TableCellComboBoxObjectValue that = (TableCellComboBoxObjectValue) o;

    return !(value != null ? !value.equals(that.value) : that.value != null);

  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }

  @SuppressWarnings("unchecked")
  public static final TableCellComboBoxObjectValue EMPTY =
      new TableCellComboBoxObjectValue(null, "");

}
