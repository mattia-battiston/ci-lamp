package com.cilamp.serial;

import org.junit.Test;

public class SerialPortInterfaceIntegrationTest {

  private SerialPortInterface serialPortInterface = new SerialPortInterfaceProvider()
      .getSerialPortInterface();

  @Test
  public void buildFailed() {
    serialPortInterface.sendCommand("BUILD_FAILED");
  }

  @Test
  public void buildSucceeded() throws InterruptedException {
    serialPortInterface.sendCommand("BUILD_SUCCEEDED");
    Thread.sleep(1000L);
  }

}
