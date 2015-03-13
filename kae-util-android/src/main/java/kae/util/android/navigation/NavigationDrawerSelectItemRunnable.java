package kae.util.android.navigation;

public class NavigationDrawerSelectItemRunnable implements Runnable {

  private NavigationDrawer navigationDrawer;

  private int position;

  public NavigationDrawerSelectItemRunnable(NavigationDrawer navigationDrawer, int position) {
    this.navigationDrawer = navigationDrawer;
    this.position = position;
  }

  @Override
  public void run() {
    navigationDrawer.selectItem(position);
  }
}
