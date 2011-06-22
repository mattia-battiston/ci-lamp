package com.cilamp.serial;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.OutputStream;

public class SerialPortInterface {

  private static SerialPortInterface instance;

  static SerialPortInterface getInstance() {
    if (instance == null) {
      instance = new SerialPortInterface();
    }
    return instance;
  }

  private SerialPortInterface() {
    try {
      initialize();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  // TODO get this from properties file
  private static final String SERIAL_PORT_NAME = "COM3";
  private OutputStream serialPortOutputStream;

  public void initialize() throws Exception {
    CommPortIdentifier commPortIdentifier = CommPortIdentifier
        .getPortIdentifier(SERIAL_PORT_NAME);

    if (commPortIdentifier == null) {
      throw new RuntimeException("Serial port " + SERIAL_PORT_NAME
          + " is not available");
    }
    if (commPortIdentifier.getPortType() != CommPortIdentifier.PORT_SERIAL) {
      throw new RuntimeException(SERIAL_PORT_NAME + " is not a serial port");
    }
    if (commPortIdentifier.isCurrentlyOwned()) {
      throw new RuntimeException("Port is currently in use");
    }

    System.out.println("Found port COM1");

    SerialPort serialPort = (SerialPort) commPortIdentifier
        .open("CILamp", 2000);
    // TODO understand these parameters
    serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
        SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
    serialPortOutputStream = serialPort.getOutputStream();
  }

  public void sendCommand(String command) {
    try {
      serialPortOutputStream.write(command.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

}
