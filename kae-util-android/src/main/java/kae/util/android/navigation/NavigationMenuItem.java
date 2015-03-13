package kae.util.android.navigation;

public class NavigationMenuItem implements NavigationDrawerItem {

  public static final int ITEM_TYPE = 1;

  private int id;

  private int labelId;

  private int iconId;

  private boolean updateActionBar;

  private boolean checkable;

  public NavigationMenuItem(int id, int labelId, int iconId, boolean updateActionBar,
      boolean checkable) {
    this.id = id;
    this.labelId = labelId;
    this.iconId = iconId;
    this.updateActionBar = updateActionBar;
    this.checkable = checkable;
  }

  @Override
  public int getType() {
    return ITEM_TYPE;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getLabelId() {
    return labelId;
  }

  public void setLabelId(int labelId) {
    this.labelId = labelId;
  }

  @Override
  public int getIconId() {
    return iconId;
  }

  public void setIconId(int iconId) {
    this.iconId = iconId;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean updateActionBarTitle() {
    return this.updateActionBar;
  }

  public void setUpdateActionBar(boolean updateActionBar) {
    this.updateActionBar = updateActionBar;
  }

  public boolean isCheckable() {
    return checkable;
  }

  public void setCheckable(boolean checkable) {
    this.checkable = checkable;
  }
}
