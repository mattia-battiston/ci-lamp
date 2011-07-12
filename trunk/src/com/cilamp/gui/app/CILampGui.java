package com.cilamp.gui.app;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cilamp.gui.factory.LabelFactory;
import com.cilamp.gui.factory.PanelFactory;
import com.cilamp.gui.util.UIManagerUtils;

public class CILampGui implements CILampGuiPresenter.View {

  private JFrame app = new JFrame();

  private PanelFactory panelFactory = new PanelFactory();

  private LabelFactory labelFactory = new LabelFactory();

  private Button alarmOnButton;

  private Button alarmOffButton;

  private UIManagerUtils uiManagerUtils = new UIManagerUtils();

  public CILampGui() {
    initialize();
  }

  public void initialize() {
    app.setSize(300, 200);
    app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    uiManagerUtils.setSystemLookAndFeel();

    drawGui();
    hide();
  }

  private void drawGui() {
    Container mainPanel = app.getContentPane();
    mainPanel.setLayout(new BorderLayout());
    JPanel northPanel = panelFactory.createPanel();
    JPanel centerPanel = panelFactory.createPanel();
    mainPanel.add(northPanel, BorderLayout.NORTH);
    mainPanel.add(centerPanel, BorderLayout.CENTER);

    JLabel appTitle = labelFactory.createLabel();
    appTitle.setText("<html><b>Continuous Integration Lamp");
    northPanel.add(appTitle);

    centerPanel.setLayout(new BorderLayout());
    JPanel actionsPanel = panelFactory.createPanel();
    JPanel buildStatusPanel = panelFactory.createPanel();
    centerPanel.add(actionsPanel, BorderLayout.NORTH);
    centerPanel.add(buildStatusPanel, BorderLayout.CENTER);

    actionsPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
    addAlarmOnButton(actionsPanel);
    addAlarmOffButton(actionsPanel);

    buildStatusPanel.setLayout(new GridLayout(1, 1));
    buildStatusPanel.setBorder(BorderFactory
        .createTitledBorder("Last Completed Build"));

    JPanel lastBuildResultPanel = panelFactory.createPanel();
    lastBuildResultPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel lastBuildResultTitle = labelFactory.createLabel();
    lastBuildResultTitle.setText("Result:");
    JLabel lastBuildResultValue = labelFactory.createLabel();
    lastBuildResultValue.setText("");
    lastBuildResultPanel.add(lastBuildResultTitle);
    lastBuildResultPanel.add(lastBuildResultValue);
    buildStatusPanel.add(lastBuildResultPanel);
  }

  private void addAlarmOnButton(JPanel content) {
    alarmOnButton = new Button("Alarm ON");
    content.add(alarmOnButton);
  }

  private void addAlarmOffButton(JPanel content) {
    alarmOffButton = new Button("Alarm OFF");
    content.add(alarmOffButton);
  }

  @Override
  public void show() {
    app.setVisible(true);
  }

  @Override
  public void hide() {
    app.setVisible(false);
  }

  @Override
  public Button getAlarmOnButton() {
    return alarmOnButton;
  }

  @Override
  public Button getAlarmOffButton() {
    return alarmOffButton;
  }

  public void setApp(JFrame app) {
    this.app = app;
  }

  public void setPanelFactory(PanelFactory panelFactory) {
    this.panelFactory = panelFactory;
  }

  public void setUiManagerUtils(UIManagerUtils uiManagerUtils) {
    this.uiManagerUtils = uiManagerUtils;
  }

  public void setLabelFactory(LabelFactory labelFactory) {
    this.labelFactory = labelFactory;
  }

}
