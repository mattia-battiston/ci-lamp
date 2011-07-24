package com.cilamp.gui.tray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.cilamp.event.base.EventBus;
import com.cilamp.event.base.EventBusInstance;
import com.cilamp.gui.app.CILampGuiPresenter;
import com.cilamp.gui.factory.SystemTrayFactory;
import com.cilamp.gui.factory.TrayIconFactory;

public class CILampTrayService {

  private CILampGuiPresenter mainGui;

  private SystemTrayFactory systemTrayFactory = new SystemTrayFactory();
  private TrayIconFactory trayIconFactory = new TrayIconFactory();
  private CILampTrayMenu trayMenu = new CILampTrayMenu();

  private EventBus eventBus = EventBusInstance.getEventBus();

  private TrayIcon trayIcon;

  public void init() {
    try {
      initializeApplicationInSystemTray();
    } catch (AWTException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  private void initializeApplicationInSystemTray() throws AWTException {
    ensureTrayIconsAreaSupported();

    addApplicationTrayIcon();
    initRightClickMenuOnTrayIcon();
    openMainGuiOnClick();
  }

  private void openMainGuiOnClick() {
    trayIcon.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainGui.show();
      }
    });
  }

  private void initRightClickMenuOnTrayIcon() {
    trayIcon.setPopupMenu(trayMenu.init(mainGui, eventBus));
  }

  private void addApplicationTrayIcon() throws AWTException {
    SystemTray tray = systemTrayFactory.getSystemTray();
    // TODO get this image from classpath
    Image image = Toolkit.getDefaultToolkit().getImage("CILavaLamp-icon.png");
    trayIcon = trayIconFactory.createTrayIcon(image);
    trayIcon.setToolTip("CI Lamp");
    trayIcon.setImageAutoSize(true);
    tray.add(trayIcon);
  }

  private void ensureTrayIconsAreaSupported() {
    if (!systemTrayFactory.isSystemTraySupported())
      throw new RuntimeException("system tray is not supported");
  }

  public void setMainGui(CILampGuiPresenter gui) {
    this.mainGui = gui;
  }

  public void setSystemTrayFactory(SystemTrayFactory systemTrayFactory) {
    this.systemTrayFactory = systemTrayFactory;
  }

  public void setTrayIconFactory(TrayIconFactory trayIconFactory) {
    this.trayIconFactory = trayIconFactory;
  }

  public void setTrayMenu(CILampTrayMenu trayMenu) {
    this.trayMenu = trayMenu;
  }

  public TrayIcon getTrayIcon() {
    return trayIcon;
  }

}
