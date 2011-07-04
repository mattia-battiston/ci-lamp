package com.cilamp.event.base;

class TestEvent implements CILampEvent<TestEventHandler> {

  static CILampEvent.Type<TestEventHandler> TYPE = new Type<TestEventHandler>();

  private String property;

  private int numberOfHandlersExecuted;

  @Override
  public com.cilamp.event.base.CILampEvent.Type<TestEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(TestEventHandler handler) {
    handler.onTestEvent(this);
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public int getNumberOfHandlersExecuted() {
    return numberOfHandlersExecuted;
  }

  public void increaseNumberOfHandlersExecuted() {
    numberOfHandlersExecuted++;
  }

}