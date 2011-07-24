package com.cilamp.serial;

import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.cilamp.service.services.PropertiesService;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ CommPortIdentifier.class })
public class SerialPortInterfaceTest {

  @Mock
  private CommPortIdentifier commPortIdentifier;

  @Mock
  private SerialPort serialPort;

  @Before
  public void before() throws NoSuchPortException, PortInUseException {
    MockitoAnnotations.initMocks(this);

    mockSerialPort();
    mockPortName("COM1");
  }

  @Test
  public void exceptionWhenPortNotFound() throws NoSuchPortException {
    setPortNonExisting();

    try {
      new SerialPortInterface();
      fail("expected exception");
    } catch (RuntimeException e) {
      assertThat(e.getCause().getMessage(), containsString("is not available"));
    }
  }

  @Test
  public void exceptionWhenPortIsNotSerial() throws NoSuchPortException {
    setPortNonSerial();

    try {
      new SerialPortInterface();
      fail("expected exception");
    } catch (RuntimeException e) {
      assertThat(e.getCause().getMessage(),
          containsString("is not a serial port"));
    }
  }

  @Test
  public void exceptionWhenPortIsInUse() {
    setPortInUse();

    try {
      new SerialPortInterface();
      fail("expected exception");
    } catch (RuntimeException e) {
      assertThat(e.getCause().getMessage(),
          containsString("Port is currently in use"));
    }
  }

  @Test
  public void opensCorrectSerialPort() throws NoSuchPortException {
    mockPortName("COM9");

    new SerialPortInterface();

    PowerMockito.verifyStatic();
    CommPortIdentifier.getPortIdentifier("COM9");
  }

  @Test
  public void opensSerialPortWithRightName() throws PortInUseException {
    new SerialPortInterface();

    verify(commPortIdentifier).open(eq("CILamp"), anyInt());
  }

  @Test
  public void initializesOutputStream() throws IOException {
    OutputStream outputStream = mock(OutputStream.class);
    setOutputStreamToSerialPort(outputStream);

    SerialPortInterface serialPortInterface = new SerialPortInterface();

    assertThat(serialPortInterface.getSerialPortOutputStream(),
        is(outputStream));
  }

  @Test
  public void sendCommandSendsBytesToSerialPort() throws IOException {
    SerialPortInterface serialPortInterface = new SerialPortInterface();
    OutputStream outputStream = mock(OutputStream.class);
    serialPortInterface.setSerialPortOutputStream(outputStream);

    serialPortInterface.sendCommand("COMMAND_TEST");

    verify(outputStream).write("COMMAND_TEST".getBytes());
  }

  private void setOutputStreamToSerialPort(OutputStream outputStream)
      throws IOException {
    when(serialPort.getOutputStream()).thenReturn(outputStream);
  }

  private void setPortInUse() {
    when(commPortIdentifier.isCurrentlyOwned()).thenReturn(true);
  }

  private void setPortNonSerial() {
    when(commPortIdentifier.getPortType()).thenReturn(
        CommPortIdentifier.PORT_PARALLEL);
  }

  private void setPortNonExisting() throws NoSuchPortException {
    when(CommPortIdentifier.getPortIdentifier(anyString())).thenReturn(null);
  }

  private void mockSerialPort() throws NoSuchPortException, PortInUseException {
    PowerMockito.mockStatic(CommPortIdentifier.class);
    when(CommPortIdentifier.getPortIdentifier(anyString())).thenReturn(
        commPortIdentifier);
    when(commPortIdentifier.getPortType()).thenReturn(
        CommPortIdentifier.PORT_SERIAL);
    when(commPortIdentifier.isCurrentlyOwned()).thenReturn(false);
    when(commPortIdentifier.open(anyString(), anyInt())).thenReturn(serialPort);
  }

  private void mockPortName(String portName) {
    PropertiesService propertiesService = mock(PropertiesService.class);
    when(propertiesService.getSerialPortName()).thenReturn(portName);
    SerialPortInterface.setPropertiesService(propertiesService);
  }
}
