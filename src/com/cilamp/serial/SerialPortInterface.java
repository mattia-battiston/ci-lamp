package com.cilamp.serial;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerialPortInterface {

  private static final int BIT_RATE = 9600;
  private static final int DATABITS = SerialPort.DATABITS_8;
  private static final int STOPBITS = SerialPort.STOPBITS_1;
  private static final int PARITY = SerialPort.PARITY_NONE;

  final Logger log = LoggerFactory.getLogger(SerialPortInterface.class);

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
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  // TODO get this from properties file
  private static final String SERIAL_PORT_NAME = "COM3";
  private OutputStream serialPortOutputStream;

  public void initialize() throws Exception {
    log.info("initializing communication with serial port {}", SERIAL_PORT_NAME);
    SerialPort serialPort = retrieveSerialPort();
    serialPortOutputStream = serialPort.getOutputStream();
    log.info("communication with serial port has been initialized");
  }

  private SerialPort retrieveSerialPort() throws NoSuchPortException,
      PortInUseException, UnsupportedCommOperationException {
    log.info("looking for serial port {}", SERIAL_PORT_NAME);
    CommPortIdentifier commPortIdentifier = CommPortIdentifier
        .getPortIdentifier(SERIAL_PORT_NAME);
    ensurePortIsAvailable(commPortIdentifier);
    log.info("serial port {} found and available, opening communication",
        SERIAL_PORT_NAME);

    SerialPort serialPort = (SerialPort) commPortIdentifier
        .open("CILamp", 2000);
    serialPort.setSerialPortParams(BIT_RATE, DATABITS, STOPBITS, PARITY);
    log.info("communication opened");
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
      log.info("sending command to port {}: [{}]", SERIAL_PORT_NAME, command);
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
