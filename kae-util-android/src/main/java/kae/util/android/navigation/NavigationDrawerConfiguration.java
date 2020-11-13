package kae.util.android.navigation;

import android.widget.ArrayAdapter;

public class NavigationDrawerConfiguration {

  private int mainLayout;
  private int drawerShadow;
  private int drawerLayoutId;
  private int leftDrawerId;
  private int[] actionMenuItemsToHideWhenDrawerOpen;
  private NavigationDrawerItem[] navItems;
  private int drawerOpenDesc;
  private int drawerCloseDesc;
  private int drawerIcon;
  private ArrayAdapter<NavigationDrawerItem> adapter;

  public int getMainLayout() {
    return mainLayout;
  }

  public void setMainLayout(int mainLayout) {
    this.mainLayout = mainLayout;
  }

  public int getDrawerShadow() {
    return drawerShadow;
  }

  public void setDrawerShadow(int drawerShadow) {
    this.drawerShadow = drawerShadow;
  }

  public int getDrawerLayoutId() {
    return drawerLayoutId;
  }

  public void setDrawerLayoutId(int drawerLayoutId) {
    this.drawerLayoutId = drawerLayoutId;
  }

  public int getLeftDrawerId() {
    return leftDrawerId;
  }

  public void setLeftDrawerId(int leftDrawerId) {
    this.leftDrawerId = leftDrawerId;
  }

  public int[] getActionMenuItemsToHideWhenDrawerOpen() {
    return actionMenuItemsToHideWhenDrawerOpen;
  }

  public void setActionMenuItemsToHideWhenDrawerOpen(int[] actionMenuItemsToHideWhenDrawerOpen) {
    this.actionMenuItemsToHideWhenDrawerOpen = actionMenuItemsToHideWhenDrawerOpen;
  }

  public int getDrawerOpenDesc() {
    return drawerOpenDesc;
  }

  public void setDrawerOpenDesc(int drawerOpenDesc) {
    this.drawerOpenDesc = drawerOpenDesc;
  }

  public int getDrawerCloseDesc() {
    return drawerCloseDesc;
  }

  public void setDrawerCloseDesc(int drawerCloseDesc) {
    this.drawerCloseDesc = drawerCloseDesc;
  }

  public ArrayAdapter<NavigationDrawerItem> getAdapter() {
    return adapter;
  }

  public void setAdapter(ArrayAdapter<NavigationDrawerItem> adapter) {
    this.adapter = adapter;
  }

  public int getDrawerIcon() {
    return drawerIcon;
  }

  public void setDrawerIcon(int drawerIcon) {
    this.drawerIcon = drawerIcon;
  }
}
