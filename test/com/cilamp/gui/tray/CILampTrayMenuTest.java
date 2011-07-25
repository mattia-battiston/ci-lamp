package com.cilamp.gui.tray;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doThrow;
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

import com.cilamp.event.ErrorEvent;
import com.cilamp.event.base.EventBus;
import com.cilamp.gui.app.CILampGuiPresenter;
import com.cilamp.gui.factory.PopupMenuFactory;
import com.cilamp.service.services.ShutdownService;

public class CILampTrayMenuTest {

  private CILampTrayMenu menu = new CILampTrayMenu();

  @Mock
  private CILampGuiPresenter mainGui;

  @Mock
  private PopupMenuFactory menuFactory;

  @Mock
  private ShutdownService shutdownService;

  @Mock
  private MenuItem exitMenuItem;

  @Mock
  private MenuItem openMenuItem;

  @Mock
  private EventBus eventBus;

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
    PopupMenu popupMenu = menu.init(mainGui, eventBus);

    verify(popupMenu).add(exitMenuItem);
  }

  @Test
  public void menuHasOpenItem() {
    PopupMenu popupMenu = menu.init(mainGui, eventBus);

    verify(popupMenu).add(openMenuItem);
  }

  @Test
  public void exitStopsTheApplication() {
    menu.init(mainGui, eventBus);

    ActionListener exitActionListener = getActionListenerJustAddedTo(exitMenuItem);
    exitActionListener.actionPerformed(null);

    verify(shutdownService).shutdown();
  }

  @Test
  public void exceptionsShuttingDownTheApplicationAreReported() {
    Throwable error = throwExceptionShuttingDownApplication();
    menu.init(mainGui, eventBus);

    ActionListener actionListener = getActionListenerJustAddedTo(exitMenuItem);
    actionListener.actionPerformed(null);

    ErrorEvent eventFired = errorEventIsFired();
    assertThat(eventFired.getError(), is(error));
  }

  private ErrorEvent errorEventIsFired() {
    ArgumentCaptor<ErrorEvent> eventController = ArgumentCaptor
        .forClass(ErrorEvent.class);
    verify(eventBus).fireEvent(eventController.capture());
    ErrorEvent eventFired = eventController.getValue();
    return eventFired;
  }

  private Throwable throwExceptionShuttingDownApplication() {
    RuntimeException exception = new RuntimeException("TEST");
    doThrow(exception).when(shutdownService).shutdown();
    return exception;
  }

  @Test
  public void openShowsTheMainGui() {
    menu.init(mainGui, eventBus);

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
