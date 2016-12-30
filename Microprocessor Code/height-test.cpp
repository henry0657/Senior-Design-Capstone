

const int anPin = A2;
long anVolt, mm,nextmm = 0;
float inches,nextinch,cmp =0.0;
float heights[2];
long avg;
int x = 10000;
int i,z =0;
void setup() {

    pinMode(anPin, INPUT);
    Serial.begin(9600);
}

void loop() {
    
    heights[0] = read_sensorpwm();
    print_range();
    heights[1] = read_sensorpwm();
    
    if((abs(heights[0] - heights[1])) < 2 )
        delay(x);
    
   // delay(1000);
}

void read_sensor(){
    anVolt = analogRead(anPin);
    mm = anVolt*5;
    inches = mm/25.4;
}
float read_sensorpwm(){
    
    while(i < 10){
    mm = pulseIn(anPin,HIGH);
    inches = mm/25.4;
    cmp = abs(inches-nextinch);
     if(cmp < 5 || z==0){
        avg = avg + mm;
        nextinch = inches;
        nextmm = mm;
        z++;
        
    }
    i++;
    }
    mm = avg/z;
    inches =mm/25.4;
    i=0;
    z=0;
    avg =0;
    return inches;
}

void print_range() { 
    Serial.print("H=");
    Serial.print(mm);
    Serial.print(" mm ");
    Serial.print(inches);
    Serial.print(" inches ");
   
}
   