#define IDLE "IDLE"
#define BUILD_FAILED "BUILD_FAILED"
#define BUILD_SUCCEEDED "BUILD_SUCCEEDED"

const int RED_LIGHT = 10;
const int GREEN_LIGHT = 9;

String command;

void setup() {
  Serial.begin(9600);
  Serial.flush();
  log("Starting application");
  pinMode(RED_LIGHT, OUTPUT);
  pinMode(GREEN_LIGHT, OUTPUT);
  command = IDLE;
}

void loop() {

  if(isCommandAvailable()){
    command = readCommand();
  } 

  if(command.equals(BUILD_FAILED)) {
    alarmOn();
  } else if(command.equals(BUILD_SUCCEEDED)){
    alarmOff();
  } else if(command.equals(IDLE)){
    //do nothing
  } else{
    log("Unknown command: [" + command + "]");
    delay(1000);
  }


}

/**********************************************/
/* Commands: */
/**********************************************/
void alarmOn(){
  greenLightOff();
  
  redLightOn();
  delay(1000);
  redLightOff();
  delay(500);
}

void alarmOff(){
  redLightOff();
  greenLightOn();
  delay(1000);
}

void redLightOn(){
  digitalWrite(RED_LIGHT, HIGH);
}
void redLightOff(){
  digitalWrite(RED_LIGHT, LOW);
}
void greenLightOn(){
  digitalWrite(GREEN_LIGHT, HIGH);
}
void greenLightOff(){
  digitalWrite(GREEN_LIGHT, LOW);
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

