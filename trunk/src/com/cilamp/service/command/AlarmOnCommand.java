package com.cilamp.service.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cilamp.serial.SerialPortInterfaceProvider;

public class AlarmOnCommand {

  final Logger log = LoggerFactory.getLogger(AlarmOnCommand.class);

  private SerialPortInterfaceProvider serialPortInterfaceProvider = new SerialPortInterfaceProvider();

  public void execute() {
    log.info("Sending command TURN_ALARM_ON to serial port");
    String command = "TURN_ALARM_ON";
    serialPortInterfaceProvider.getSerialPortInterface().sendCommand(command);
  }

  public void setSerialPortInterfaceProvider(
      SerialPortInterfaceProvider serialPortInterfaceProvider) {
    this.serialPortInterfaceProvider = serialPortInterfaceProvider;
  }

}
