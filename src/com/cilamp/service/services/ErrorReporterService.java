package com.cilamp.service.services;

import com.cilamp.gui.app.CILampGuiPresenter;
import com.cilamp.gui.tray.CILampTrayService;
import com.cilamp.gui.util.DialogManager;

public class ErrorReporterService {

  private DialogManager dialogManager = new DialogManager();
  private CILampGuiPresenter.View view;
  private CILampTrayService trayService;

  public void initialize(CILampGuiPresenter.View view,
      CILampTrayService trayService) {
    this.view = view;
    this.trayService = trayService;
  }

  public void notifyError(Throwable e) {
    dialogManager.showMessageDialog(
        view.getParentComponent(),
        "Following error occured, view the log file for more details:\n"
            + e.getMessage());
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
