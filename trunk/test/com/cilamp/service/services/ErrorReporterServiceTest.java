package com.cilamp.service.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Component;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.event.ErrorEvent;
import com.cilamp.gui.app.CILampGuiPresenter.View;
import com.cilamp.gui.tray.CILampTrayService;
import com.cilamp.gui.util.DialogManager;

public class ErrorReporterServiceTest {

  private ErrorReporterService service = new ErrorReporterService();

  @Mock
  private DialogManager dialogManager;

  @Mock
  private View view;

  @Mock
  private CILampTrayService trayService;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    service.setDialogManager(dialogManager);
    service.initialize(view, trayService);
    mockView();
    mockTrayService();
  }

  @Test
  public void showAlertWhenViewIsVisible() {
    viewIsVisible();
    ErrorEvent testError = new ErrorEvent(new Exception(
        "There was a test error"));

    service.onError(testError);

    verify(dialogManager)
        .showMessageDialog(
            view.getParentComponent(),
            "Following error occured, view the log file for more details:\nThere was a test error");
  }

  @Test
  public void showTrayMessageWhenViewIsNotVisible() {
    viewIsNotVisible();
    ErrorEvent testError = new ErrorEvent(new Exception(
        "There was a test error"));

    service.onError(testError);

    verify(trayService.getTrayIcon()).displayMessage("Error occured",
        "There was a test error", MessageType.ERROR);
  }

  private void viewIsNotVisible() {
    when(view.isShown()).thenReturn(false);
  }

  private void viewIsVisible() {
    when(view.isShown()).thenReturn(true);
  }

  private void mockView() {
    Component parentComponent = mock(Component.class);
    when(view.getParentComponent()).thenReturn(parentComponent);
  }

  private void mockTrayService() {
    TrayIcon trayIcon = mock(TrayIcon.class);
    when(trayService.getTrayIcon()).thenReturn(trayIcon);
  }

}
