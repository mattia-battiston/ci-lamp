package com.cilamp.service.services;

import com.cilamp.service.command.BuildSucceededCommand;
import com.cilamp.service.command.BuildFailedCommand;

public class LampService {

  public void buildFailed() {
    new BuildFailedCommand().execute();
  }

  public void buildSucceeded() {
    new BuildSucceededCommand().execute();
  }

}
