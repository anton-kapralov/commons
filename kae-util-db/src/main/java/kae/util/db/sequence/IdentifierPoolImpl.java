package kae.util.db.sequence;

class IdentifierPoolImpl implements IdentifierPool {

  private static final int DEFAULT_FETCH_SIZE = 100;

  private final String key;
  private final int fetchSize;

  private long currentValue = 0;
  private long highValue = 0;

  public IdentifierPoolImpl(String key) {
    this(key, DEFAULT_FETCH_SIZE);
  }

  public IdentifierPoolImpl(String key, int fetchSize) {
    this.key = key;
    this.fetchSize = fetchSize;
  }

  @Override
  public synchronized long getNextId() {
    if (highValue <= currentValue) {
      reserve();
    }

    currentValue++;

    return currentValue;
  }

  @Override
  public void update(long currentValue, long highValue) {
    this.currentValue = currentValue;
    this.highValue = highValue;
  }

  private void reserve() {
    try {
      Sequence sequence = SequenceFactory.getInstance().getSequence();
      sequence.reserve(this);
    } catch (SequenceException thrown) {
      throw new RuntimeException(thrown);
    }
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public int getFetchSize() {
    return fetchSize;
  }

}
