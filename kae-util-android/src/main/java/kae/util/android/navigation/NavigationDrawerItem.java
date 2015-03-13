package kae.util.android.navigation;

public interface NavigationDrawerItem {

  public int getId();

  public int getLabelId();

  int getIconId();

  public int getType();

  public boolean isEnabled();

  public boolean updateActionBarTitle();

  public boolean isCheckable();
}
