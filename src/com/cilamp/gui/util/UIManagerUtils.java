package com.cilamp.gui.util;

import javax.swing.UIManager;

public class UIManagerUtils {

  public void setSystemLookAndFeel() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

}
