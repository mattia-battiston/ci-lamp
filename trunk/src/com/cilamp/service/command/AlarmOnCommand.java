package com.cilamp.service.command;

import com.cilamp.serial.SerialPortInterface;

public class AlarmOnCommand {

  public void execute() {
    String command = "A1";
    SerialPortInterface.getInstance().sendCommand(command);
  }
}
