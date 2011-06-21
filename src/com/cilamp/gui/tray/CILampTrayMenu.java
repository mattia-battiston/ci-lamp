package com.cilamp.gui.tray;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.cilamp.gui.app.CILampGuiPresenter;
import com.cilamp.gui.factory.PopupMenuFactory;
import com.cilamp.service.services.ShutdownService;

public class CILampTrayMenu {

  // TODO use MenuView and MenuPresenter

  private PopupMenuFactory popupMenuFactory = new PopupMenuFactory();
  private ShutdownService shutdownService = new ShutdownService();

  public PopupMenu init(final CILampGuiPresenter mainGui) {
    PopupMenu popupMenu = popupMenuFactory.createPopupMenu();

    addOpen(mainGui, popupMenu);

    addExit(popupMenu);

    return popupMenu;
  }

  private void addExit(PopupMenu popupMenu) {
    MenuItem exit = popupMenuFactory.createMenuItem("Exit");
    exit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        shutdownService.shutdown();
      }
    });
    popupMenu.add(exit);
  }

  private void addOpen(final CILampGuiPresenter mainGui, PopupMenu popupMenu) {
    MenuItem open = popupMenuFactory.createMenuItem("Open");
    open.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        mainGui.show();
      }
    });
    popupMenu.add(open);
  }

  public void setPopupMenuFactory(PopupMenuFactory popupMenuFactory) {
    this.popupMenuFactory = popupMenuFactory;
  }

  public void setShutdownService(ShutdownService shutdownService) {
    this.shutdownService = shutdownService;
  }

}
