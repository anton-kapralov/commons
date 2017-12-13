/*
 *
 *
 * Kapralov A.
 * 20.03.13
 */

package kae.util.db;

import java.io.Serializable;
import java.util.List;

/**
 * @author A. Kapralov
 * 20.03.13 13:28
 */
public class DataChunk<T> implements Serializable {

  private List<T> content;

  private long totalCount;

  public DataChunk() {
  }

  public DataChunk(List<T> content, long totalCount) {
    this.content = content;
    this.totalCount = totalCount;
  }

  public List<T> getContent() {
    return content;
  }

  public void setContent(List<T> content) {
    this.content = content;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(long totalCount) {
    this.totalCount = totalCount;
  }
}
