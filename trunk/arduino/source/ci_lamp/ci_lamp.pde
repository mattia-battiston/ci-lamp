#define BUILD_FAILED "BUILD_FAILED"
#define BUILD_SUCCEEDED "BUILD_SUCCEEDED"

void setup() {
  Serial.begin(9600);
  Serial.flush();
  log("Starting application");
  pinMode(13, OUTPUT);
}

void loop() {

  if(isCommandAvailable()){
    String command = readCommand();

    if(command.equals(BUILD_FAILED)) {
      turnAlarmOn();
    }
    else if(command.equals(BUILD_SUCCEEDED)){
      turnAlarmOff();
    }
    else{
      log("Unknown command: [" + command + "]");
    }
  }

}

/**********************************************/
/* Commands: */
/**********************************************/
void turnAlarmOn(){
  log("Turning alarm on:");
  digitalWrite(13, HIGH);
}

void turnAlarmOff(){
  log("Turning alarm off:");
  digitalWrite(13, LOW);
}

/**********************************************/
/* CommandReader: reads input from serial */
/**********************************************/
boolean isCommandAvailable(){
  return Serial.available();
}

String readCommand(){
  //TODO is this needed?
  delay(100);
  String command = readInputFromSerial();
  log("Input command is " + command);
  return command;
}

String readInputFromSerial(){
  String inputLine = "";
  while(Serial.available()) {
    inputLine += byte(Serial.read());
  }
  //Flushes the buffer of incoming serial data
  Serial.flush();
  return inputLine;
}

/**********************************************/
/* util */
/**********************************************/
void log(String param){
  Serial.println(param);
}
