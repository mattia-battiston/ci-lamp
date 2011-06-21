package com.cilamp.gui.tray;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.gui.app.CILampGui;
import com.cilamp.gui.factory.PopupMenuFactory;
import com.cilamp.service.command.ShutdownService;

public class CILampTrayMenuTest {

  private CILampTrayMenu menu = new CILampTrayMenu();

  @Mock
  private CILampGui mainGui;

  @Mock
  private PopupMenuFactory menuFactory;

  @Mock
  private ShutdownService shutdownService;

  @Mock
  private MenuItem exitMenuItem;

  @Mock
  private MenuItem openMenuItem;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    menu.setPopupMenuFactory(menuFactory);
    mockPopupMenuFactory();
    mockMenuItemFactory();
    menu.setShutdownService(shutdownService);
  }

  @Test
  public void menuHasExitItem() {
    PopupMenu popupMenu = menu.init(mainGui);

    verify(popupMenu).add(exitMenuItem);
  }

  @Test
  public void menuHasOpenItem() {
    PopupMenu popupMenu = menu.init(mainGui);

    verify(popupMenu).add(openMenuItem);
  }

  @Test
  public void exitStopsTheApplication() {
    menu.init(mainGui);

    ActionListener exitActionListener = getActionListenerJustAddedTo(exitMenuItem);
    exitActionListener.actionPerformed(null);

    verify(shutdownService).shutdown();
  }

  @Test
  public void openShowsTheMainGui() {
    menu.init(mainGui);

    ActionListener openActionListener = getActionListenerJustAddedTo(openMenuItem);
    openActionListener.actionPerformed(null);

    verify(mainGui).show();
  }

  private ActionListener getActionListenerJustAddedTo(MenuItem menuItem) {
    ArgumentCaptor<ActionListener> actionListenerCaptor = ArgumentCaptor
        .forClass(ActionListener.class);
    verify(menuItem).addActionListener(actionListenerCaptor.capture());
    return actionListenerCaptor.getValue();
  }

  private void mockPopupMenuFactory() {
    PopupMenu popupMenu = mock(PopupMenu.class);
    when(menuFactory.createPopupMenu()).thenReturn(popupMenu);
  }

  private void mockMenuItemFactory() {
    mockOpenMenuItem();
    mockExitMenuItem();
  }

  private void mockOpenMenuItem() {
    when(menuFactory.createMenuItem("Open")).thenReturn(openMenuItem);
  }

  private void mockExitMenuItem() {
    when(menuFactory.createMenuItem("Exit")).thenReturn(exitMenuItem);
  }

}
