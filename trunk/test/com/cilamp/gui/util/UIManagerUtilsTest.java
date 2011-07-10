package com.cilamp.gui.util;

import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ UIManager.class })
public class UIManagerUtilsTest {

  private UIManagerUtils uiManagerUtils = new UIManagerUtils();

  @Before
  public void before() throws NoSuchPortException, PortInUseException {
    MockitoAnnotations.initMocks(this);

    PowerMockito.mockStatic(UIManager.class);
  }

  @Test
  public void setsSystemLookAndFeel() throws ClassNotFoundException,
      InstantiationException, IllegalAccessException,
      UnsupportedLookAndFeelException {
    setLookAndFeelName("CoolLookAndFeel");

    uiManagerUtils.setSystemLookAndFeel();

    PowerMockito.verifyStatic();
    UIManager.setLookAndFeel("CoolLookAndFeel");
  }

  @Test
  public void rethrowsExceptions() throws ClassNotFoundException,
      InstantiationException, IllegalAccessException,
      UnsupportedLookAndFeelException {
    lookAndFeelIsNotSupported();

    try {
      uiManagerUtils.setSystemLookAndFeel();
      fail("expected exception");
    } catch (RuntimeException e) {
      assertThat(e.getMessage(), is("Test"));
    }
  }

  private void lookAndFeelIsNotSupported()
      throws ClassNotFoundException, InstantiationException,
      IllegalAccessException, UnsupportedLookAndFeelException {
    PowerMockito.doThrow(new UnsupportedLookAndFeelException("Test")).when(
        UIManager.class);
    UIManager.setLookAndFeel(anyString());
  }

  private void setLookAndFeelName(String lookAndFeelName) {
    when(UIManager.getSystemLookAndFeelClassName()).thenReturn(lookAndFeelName);
  }

}
