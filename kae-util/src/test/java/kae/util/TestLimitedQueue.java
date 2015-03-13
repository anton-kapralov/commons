package kae.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * TestLimitedQueue
 *
 * @author Kapralov A.
 *         03.07.2014 20:32
 */
public class TestLimitedQueue {

  @Test
  public void testAdd() throws Exception {
    final int size = 3;
    LimitedQueue<Integer> queue = new LimitedQueue<Integer>(size);

    for (int i = 0; i < size; i++) {
      queue.add(i);
    }

    Assert.assertEquals(size, queue.size());

    queue.add(size);

    Assert.assertEquals(size, queue.size());
    for (int i = 0; i < size; i++) {
      Assert.assertEquals(i + 1, (int) queue.get(i));
    }
  }

}
