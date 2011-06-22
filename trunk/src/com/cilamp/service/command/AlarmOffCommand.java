package com.cilamp.service.command;

import com.cilamp.serial.SerialPortInterfaceProvider;

public class AlarmOffCommand {

  private SerialPortInterfaceProvider serialPortInterfaceProvider = new SerialPortInterfaceProvider();

  public void execute() {
    String command = "TURN_ALARM_OFF";
    serialPortInterfaceProvider.getSerialPortInterface().sendCommand(command);
  }

  public void setSerialPortInterfaceProvider(
      SerialPortInterfaceProvider serialPortInterfaceProvider) {
    this.serialPortInterfaceProvider = serialPortInterfaceProvider;
  }

}
