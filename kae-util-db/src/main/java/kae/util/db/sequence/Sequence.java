package kae.util.db.sequence;

public interface Sequence {

  public void reserve(IdentifierPool pool) throws SequenceException;
}
