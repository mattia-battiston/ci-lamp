package com.cilamp.service.services;

import com.cilamp.service.command.AlarmOffCommand;
import com.cilamp.service.command.AlarmOnCommand;

public class LampService {

  public void turnAlarmOn() {
    new AlarmOnCommand().execute();
  }

  public void turnAlarmOff() {
    new AlarmOffCommand().execute();
  }

}
