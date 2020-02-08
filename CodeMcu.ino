#include <ESP8266WiFi.h>
// Nome e Senha da Rede
char* net_ssid = "";              
char* net_pass = "";         

IPAddress ip(192, 168,4,1);
WiFiServer  daServer(1987); //ip:1987 = socket     
WiFiClient  daClient;    

void setup(){
  pinMode(D5, OUTPUT);
  Serial.begin(9600);        
  ConfigWifi("Esp-Java", "12345678");// configurando o acess point(nome e senha)
}

void loop(){
  CheckClients(); //verifica se algum cliente criou uma conexão
  CheckMessage();//verifica se tem alguma mensagem recebida
 }
  
void ConfigWifi(char* Nome, char* Senha){
  WiFi.disconnect(); //desconecta wifi para configurar um  novo
  WiFi.mode(WIFI_AP_STA);//modo access point
  Serial.println("WIFI Mode : AccessPoint Station");
      
  net_ssid = Nome;
  net_pass = Senha;
  
  WiFi.softAP(net_ssid, net_pass);//inicia o access point
  Serial.println("WIFI << " + String(net_ssid) + " >>");
  delay(2000);
  
  IPAddress IP = WiFi.softAPIP();//recebe o ip da rede do esp
  Serial.println("Server IP :");
  Serial.println(IP);
  
  daServer.begin();//inicia o servidor
  daServer.setNoDelay(true);
  Serial.println("Servidor Iniciado");
}

void CheckClients(){   
  if(daServer.hasClient()){//verifica se algum cliente se conectou ao wifi
    if(daClient = daServer.available()){//verifica se tem client conectado no server e atribui ao daclient
        Serial.println("Novo Cliente");
    }
  }
  WiFiClient daClient = daServer.available();
  daClient.stop();
}

void CheckMessage(){
  while(daClient.available()){
    String Message = daClient.readStringUntil(' ');//lê caracteres do buffer serial e os move para uma String
    Serial.println(Message);
    daClient.flush(); //limpa o buffer p não receber caracter lixos
    if(Message == "a"){//liga led se receber a
      digitalWrite(D5, HIGH);       
    }
    else if(Message == "b"){//desliga
      digitalWrite(D5, LOW);        
    }
  }
}
