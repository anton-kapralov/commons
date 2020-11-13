/*
 *
 *
 * Kapralov A.
 * 22.03.13
 */

package kae.util.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** @author A. Kapralov 22.03.13 16:35 */
public class SQLUtils {

  public static String makeMask(String s) {
    return s != null ? String.format("%%%s%%", s) : null;
  }

  public static long getSingleLongResult(PreparedStatement st) throws SQLException {
    return getSingleLongResult(st.executeQuery());
  }

  public static long getSingleLongResult(ResultSet rs) throws SQLException {
    if (rs.next()) {
      return rs.getLong(1);
    } else {
      return 0;
    }
  }

  public static void appendLimit(StringBuilder sb, long firstResult, int maxResults) {
    if (firstResult > 0 && maxResults > 0) {
      sb.append(" LIMIT ?, ?");
    } else if (maxResults > 0) {
      sb.append(" LIMIT ?");
    }
  }

  public static void setLimit(PreparedStatement st, int parIdx, long first, int max)
      throws SQLException {
    if (first > 0 && max > 0) {
      st.setLong(++parIdx, first);
      st.setInt(++parIdx, max);
    } else if (max > 0) {
      st.setInt(++parIdx, max);
    }
  }
}
