package com.cilamp.gui.app;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CILampGuiTest {

  private CILampGui ciLampGui = new CILampGui();

  @Mock
  private JFrame app;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    ciLampGui.setApp(app);
  }

  @Test
  public void someSizeIsGivenToTheAppWhenInitializing() {
    ciLampGui.initialize();

    verify(app).setSize(anyInt(), anyInt());
  }

  @Test
  public void appIsDisposedWhenClosing() {
    ciLampGui.initialize();

    verify(app).setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  @Test
  public void appIsNotVisibleByDefault() {
    ciLampGui.initialize();

    verify(app).setVisible(false);
  }

  @Test
  public void showMakesTheAppVisible() {
    ciLampGui.show();

    verify(app).setVisible(true);
  }

  @Test
  public void hideMakesTheAppNotVisible() {
    ciLampGui.hide();

    verify(app).setVisible(false);
  }
}
