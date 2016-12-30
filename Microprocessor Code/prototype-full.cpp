// This #include statement was automatically added by the Particle IDE.
#include "TinyGPS++/TinyGPS++.h"


int pC = B2;
int bU = B3;
int yellow = B1;
int red = B0;
int wakeup = D0;
int color = 0;
long height;
float sendHeight;
long heightarray[41];
int led = D7;
float lat = 30.4095;
float lon = -91.1081;
TinyGPSPlus gps;
char c;
char data[256];    
float offset = 0;
const int anPin = A2;

float inches,nextinch =0.0;
long heights[2];
long avg;
const long sleeptime = 30;

bool del, found = false;

void setup(){
    //begin PWM reading
    pinMode(anPin, INPUT);
    //begin serial connections
    Serial1.begin(9600);
    Serial.begin(9600);
   
    //set pin modes and write high
    pinMode(pC,OUTPUT);
    pinMode(bU,OUTPUT);
    pinMode(led,OUTPUT);
    pinMode(yellow,OUTPUT);
    pinMode(red, OUTPUT);
    pinMode(wakeup, INPUT);
    digitalWrite(pC,HIGH);
    digitalWrite(bU,HIGH);
    //found =FALSE;
  //  delay(30000);
   unsigned long tooLong =  millis();
         //input gps data while available
      //  Serial.println("Getting GPS Data ");
     
     // digitalWrite(red, HIGH);
        while(found !=true && ((millis() - tooLong) < 30000UL))
            get_GPS();
            //digitalWrite(pC,LOW);
       //     digitalWrite(yellow, HIGH);
   
    
}
void loop()
{
        //digitalWrite(pC,LOW);       
        del = false;
        if(digitalRead(wakeup) != HIGH)
        {
            digitalWrite(led,HIGH);           
            delay(2000);
            digitalWrite(led,LOW);
            digitalWrite(color,LOW);
            System.sleep(wakeup,RISING,SLEEP_NETWORK_STANDBY);
        }
        digitalWrite(led, HIGH);
       
        
        
        
      //  unsigned long tooLong = millis();
    
        if(del != true ){
            delay(5000);
            //height = run_ultra();
            height = alt_readSensor();
        }
       // height=alt_readSensor();
       // height = avg_readSensor();
        sendHeight= (1508-height + offset)/25.4;
        send_data();
        
        //System.sleep(SLEEP_MODE_DEEP, sleeptime);
        //del=false;
        
            
        if(sendHeight > 8.0)  color = red;
        else color = yellow;
            digitalWrite(yellow, LOW);
            digitalWrite(red,LOW);
            unsigned long tooLong = millis();
            while((millis() - tooLong) < 15000UL)
            {
                digitalWrite(color, HIGH);
                delay(250);
                digitalWrite(color, LOW);
                delay(250);
            }
            digitalWrite(color,HIGH);
            //digitalWrite(yellow, HIGH);
            //digitalWrite(red, HIGH);
        clearHeights();
    
        
}

void clearHeights()
{
  //  heights[0] = 0;
    //heights[1] = 0;
    height = 0;
}


void get_coords(){
    // If we have a valid GPS location then publish it'
    if (gps.location.isValid() ){
   
        lat = gps.location.lat();
        lon = gps.location.lng();
        found = true;
        //turn power control off for gps, also failsafe boolean to stop from posting
        digitalWrite(pC,LOW);
    }
    
    else found = false;
    
}

long run_ultra() {
    //del = false;
   
    
    while((abs(heights[0] - heights[1])) < 3 ){
        heights[0] = 1492-read_sensorpwm();
        delay(500);
        heights[1] = 1497-read_sensorpwm();
          }
    del = true;
    return heights[1];
}


long read_sensorpwm(){
    int i,z,val = 0;
    long  mm,nextmm, cmp = 0;
    while(i < 20){
    mm = pulseIn(anPin,HIGH);
    //inches = mm/25.4;
    cmp = abs(mm-nextmm);
        if(cmp < 3 || z==0){
            avg = avg + mm;
            //nextinch = inches;
            nextmm = mm;
            z++;
        }
    i++;
    }
    val = avg/z;
    //inches =val/25.4;
    i=0;
    z=0;
    avg =0;
    nextmm = 0;
    mm = 0;
    return val;
}

long alt_readSensor(){
    for(int i = 0; i<41; i++){
        delay(100);
        heightarray[i] = pulseIn(anPin,HIGH);
        Serial.print(F(" HEIGHT "));
        Serial.print(i);
        Serial.print(F(": "));
        Serial.print(heightarray[i]);
        Serial.println(F(" "));
    }
    selectionSort(heightarray);
    del = true;
    return heightarray[20];
    
    
}

long avg_readSensor(){
    int total = 0;
    for(int i = 0; i<100; i++)
        total += pulseIn(anPin,HIGH);
    return total/100;
    del = true;
}
void get_GPS(){
   for(unsigned long start = millis(); millis() - start <1000;){
        while(Serial1.available() > 0){
            c = Serial1.read();
            Serial.print(c);
            if(gps.encode(c)){
                get_coords();
            }
        }
 
    }
     delay(500);
}

void print_GPS(){
    //print gps data
    Serial.print(F(" LATITUDE "));
    Serial.print(lat,6);
    Serial.print(F(" , "));
    Serial.print(F(" LONGITUDE "));
    Serial.print(lon,6);
}

void print_height(){
    //print height data
    Serial.print(" H= ");
    Serial.print(height);
    Serial.print(" mm ");
    Serial.print(inches);
    Serial.print(" inches ");
   
}
void send_data() { 
   //publish the data to the database
    sprintf(data, "{ \"height\": %f, \"latitude\": %f, \"longitude\": %f }", sendHeight, lat, lon);
        Particle.publish("floodread", data);
}
   
   
void selectionSort(long A[41] ){
    int j, k, minIndex;
    long min;
    int n = 41;
    for(k=0; k < n; k++){
        min = A[k];
        minIndex = k;
        for(j = k+1; j < n; j++){
            if(A[j] < min){
                min = A[j];
                minIndex = j;
            }
        }
        A[minIndex] = A[k];
        A[k] = min;
    }
}