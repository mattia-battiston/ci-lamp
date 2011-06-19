package com.cilamp.gui.app;

import static junit.framework.Assert.fail;
import static org.mockito.Mockito.verify;

import java.awt.Button;
import java.util.List;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GuiInitializerTest {

  private GuiInitializer guiInitializer = new GuiInitializer();

  @Mock
  private JPanel content;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void drawsAlarmOnButton() {
    guiInitializer.drawGui(content);

    getButtonAdded("Alarm ON!");
  }

  private Button getButtonAdded(String label) {
    List<Button> buttonsAdded = getButtonsAdded();
    for (Button button : buttonsAdded) {
      if (label.equals(button.getLabel()))
        return button;
    }
    fail("No button has been added with label " + label);
    return null;
  }

  private List<Button> getButtonsAdded() {
    ArgumentCaptor<Button> buttonsCaptor = ArgumentCaptor
        .forClass(Button.class);
    verify(content).add(buttonsCaptor.capture());
    return buttonsCaptor.getAllValues();
  }

}
