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

  private Button refreshButton;

  private UIManagerUtils uiManagerUtils = new UIManagerUtils();

  private JLabel buildResultLabel;
  private JLabel buildNumberLabel;
  private JLabel buildUrlLabel;
  private JLabel buildCommittersLabel;

  public CILampGui() {
    initialize();
  }

  public void initialize() {
    app.setSize(300, 300);
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

    buildStatusPanel.setLayout(new GridLayout(5, 1));
    buildStatusPanel.setBorder(BorderFactory
        .createTitledBorder("Last Completed Build"));

    drawBuildResultLabel(buildStatusPanel);
    drawBuildNumberLabel(buildStatusPanel);
    drawBuildUrlLabel(buildStatusPanel);
    drawBuildCommittersLabel(buildStatusPanel);
    addRefreshButton(buildStatusPanel);

  }

  private void drawBuildUrlLabel(JPanel buildStatusPanel) {
    JPanel lastBuildUrlPanel = panelFactory.createPanel();
    lastBuildUrlPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    buildStatusPanel.add(lastBuildUrlPanel);
    JLabel buildUrlTitle = labelFactory.createLabel();
    buildUrlTitle.setText("Url:");
    buildUrlLabel = labelFactory.createLabel();
    buildUrlLabel.setText("");
    lastBuildUrlPanel.add(buildUrlTitle);
    lastBuildUrlPanel.add(buildUrlLabel);
  }

  private void drawBuildCommittersLabel(JPanel buildStatusPanel) {
    JPanel lastBuildCommittersPanel = panelFactory.createPanel();
    lastBuildCommittersPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    buildStatusPanel.add(lastBuildCommittersPanel);
    JLabel buildCommittersTitle = labelFactory.createLabel();
    buildCommittersTitle.setText("Committers:");
    buildCommittersLabel = labelFactory.createLabel();
    buildCommittersLabel.setText("");
    lastBuildCommittersPanel.add(buildCommittersTitle);
    lastBuildCommittersPanel.add(buildCommittersLabel);
  }

  private void drawBuildNumberLabel(JPanel buildStatusPanel) {
    JPanel lastBuildNumberPanel = panelFactory.createPanel();
    lastBuildNumberPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    buildStatusPanel.add(lastBuildNumberPanel);
    JLabel buildNumberTitle = labelFactory.createLabel();
    buildNumberTitle.setText("Number:");
    buildNumberLabel = labelFactory.createLabel();
    buildNumberLabel.setText("");
    lastBuildNumberPanel.add(buildNumberTitle);
    lastBuildNumberPanel.add(buildNumberLabel);
  }

  private JPanel drawBuildResultLabel(JPanel buildStatusPanel) {
    JPanel lastBuildResultPanel = panelFactory.createPanel();
    lastBuildResultPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    buildStatusPanel.add(lastBuildResultPanel);
    JLabel buildResultTitle = labelFactory.createLabel();
    buildResultTitle.setText("Result:");
    buildResultLabel = labelFactory.createLabel();
    buildResultLabel.setText("");
    lastBuildResultPanel.add(buildResultTitle);
    lastBuildResultPanel.add(buildResultLabel);
    return lastBuildResultPanel;
  }

  private void addRefreshButton(JPanel buildStatusPanel) {
    JPanel refreshButtonPanel = panelFactory.createPanel();
    refreshButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    buildStatusPanel.add(refreshButtonPanel);
    refreshButton = new Button("REFRESH");
    refreshButtonPanel.add(refreshButton);
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

  @Override
  public Button getRefreshButton() {
    return refreshButton;
  }

  @Override
  public void setBuildResult(String result) {
    buildResultLabel.setText(result);
  }

  @Override
  public void setBuildNumber(String number) {
    // TODO Auto-generated method stub
    throw new RuntimeException("TODO");
  }

  @Override
  public void setBuildUrl(String url) {
    // TODO Auto-generated method stub
    throw new RuntimeException("TODO");
  }

  @Override
  public void setBuildCommitters(String committers) {
    // TODO Auto-generated method stub
    throw new RuntimeException("TODO");
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

  public JLabel getBuildResultLabel() {
    return buildResultLabel;
  }

  public JLabel getBuildNumberLabel() {
    return buildNumberLabel;
  }

  public JLabel getBuildUrlLabel() {
    return buildUrlLabel;
  }

  public JLabel getBuildCommittersLabel() {
    return buildCommittersLabel;
  }

}
