package com.cilamp.factory;

import java.awt.Image;
import java.awt.TrayIcon;

public class TrayIconFactory {

  public TrayIcon createTrayIcon(Image image) {
    return new TrayIcon(image);
  }
}
