package com.cilamp.gui.app;

import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cilamp.event.ErrorEvent;
import com.cilamp.event.BuildSucceededEvent;
import com.cilamp.event.BuildFailedEvent;
import com.cilamp.event.base.EventBus;
import com.cilamp.service.services.BuildStatusService;
import com.cilamp.service.services.LampService;

public class CILampGuiPresenter {

  final Logger log = LoggerFactory.getLogger(CILampGuiPresenter.class);

  private View view;

  private LampService lampService = new LampService();

  private BuildStatusService buildStatusService = new BuildStatusService();

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

    Boolean isShown();
  }

  public void initialize(View view, EventBus eventBus) {
    this.view = view;
    this.eventBus = eventBus;

    bindListenersToView();
  }

  private void bindListenersToView() {
    view.getAlarmOnButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          lampService.buildFailed();
          eventBus.fireEvent(new BuildFailedEvent());
        } catch (Exception exception) {
          log.error("Error turning alarm ON", exception);
          eventBus.fireEvent(new ErrorEvent(exception));
        }
      }
    });
    view.getAlarmOnButton().setEnabled(true);

    view.getAlarmOffButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          lampService.buildSucceeded();
          eventBus.fireEvent(new BuildSucceededEvent());
        } catch (Exception exception) {
          log.error("Error turning alarm OFF", exception);
          eventBus.fireEvent(new ErrorEvent(exception));
        }
      }
    });
    view.getAlarmOffButton().setEnabled(false);

    view.getRefreshButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          buildStatusService.getLastCompletedBuildStatus();
        } catch (Exception exception) {
          log.error("Error retrieving build information", exception);
          eventBus.fireEvent(new ErrorEvent(exception));
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
