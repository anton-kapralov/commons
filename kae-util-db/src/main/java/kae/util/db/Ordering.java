/*
 * 
 * 
 * Kapralov A.
 * 20.03.13
 */

package kae.util.db;

/**
 * @author A. Kapralov
 *         20.03.13 13:39
 */
public class Ordering {

  private String fieldName;

  private OrderingDirection direction;

  public Ordering() {
  }

  public Ordering(String fieldName, OrderingDirection direction) {
    this.fieldName = fieldName;
    this.direction = direction;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public OrderingDirection getDirection() {
    return direction;
  }

  public void setDirection(OrderingDirection direction) {
    this.direction = direction;
  }
}
