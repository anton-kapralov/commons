/*
 * 
 * 
 * Kapralov A.
 * 15.12.13
 */

package kae.util.android;

import android.widget.AbsListView;

/**
 * @author A. Kapralov
 *         15.12.13 19:23
 */
public class EndlessScrollListener implements AbsListView.OnScrollListener {

  private int visibleThreshold = 5;
  private int currentPage = 0;
  private int previousTotal = 0;
  private boolean loading = true;

  private final Loader loader;

  public EndlessScrollListener(Loader loader) {
    this.loader = loader;
  }

  public EndlessScrollListener(Loader loader, int visibleThreshold) {
    this.loader = loader;
    this.visibleThreshold = visibleThreshold;
  }

  public void invalidate() {
    visibleThreshold = 5;
    currentPage = 0;
    previousTotal = 0;
    loading = true;
  }

  @Override
  public void onScroll(AbsListView view, int firstVisibleItem,
      int visibleItemCount, int totalItemCount) {
    if (loading) {
      if (totalItemCount > previousTotal) {
        loading = false;
        previousTotal = totalItemCount;
        currentPage++;
      }
    }
    if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
      // I load the next page of gigs using a background task,
      // but you can call any function here.
      loader.load(currentPage + 1);
      loading = true;
    }
  }

  @Override
  public void onScrollStateChanged(AbsListView view, int scrollState) {
  }

  public static interface Loader {

    void load(int page);

  }

}
