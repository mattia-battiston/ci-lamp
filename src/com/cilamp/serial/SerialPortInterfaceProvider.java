package com.cilamp.serial;

public class SerialPortInterfaceProvider {

  public SerialPortInterface getSerialPortInterface() {
    return SerialPortInterface.getInstance();
  }

}
