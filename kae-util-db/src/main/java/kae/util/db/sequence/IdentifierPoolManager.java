/*
 *
 *
 * Kapralov A.
 * 04.03.13
 */

package kae.util.db.sequence;

import java.util.HashMap;
import java.util.Map;

/**
 * @author A. Kapralov
 * 04.03.13 13:54
 */
public class IdentifierPoolManager {

  private static IdentifierPoolManager instance;

  public static IdentifierPoolManager getInstance() {
    if (instance == null) {
      instance = new IdentifierPoolManager();
    }

    return instance;
  }

  private Map<String, IdentifierPool> poolMap = new HashMap<String, IdentifierPool>();

  public IdentifierPool getPool(String key) {
    IdentifierPool pool;
    synchronized (this) {
      pool = poolMap.get(key);
    }

    if (pool == null) {
      pool = new IdentifierPoolImpl(key);
      synchronized (this) {
        poolMap.put(key, pool);
      }
    }

    return pool;
  }

}
