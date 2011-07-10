package com.cilamp;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GuiSpike extends JFrame {

  private static final long serialVersionUID = -8849927121852293707L;

  public GuiSpike() throws ClassNotFoundException, InstantiationException,
      IllegalAccessException, UnsupportedLookAndFeelException {
    super("Gui Spike");
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setSize(300, 200);

    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    Container mainPanel = this.getContentPane();
    mainPanel.setLayout(new BorderLayout());
    JPanel northPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    mainPanel.add(northPanel, BorderLayout.NORTH);
    mainPanel.add(centerPanel, BorderLayout.CENTER);

    JLabel appTitle = new JLabel("<html><b>Continuous Integration Lamp");
    northPanel.add(appTitle);

    centerPanel.setLayout(new BorderLayout());
    JPanel actionsPanel = new JPanel();
    JPanel buildStatusPanel = new JPanel();
    centerPanel.add(actionsPanel, BorderLayout.NORTH);
    centerPanel.add(buildStatusPanel, BorderLayout.CENTER);

    actionsPanel.setBorder(BorderFactory.createTitledBorder("Actions"));
    actionsPanel.add(new JButton("Alarm ON"), BorderLayout.NORTH);
    actionsPanel.add(new JButton("Alarm OFF"));

    buildStatusPanel.setLayout(new GridLayout(1, 1));
    buildStatusPanel
        .setBorder(BorderFactory.createTitledBorder("Build Status"));
    JLabel lastBuild = new JLabel("Last build: Successfull");
    lastBuild.setVerticalAlignment(JLabel.TOP);
    buildStatusPanel.add(lastBuild);

    this.setVisible(true);
  }

  public static void main(String args[]) throws ClassNotFoundException,
      InstantiationException, IllegalAccessException,
      UnsupportedLookAndFeelException {
    new GuiSpike();
  }

}
