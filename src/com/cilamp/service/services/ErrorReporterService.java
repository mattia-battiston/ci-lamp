package com.cilamp.service.services;

import java.awt.TrayIcon.MessageType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cilamp.CILamp;
import com.cilamp.gui.app.CILampGuiPresenter;
import com.cilamp.gui.tray.CILampTrayService;
import com.cilamp.gui.util.DialogManager;

public class ErrorReporterService {

  final Logger log = LoggerFactory.getLogger(CILamp.class);

  private DialogManager dialogManager = new DialogManager();
  private CILampGuiPresenter.View view;
  private CILampTrayService trayService;

  public void initialize(CILampGuiPresenter.View view,
      CILampTrayService trayService) {
    this.view = view;
    this.trayService = trayService;
  }

  public void notifyError(Throwable e) {
    if (view.isShown()) {
      log.info("reporting error as message dialog: " + e.getMessage());
      dialogManager.showMessageDialog(
          view.getParentComponent(),
          "Following error occured, view the log file for more details:\n"
              + e.getMessage());
    } else {
      log.info("reporting error as tray message: " + e.getMessage());
      trayService.getTrayIcon().displayMessage("Error occured", e.getMessage(),
          MessageType.ERROR);
    }
  }

  public void setDialogManager(DialogManager dialogManager) {
    this.dialogManager = dialogManager;
  }

  public void setView(CILampGuiPresenter.View view) {
    this.view = view;
  }

  public void setTray(CILampTrayService trayService) {
    this.trayService = trayService;
  }

}
