// This #include statement was automatically added by the Particle IDE.
#include "TinyGPS++/TinyGPS++.h"

TinyGPSPlus gps;
int pC = D1;
int bU = D2;
float lat, lon;
char c;

void setup() {
    
   
    pinMode(pC,OUTPUT);
    pinMode(bU,OUTPUT);
    Serial.begin(9600);
    Serial1.begin(9600);
    digitalWrite(pC,HIGH);
    digitalWrite(bU,HIGH);
}

void loop() {
       
 for(unsigned long start = millis(); millis() - start <1000;){
        while(Serial1.available() > 0 ){
            c = Serial1.read();
            //Serial.print(c);
            if(gps.encode(c)){
                get_coords();
            }
        }
}
    delay(500);
}
void get_coords(){
    // If we have a valid GPS location then publish it'
   
    if (gps.location.isValid() ){
   
        lat = gps.location.lat();
        lon = gps.location.lng();
        //turn power control off for gps, also failsafe boolean to stop from posting
        
    Serial.print(F(" LATITUDE "));
    Serial.print(lat,6);
    Serial.print(F(" , "));
    Serial.print(F(" LONGITUDE "));
    Serial.print(lon,6);
    digitalWrite(pC,LOW);
    }

    
}