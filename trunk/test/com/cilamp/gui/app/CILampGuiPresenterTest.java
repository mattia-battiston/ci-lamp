package com.cilamp.gui.app;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Button;
import java.awt.event.ActionListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.event.LampTurnedOffEvent;
import com.cilamp.event.LampTurnedOnEvent;
import com.cilamp.event.base.EventBus;
import com.cilamp.service.services.LampService;

public class CILampGuiPresenterTest {

  private CILampGuiPresenter presenter = new CILampGuiPresenter();

  @Mock
  private CILampGuiPresenter.View view;

  @Mock
  private Button alarmOnButton;

  @Mock
  private Button alarmOffButton;

  @Mock
  private LampService lampService;

  @Mock
  private EventBus eventBus;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    mockView();
    presenter.setLampService(lampService);
    presenter.initialize(view, eventBus);
  }

  @Test
  public void alarmOnCallsService() {
    ActionListener alarmOnListener = getActionListenerForButton(alarmOnButton);
    alarmOnListener.actionPerformed(null);

    verify(lampService).turnAlarmOn();
  }

  @Test
  public void alarmOnFiresEvent() {
    ActionListener alarmOnListener = getActionListenerForButton(alarmOnButton);
    alarmOnListener.actionPerformed(null);

    verify(eventBus).fireEvent(any(LampTurnedOnEvent.class));
  }

  @Test
  public void alarmOffCallsService() {
    ActionListener alarmOffListener = getActionListenerForButton(alarmOffButton);
    alarmOffListener.actionPerformed(null);

    verify(lampService).turnAlarmOff();
  }

  @Test
  public void alarmOffFiresEvent() {
    ActionListener alarmOnListener = getActionListenerForButton(alarmOnButton);
    alarmOnListener.actionPerformed(null);

    verify(eventBus).fireEvent(any(LampTurnedOffEvent.class));
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
