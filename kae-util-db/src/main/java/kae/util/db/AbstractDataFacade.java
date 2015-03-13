/*
 * 
 * 
 * Kapralov A.
 * 15.03.13
 */

package kae.util.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * @author A. Kapralov
 *         15.03.13 17:20
 */
public abstract class AbstractDataFacade {

  protected final DataSource ds;

  public AbstractDataFacade(DataSource ds) {
    this.ds = ds;
  }

  protected void rollbackTransaction(Connection connection) {
    if (connection != null) {
      try {
        connection.rollback();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  protected void closeConnection(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}
