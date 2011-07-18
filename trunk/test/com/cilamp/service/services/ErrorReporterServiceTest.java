package com.cilamp.service.services;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.gui.util.DialogManager;

public class ErrorReporterServiceTest {

  private ErrorReporterService service = new ErrorReporterService();

  @Mock
  private DialogManager dialogManager;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    service.setDialogManager(dialogManager);
  }

  @Test
  public void showAlertToNotifyErrors() {
    Exception testError = new Exception("There was a test error");

    service.notifyError(testError);

    verify(dialogManager)
        .showMessageDialog(
            "Following error occured, view the log file for more details:\nThere was a test error");
  }

}
