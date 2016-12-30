int led = D7;

void setup() {

pinMode(led,OUTPUT);

int height = 25;
float lat = 2.254656;
float lon = 22.254365;
char data[256];    
digitalWrite(led,HIGH);    

sprintf(data, "{ \"height\": %u, \"latitude\": %f, \"longitude\": %f }", height, lat, lon);
Particle.publish("floodread", data);
delay(60000);
digitalWrite(led,LOW);



}
void loop() {


}
