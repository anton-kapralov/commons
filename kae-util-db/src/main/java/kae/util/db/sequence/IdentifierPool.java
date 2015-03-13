package kae.util.db.sequence;

public interface IdentifierPool {

  String getKey();

  int getFetchSize();

  long getNextId();

  void update(long currentValue, long highValue);
}
