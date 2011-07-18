package com.cilamp;

import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import org.junit.Test;

import com.cilamp.gui.tray.CILampTrayService;

public class TrayMessageSpike {

  @Test
  public void trayMessageTest() throws InterruptedException {
    CILampTrayService trayService = new CILampTrayService();
    trayService.init();

    TrayIcon trayIcon = trayService.getTrayIcon();
    trayIcon.displayMessage("Error occured", "this is the error",
        MessageType.ERROR);
    Thread.sleep(1000);
  }

}
