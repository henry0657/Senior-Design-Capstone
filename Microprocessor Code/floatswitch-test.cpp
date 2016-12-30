
int led1 = D7;
int wakeup = A0;


void setup() {
pinMode(led1, OUTPUT);
pinMode(wakeup, INPUT);

}

void loop() {

digitalWrite(led1, HIGH);
delay(4000);
digitalWrite(led1,LOW);
delay(4000);
if(digitalRead(wakeup) != HIGH){
System.sleep(wakeup, RISING, SLEEP_NETWORK_STANDBY);
}


}