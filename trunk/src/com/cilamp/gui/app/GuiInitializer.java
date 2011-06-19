package com.cilamp.gui.app;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.cilamp.command.AlarmOnCommand;

public class GuiInitializer {

  public void drawGui(JPanel content) {
    content.setLayout(new FlowLayout(FlowLayout.CENTER));

    addAlarmOnButton(content);
  }

  private void addAlarmOnButton(JPanel content) {
    Button alarmOn = new Button("Alarm ON!");
    content.add(alarmOn);
    alarmOn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO what about using an EventBus?
        new AlarmOnCommand().execute();
      }
    });
  }

}
