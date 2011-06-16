package com.cilamp.factory;

import java.awt.MenuItem;
import java.awt.PopupMenu;

public class PopupMenuFactory {

  public PopupMenu createPopupMenu() {
    return new PopupMenu();
  }

  public MenuItem createMenuItem(String label) {
    return new MenuItem(label);
  }

}
