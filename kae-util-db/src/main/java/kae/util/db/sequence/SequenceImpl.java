package kae.util.db.sequence;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

class SequenceImpl implements Sequence {

  private static final int IDX_KEY = 1;
  private static final int IDX_FETCH_SIZE = 2;
  private static final int IDX_RANGE_START = 3;
  private static final int IDX_RANGE_END = 4;

  private final DataSource dataSource;

  public SequenceImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  private DataSource getDataSource() {
    return dataSource;
  }

  /**
   * @param pool
   * @inheritDoc
   */
  public void reserve(IdentifierPool pool) throws SequenceException {
    try {
      Connection connection = getDataSource().getConnection();
      try {
        CallableStatement st = connection.prepareCall("{call SEQ_SEQUENCE_NEXT(?, ?, ?, ?)}");

        st.setString(IDX_KEY, pool.getKey());
        st.setInt(IDX_FETCH_SIZE, pool.getFetchSize());
        st.registerOutParameter(IDX_RANGE_START, Types.NUMERIC);
        st.registerOutParameter(IDX_RANGE_END, Types.NUMERIC);
        st.executeUpdate();

        pool.update(st.getLong(IDX_RANGE_START), st.getLong(IDX_RANGE_END));
      } finally {
        connection.close();
      }
    } catch (SQLException thrown) {
      throw new SequenceException(thrown);
    }
  }
}
