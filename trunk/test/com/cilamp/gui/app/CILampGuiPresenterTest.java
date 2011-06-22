package com.cilamp.gui.app;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Button;
import java.awt.event.ActionListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.service.services.LampService;

public class CILampGuiPresenterTest {

  private CILampGuiPresenter presenter;

  @Mock
  private CILampGuiPresenter.View view;

  @Mock
  private Button alarmOnButton;

  @Mock
  private Button alarmOffButton;

  @Mock
  private LampService lampService;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    mockView();
    presenter = new CILampGuiPresenter(view);
    presenter.setLampService(lampService);
  }

  @Test
  public void alarmOnCallsService() {
    ActionListener alarmOnListener = getActionListenerForButton(alarmOnButton);
    alarmOnListener.actionPerformed(null);

    verify(lampService).turnAlarmOn();
  }

  @Test
  public void alarmOffCallsService() {
    ActionListener alarmOffListener = getActionListenerForButton(alarmOffButton);
    alarmOffListener.actionPerformed(null);

    verify(lampService).turnAlarmOff();
  }

  @Test
  public void showShowsTheView() {
    presenter.show();

    verify(view).show();
  }

  private ActionListener getActionListenerForButton(Button button) {
    ArgumentCaptor<ActionListener> actionListenerCaptor = ArgumentCaptor
        .forClass(ActionListener.class);
    verify(button).addActionListener(actionListenerCaptor.capture());
    return actionListenerCaptor.getValue();
  }

  private void mockView() {
    when(view.getAlarmOnButton()).thenReturn(alarmOnButton);
    when(view.getAlarmOffButton()).thenReturn(alarmOffButton);
  }

}
