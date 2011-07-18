package com.cilamp.gui.app;

import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.cilamp.event.LampTurnedOffEvent;
import com.cilamp.event.LampTurnedOnEvent;
import com.cilamp.event.base.EventBus;
import com.cilamp.service.services.BuildStatusService;
import com.cilamp.service.services.ErrorReporterService;
import com.cilamp.service.services.LampService;

public class CILampGuiPresenter {

  private View view;

  private LampService lampService = new LampService();

  private BuildStatusService buildStatusService = new BuildStatusService();

  private ErrorReporterService errorReporterService;

  private EventBus eventBus;

  public interface View {
    Button getAlarmOnButton();

    Button getAlarmOffButton();

    Button getRefreshButton();

    void setBuildResult(String result);

    void setBuildNumber(String number);

    void setBuildUrl(String url);

    void setBuildCommitters(String committers);

    void show();

    void hide();

    Component getParentComponent();
  }

  public void initialize(View view, EventBus eventBus,
      ErrorReporterService errorReporterService) {
    this.view = view;
    this.eventBus = eventBus;
    this.errorReporterService = errorReporterService;

    bindListenersToView();
  }

  private void bindListenersToView() {
    view.getAlarmOnButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lampService.turnAlarmOn();
        eventBus.fireEvent(new LampTurnedOnEvent());
      }
    });
    view.getAlarmOnButton().setEnabled(true);

    view.getAlarmOffButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lampService.turnAlarmOff();
        eventBus.fireEvent(new LampTurnedOffEvent());
      }
    });
    view.getAlarmOffButton().setEnabled(false);

    view.getRefreshButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          buildStatusService.getLastCompletedBuildStatus();
        } catch (Exception exception) {
          errorReporterService.notifyError(exception);
          throw new RuntimeException(exception);
        }
      }
    });
  }

  public void show() {
    view.show();
  }

  public void setLampService(LampService lampService) {
    this.lampService = lampService;
  }

  public void setBuildStatusService(BuildStatusService buildStatusService) {
    this.buildStatusService = buildStatusService;
  }

}
