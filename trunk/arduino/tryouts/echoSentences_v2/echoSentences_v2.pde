
void setup() {
  Serial.begin(9600);
  Serial.flush();
  log("Starting echoing: you tell me, I'll repeat ;)");
}

void loop() {
  if(Serial.available()){
    //TODO is this needed?
    delay(100);
    String inputLine = readInputFromSerial();

    log(inputLine);

    //Flushes the buffer of incoming serial data
    Serial.flush();
  }
}

String readInputFromSerial(){
  String inputLine = "";
  while(Serial.available()) {
    inputLine += byte(Serial.read());
  }
  return inputLine;
}

void log(String param){
  Serial.println(param);
}






