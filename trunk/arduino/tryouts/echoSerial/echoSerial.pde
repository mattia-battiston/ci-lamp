int incomingByte = 0;

void setup() {
  Serial.begin(9600);
  Serial.println("Ready!");
}

void loop() {
  if (Serial.available() > 0) {
    // read the incoming byte:
    incomingByte = Serial.read();
    Serial.print(incomingByte, BYTE);
  }
}





