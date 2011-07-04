package com.cilamp.serial;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

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

  SerialPortInterface() {
    try {
      initialize();
    } catch (Exception e) {
      // TODO manage this exception, fire ErrorEvent and notify user
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  // TODO get this from properties file
  private static final String SERIAL_PORT_NAME = "COM3";
  private OutputStream serialPortOutputStream;

  public void initialize() throws Exception {
    SerialPort serialPort = retrieveSerialPort();
    serialPortOutputStream = serialPort.getOutputStream();
  }

  private SerialPort retrieveSerialPort() throws NoSuchPortException,
      PortInUseException, UnsupportedCommOperationException {
    CommPortIdentifier commPortIdentifier = CommPortIdentifier
        .getPortIdentifier(SERIAL_PORT_NAME);
    ensurePortIsAvailable(commPortIdentifier);

    // TODO use real log
    System.out.println("Found port COM1");

    SerialPort serialPort = (SerialPort) commPortIdentifier
        .open("CILamp", 2000);
    // TODO understand these parameters
    serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
        SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
    return serialPort;
  }

  private void ensurePortIsAvailable(CommPortIdentifier commPortIdentifier) {
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
  }

  public void sendCommand(String command) {
    try {
      serialPortOutputStream.write(command.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public OutputStream getSerialPortOutputStream() {
    return serialPortOutputStream;
  }

  public void setSerialPortOutputStream(OutputStream serialPortOutputStream) {
    this.serialPortOutputStream = serialPortOutputStream;
  }

}
