package com.cilamp.servial;

import org.junit.Ignore;
import org.junit.Test;

import com.cilamp.serial.SerialPortInterface;
import com.cilamp.serial.SerialPortInterfaceProvider;

public class SerialPortInterfaceIntegrationTest {

  private SerialPortInterface serialPortInterface = new SerialPortInterfaceProvider()
      .getSerialPortInterface();

  @Test
  public void turnAlarmOn() {
    serialPortInterface.sendCommand("TURN_ALARM_ON");
  }

  @Ignore
  @Test
  public void turnAlarmOff() {
    serialPortInterface.sendCommand("TURN_ALARM_OFF");
  }

}
