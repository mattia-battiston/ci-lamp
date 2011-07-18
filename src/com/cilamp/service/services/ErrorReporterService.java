package com.cilamp.service.services;

import com.cilamp.gui.util.DialogManager;

public class ErrorReporterService {

  private DialogManager dialogManager = new DialogManager();

  public void notifyError(Throwable e) {
    dialogManager
        .showMessageDialog("Following error occured, view the log file for more details:\n"
            + e.getMessage());
  }

  public void setDialogManager(DialogManager dialogManager) {
    this.dialogManager = dialogManager;
  }

}
