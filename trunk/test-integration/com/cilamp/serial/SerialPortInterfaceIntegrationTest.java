package com.cilamp.serial;

import org.junit.Ignore;
import org.junit.Test;

public class SerialPortInterfaceIntegrationTest {

  private SerialPortInterface serialPortInterface = new SerialPortInterfaceProvider()
      .getSerialPortInterface();

  @Test
  public void buildFailed() {
    serialPortInterface.sendCommand("BUILD_FAILED");
  }

  @Ignore
  @Test
  public void buildSucceeded() {
    serialPortInterface.sendCommand("BUILD_SUCCEEDED");
  }

}
