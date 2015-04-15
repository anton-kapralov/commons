package kae.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * TestLRUCache
 *
 * @author Kapralov A.
 *         15.04.2015 14:13
 */
public class TestLRUCache {

  @Test
  public void testOneSized() throws Exception {
    int capacity = 1;
    LRUCache<Integer, Integer> cache = new LRUCache<Integer, Integer>(capacity);

    for (int i = 0; i < 10; i++) {
      int value = 100 + i;
      cache.set(i, value);
      Assert.assertEquals(Integer.valueOf(value), cache.get(i));
      if (i > 0) {
        Assert.assertNull(cache.get(i - 1));
      }
      Assert.assertTrue(cache.size() <= capacity);
    }
  }

  @Test
  public void testOverflow() throws Exception {
    int capacity = 4;
    LRUCache<Integer, Integer> cache = new LRUCache<Integer, Integer>(capacity);

    for (int i = 0; i < capacity; i++) {
      int value = 100 + i;
      cache.set(i, value);
    }

    for (int i = 0; i < capacity; i++) {
      int value = 100 + i;
      Assert.assertEquals(Integer.valueOf(value), cache.get(i));
    }

    cache.get(0);
    cache.get(1);
    cache.get(3);

    cache.set(capacity, 100 + capacity);

    Assert.assertEquals(capacity, cache.size());

    for (int i = 0; i < capacity + 1; i++) {
      if (i == 2) {
        Assert.assertNull(cache.get(i));
        continue;
      }

      int value = 100 + i;
      Assert.assertEquals(Integer.valueOf(value), cache.get(i));
    }
  }

}
