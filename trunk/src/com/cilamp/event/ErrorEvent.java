package com.cilamp.event;

import com.cilamp.event.base.CILampEvent;

public class ErrorEvent implements CILampEvent<ErrorEventHandler> {

  public static Type<ErrorEventHandler> TYPE = new Type<ErrorEventHandler>();

  private Throwable error;

  public ErrorEvent(Throwable error) {
    this.error = error;
  }

  @Override
  public Type<ErrorEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(ErrorEventHandler handler) {
    handler.onError(this);
  }

  public Throwable getError() {
    return error;
  }

  public void setError(Throwable error) {
    this.error = error;
  }

}
