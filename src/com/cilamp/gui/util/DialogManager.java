package com.cilamp.gui.util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class DialogManager {

  public void showMessageDialog(Component parentComponent, String message) {
    JOptionPane.showMessageDialog(parentComponent, message);
  }

}
