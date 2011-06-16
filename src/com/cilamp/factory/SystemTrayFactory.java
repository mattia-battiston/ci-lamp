package com.cilamp.factory;

import java.awt.SystemTray;

public class SystemTrayFactory {

  public SystemTray getSystemTray() {
    return SystemTray.getSystemTray();
  }

  public boolean isSystemTraySupported() {
    return SystemTray.isSupported();
  }

}
