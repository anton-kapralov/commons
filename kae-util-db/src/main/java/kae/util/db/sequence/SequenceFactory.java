/*
 *
 *
 * Kapralov A.
 * 04.03.13
 */

package kae.util.db.sequence;

import javax.sql.DataSource;

/**
 * @author A. Kapralov
 * 04.03.13 13:46
 */
public class SequenceFactory {

  private static SequenceFactory instance;

  public static SequenceFactory getInstance() {
    if (instance == null) {
      instance = new SequenceFactory();
    }

    return instance;
  }

  private Sequence sequenceInstance;

  Sequence getSequence() throws SequenceException {
    if (sequenceInstance == null) {
      throw new SequenceException("ID sequence has not initialized");
    }

    return sequenceInstance;
  }

  public void initialize(DataSource dataSource) {
    if (sequenceInstance == null) {
      sequenceInstance = new SequenceImpl(dataSource);
    }
  }


}
