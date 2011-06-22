
void setup() {
  Serial.begin(9600);
  Serial.flush();
  log("Starting echoing: you tell me, I'll repeat ;)");
}

void loop() {
  int i=0;  
  char com
  mandbuffer[100];
  
  if(Serial.available()){
     delay(100);
     
     while(Serial.available() && i < 99) {
        commandbuffer[i++] = Serial.read();
     }
     commandbuffer[i++]='\0';
     
     Serial.println((char*)commandbuffer);
     
     //Flushes the buffer of incoming serial data
     Serial.flush();
  }
}

void log(String param){
  Serial.println(param);
}




