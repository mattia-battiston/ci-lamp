package com.cilamp.event.base;

public interface CILampEvent<H extends CILampEventHandler> {

  public static class Type<H> {
  }

  Type<H> getAssociatedType();

  void dispatch(H handler);

}
