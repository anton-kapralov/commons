package kae.util;

import java.util.LinkedList;

/**
 * LimitedQueue
 *
 * @author Kapralov A.
 *         18.02.2014 12:50
 */
public class LimitedQueue<E> extends LinkedList<E> {

  private final int limit;

  public LimitedQueue(int limit) {
    this.limit = limit;
  }

  @Override
  public boolean add(E o) {
    super.add(o);
    while (size() > limit) { super.remove(0); }
    return true;
  }

}
