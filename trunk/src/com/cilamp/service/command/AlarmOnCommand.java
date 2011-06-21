package com.cilamp.service.command;

import com.cilamp.serial.SerialPortInterface;

public class AlarmOnCommand {

  // TODO use a service under services (LampService.turnAlarmOn), which might
  // dispatch to a command

  public void execute() {
    String command = "A1";
    SerialPortInterface.getInstance().sendCommand(command);
  }
}
