package com.cilamp.serial;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

public class SerialPortInterface {

  private static SerialPortInterface instance;

  public static SerialPortInterface getInstance() {
    if (instance == null) {
      instance = new SerialPortInterface();
    }
    return instance;
  }

  private SerialPortInterface() {
    initialize();
  }

  // TODO get this from properties file
  private static final String SERIAL_PORT_NAME = "COM1";
  private OutputStream serialPortOutputStream;

  public void initialize() {
    CommPortIdentifier commPortIdentifier = getSerialPort();
    System.out.println("Found port COM1");

    CommPort genericPort;
    try {
      genericPort = commPortIdentifier.open("MattiaTestApp", 2000);
      SerialPort serialPort = (SerialPort) genericPort;
      // TODO understand these parameters
      serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
          SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
      serialPortOutputStream = serialPort.getOutputStream();

    } catch (PortInUseException e) {
      e.printStackTrace();
    } catch (UnsupportedCommOperationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("rawtypes")
  private CommPortIdentifier getSerialPort() {
    Enumeration portList = CommPortIdentifier.getPortIdentifiers();

    while (portList.hasMoreElements()) {
      CommPortIdentifier port = (CommPortIdentifier) portList.nextElement();
      if (port.getPortType() == CommPortIdentifier.PORT_SERIAL
          && port.getName().equals(SERIAL_PORT_NAME))
        return port;
    }

    throw new RuntimeException("Serial port " + SERIAL_PORT_NAME
        + " is not available");
  }

  public void sendCommand(String command) {
    try {
      serialPortOutputStream.write(command.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

}
