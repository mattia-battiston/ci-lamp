package com.cilamp.gui.app;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Button;
import java.awt.Container;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.gui.factory.PanelFactory;
import com.cilamp.gui.util.UIManagerUtils;

public class CILampGuiTest {

  private CILampGui ciLampGui = new CILampGui();

  @Mock
  private JFrame app;

  @Mock
  private JPanel contentPanel;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    mockAppContent();
    mockLookAndFeel();
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
  public void setsDefaultSystemLookAndFeel() {
    UIManagerUtils mockLookAndFeel = mockLookAndFeel();

    ciLampGui.initialize();

    verify(mockLookAndFeel).setSystemLookAndFeel();
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

  @Test
  public void alarmOnIsDrawn() {
    ciLampGui.initialize();

    assertNotNull(getButtonAddedToPanel("Alarm ON"));
  }

  @Test
  public void alarmOffIsDrawn() {
    ciLampGui.initialize();

    assertNotNull(getButtonAddedToPanel("Alarm OFF"));
  }

  private Button getButtonAddedToPanel(String label) {
    ArgumentCaptor<Button> buttonsCaptor = ArgumentCaptor
        .forClass(Button.class);
    verify(contentPanel, atLeastOnce()).add(buttonsCaptor.capture());
    List<Button> buttonsAddedToPanel = buttonsCaptor.getAllValues();
    for (Button button : buttonsAddedToPanel) {
      if (label.equals(button.getLabel()))
        return button;
    }
    fail("Button with label " + label + " has not been added");
    return null;
  }

  private Container mockAppContent() {
    PanelFactory panelFactory = mock(PanelFactory.class);
    when(panelFactory.createPanel()).thenReturn(contentPanel);
    ciLampGui.setPanelFactory(panelFactory);

    Container mainContainer = mock(Container.class);
    when(app.getContentPane()).thenReturn(mainContainer);
    return mainContainer;
  }

  private UIManagerUtils mockLookAndFeel() {
    UIManagerUtils uiManagerUtils = mock(UIManagerUtils.class);
    ciLampGui.setUiManagerUtils(uiManagerUtils);
    return uiManagerUtils;
  }

}
