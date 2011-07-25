package com.cilamp.serial;

import org.junit.Ignore;
import org.junit.Test;

public class SerialPortInterfaceIntegrationTest {

  private SerialPortInterface serialPortInterface = new SerialPortInterfaceProvider()
      .getSerialPortInterface();

  @Test
  public void buildFailed() {
    serialPortInterface.sendCommand("TURN_ALARM_ON");
  }

  @Ignore
  @Test
  public void buildSucceeded() {
    serialPortInterface.sendCommand("TURN_ALARM_OFF");
  }

}
