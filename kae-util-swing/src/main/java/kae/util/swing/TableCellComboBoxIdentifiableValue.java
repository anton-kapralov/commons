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
public class TableCellComboBoxIdentifiableValue {

  private long id;

  private String name;

  public TableCellComboBoxIdentifiableValue(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
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

    TableCellComboBoxIdentifiableValue that = (TableCellComboBoxIdentifiableValue) o;

    return id == that.id;

  }

  @Override
  public int hashCode() {
    return (int) (id ^ (id >>> 32));
  }

  public static final TableCellComboBoxIdentifiableValue
      EMPTY = new TableCellComboBoxIdentifiableValue(0, "");

}
