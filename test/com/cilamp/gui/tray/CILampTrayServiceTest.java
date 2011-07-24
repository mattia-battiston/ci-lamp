package com.cilamp.gui.tray;

import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.event.base.EventBus;
import com.cilamp.gui.app.CILampGuiPresenter;
import com.cilamp.gui.factory.SystemTrayFactory;
import com.cilamp.gui.factory.TrayIconFactory;

public class CILampTrayServiceTest {

  private CILampTrayService service = new CILampTrayService();

  @Mock
  private SystemTrayFactory systemTrayFactory;

  @Mock
  private TrayIconFactory trayIconFactory;

  @Mock
  private SystemTray systemTray;

  @Mock
  private TrayIcon trayIcon;

  @Mock
  private CILampTrayMenu trayMenu;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    mockFactories();
    mockMenu();
    systemTrayIsSupported();
  }

  @Test(expected = RuntimeException.class)
  public void exceptionIfSystemTrayIsNotSupported() {
    systemTrayIsNotSupported();

    service.init();
  }

  @Test
  public void addsIconToTheSystemTray() throws AWTException {
    service.init();

    verify(systemTray).add(trayIcon);
  }

  @Test
  public void iconHasATooltip() throws AWTException {
    service.init();

    verify(trayIcon).setToolTip(anyString());
  }

  @Test
  public void iconIsAutosizable() throws AWTException {
    service.init();

    verify(trayIcon).setImageAutoSize(true);
  }

  @Test
  public void openMenuOnRightClick() {
    service.init();

    verify(trayMenu).init(any(CILampGuiPresenter.class), any(EventBus.class));
    verify(trayIcon).setPopupMenu(any(PopupMenu.class));
  }

  @Test
  public void showMainClickOnClick() {
    CILampGuiPresenter mainGui = mock(CILampGuiPresenter.class);
    service.setMainGui(mainGui);
    service.init();

    click();

    verify(mainGui).show();
  }

  @Test
  public void anyExceptionIsRethrown() throws AWTException {
    exceptionAddingTray("Test exception");

    try {
      service.init();
      fail("Expected exception");
    } catch (RuntimeException e) {
      assertThat(e.getMessage(), is("Test exception"));
    }
  }

  private void exceptionAddingTray(String message) throws AWTException {
    doThrow(new AWTException(message)).when(systemTray).add(trayIcon);
  }

  private void click() {
    ActionListener actionListener = getActionListener();
    actionListener.actionPerformed(null);
  }

  private ActionListener getActionListener() {
    ArgumentCaptor<ActionListener> actionListenerCaptor = ArgumentCaptor
        .forClass(ActionListener.class);
    verify(trayIcon).addActionListener(actionListenerCaptor.capture());
    ActionListener actionListener = actionListenerCaptor.getValue();
    return actionListener;
  }

  private void mockMenu() {
    service.setTrayMenu(trayMenu);
    when(trayMenu.init(any(CILampGuiPresenter.class), any(EventBus.class)))
        .thenReturn(new PopupMenu());
  }

  private void mockFactories() {
    service.setSystemTrayFactory(systemTrayFactory);
    when(systemTrayFactory.getSystemTray()).thenReturn(systemTray);

    service.setTrayIconFactory(trayIconFactory);
    when(trayIconFactory.createTrayIcon(any(Image.class))).thenReturn(trayIcon);
  }

  private void systemTrayIsNotSupported() {
    when(systemTrayFactory.isSystemTraySupported()).thenReturn(false);
  }

  private void systemTrayIsSupported() {
    when(systemTrayFactory.isSystemTraySupported()).thenReturn(true);
  }

}
