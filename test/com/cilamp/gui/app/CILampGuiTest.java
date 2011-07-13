package com.cilamp.gui.app;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Button;
import java.awt.Container;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.gui.factory.LabelFactory;
import com.cilamp.gui.factory.PanelFactory;
import com.cilamp.gui.util.UIManagerUtils;

public class CILampGuiTest {

  private CILampGui ciLampGui = new CILampGui();

  @Mock
  private JFrame app;

  @Mock
  private Container mainPanel;

  @Mock
  private JLabel logoLabel;

  @Mock
  private JPanel actionsPanel;

  @Mock
  private JPanel buildStatusPanel;

  private JLabel buildResultLabel;

  private JLabel buildNumberLabel;

  private JLabel buildUrlLabel;

  private JLabel buildCommittersLabel;

  private ArgumentCaptor<JPanel> panelCaptor;

  private ArgumentCaptor<JLabel> labelCaptor;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    mockMainPanel();
    mockLookAndFeel();
    mockPanelFactory();
    mockLabelFactory();
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
  public void putLogoStringAtTheTop() {
    initializeCiLampGui();

    verify(logoLabel).setText(contains("Continuous Integration Lamp"));
  }

  @Test
  public void logoStringIsBold() {
    initializeCiLampGui();

    verify(logoLabel).setText(startsWith("<html><b>"));
  }

  @Test
  public void actionPanelIsTitled() {
    initializeCiLampGui();

    verify(actionsPanel).setBorder(any(TitledBorder.class));
  }

  @Test
  public void alarmOnIsDrawn() {
    initializeCiLampGui();

    assertNotNull(getButtonAddedToPanel("Alarm ON"));
  }

  @Test
  public void alarmOffIsDrawn() {
    initializeCiLampGui();

    assertNotNull(getButtonAddedToPanel("Alarm OFF"));
  }

  @Test
  public void buildStatusIsTitled() {
    initializeCiLampGui();

    verify(buildStatusPanel).setBorder(any(TitledBorder.class));
  }

  @Test
  public void buildResultLabelIsInitialized() {
    initializeCiLampGui();

    verify(buildResultLabel).setText("");
  }

  @Test
  public void buildNumberLabelIsInitialized() {
    initializeCiLampGui();

    verify(buildNumberLabel).setText("");
  }

  @Test
  public void buildUrlLabelIsInitialized() {
    initializeCiLampGui();

    verify(buildUrlLabel).setText("");
  }

  @Test
  public void buildCommittersLabelIsInitialized() {
    initializeCiLampGui();

    verify(buildCommittersLabel).setText("");
  }

  private void initializeCiLampGui() {
    ciLampGui.initialize();

    List<JPanel> allPanelsAddedToMainPanel = captureMainPanels();
    JPanel northPanel = allPanelsAddedToMainPanel.get(0);
    JPanel centerPanel = allPanelsAddedToMainPanel.get(1);

    captureLogoLabel(northPanel);
    captureCenterPanels(centerPanel);
    captureBuildResultLabels();
  }

  private void captureBuildResultLabels() {
    buildResultLabel = ciLampGui.getBuildResultLabel();
    buildNumberLabel = ciLampGui.getBuildNumberLabel();
    buildUrlLabel = ciLampGui.getBuildUrlLabel();
    buildCommittersLabel = ciLampGui.getBuildCommittersLabel();
  }

  private void captureCenterPanels(JPanel centerPanel) {
    panelCaptor = ArgumentCaptor.forClass(JPanel.class);
    verify(centerPanel, times(2)).add(panelCaptor.capture(), anyString());
    List<JPanel> allPanelsAddedToCenterPanel = panelCaptor.getAllValues();
    actionsPanel = allPanelsAddedToCenterPanel.get(0);
    buildStatusPanel = allPanelsAddedToCenterPanel.get(1);
  }

  private void captureLogoLabel(JPanel northPanel) {
    ArgumentCaptor<JLabel> labelCaptor = ArgumentCaptor.forClass(JLabel.class);
    verify(northPanel).add(labelCaptor.capture());
    logoLabel = labelCaptor.getValue();
  }

  private List<JPanel> captureMainPanels() {
    ArgumentCaptor<JPanel> panelCaptor = ArgumentCaptor.forClass(JPanel.class);
    verify(mainPanel, times(2)).add(panelCaptor.capture(), anyString());
    List<JPanel> allPanelsAddedToMainPanel = panelCaptor.getAllValues();
    return allPanelsAddedToMainPanel;
  }

  private Button getButtonAddedToPanel(String label) {
    ArgumentCaptor<Button> buttonsCaptor = ArgumentCaptor
        .forClass(Button.class);
    verify(actionsPanel, atLeastOnce()).add(buttonsCaptor.capture());
    List<Button> buttonsAddedToPanel = buttonsCaptor.getAllValues();
    for (Button button : buttonsAddedToPanel) {
      if (label.equals(button.getLabel()))
        return button;
    }
    fail("Button with label " + label + " has not been added");
    return null;
  }

  private void mockPanelFactory() {
    PanelFactory panelFactory = new PanelFactory() {
      @Override
      public JPanel createPanel() {
        return mock(JPanel.class);
      }
    };
    ciLampGui.setPanelFactory(panelFactory);
  }

  private void mockLabelFactory() {
    LabelFactory labelFactory = new LabelFactory() {
      @Override
      public JLabel createLabel() {
        return mock(JLabel.class);
      }
    };
    ciLampGui.setLabelFactory(labelFactory);
  }

  private UIManagerUtils mockLookAndFeel() {
    UIManagerUtils uiManagerUtils = mock(UIManagerUtils.class);
    ciLampGui.setUiManagerUtils(uiManagerUtils);
    return uiManagerUtils;
  }

  private void mockMainPanel() {
    when(app.getContentPane()).thenReturn(mainPanel);
  }

}
