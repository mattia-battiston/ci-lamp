package com.cilamp.service.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.serial.SerialPortInterface;
import com.cilamp.serial.SerialPortInterfaceProvider;

public class BuildFailedCommandTest {

  private BuildFailedCommand command = new BuildFailedCommand();

  @Mock
  private SerialPortInterface serialPortInterface;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    mockSerialPort();
  }

  @Test
  public void sendsCommandToSerialPort() {
    command.execute();

    verify(serialPortInterface).sendCommand("TURN_ALARM_ON");
  }

  private void mockSerialPort() {
    SerialPortInterfaceProvider serialPortInterfaceProvider = mock(SerialPortInterfaceProvider.class);
    command.setSerialPortInterfaceProvider(serialPortInterfaceProvider);
    when(serialPortInterfaceProvider.getSerialPortInterface()).thenReturn(
        serialPortInterface);
  }

}
