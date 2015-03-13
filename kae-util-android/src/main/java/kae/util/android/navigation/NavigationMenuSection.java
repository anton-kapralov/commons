package kae.util.android.navigation;

public class NavigationMenuSection implements NavigationDrawerItem {

  public static final int SECTION_TYPE = 0;

  private int id;

  private int labelId;

  public NavigationMenuSection(int id, int labelId) {
    this.id = id;
    this.labelId = labelId;
  }

  @Override
  public int getType() {
    return SECTION_TYPE;
  }

  public int getLabelId() {
    return labelId;
  }

  public void setLabelId(int labelId) {
    this.labelId = labelId;
  }

  @Override
  public int getIconId() {
    return 0;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean updateActionBarTitle() {
    return false;
  }

  @Override
  public boolean isCheckable() {
    return false;
  }
}
