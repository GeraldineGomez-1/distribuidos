package com.pruebaps;

import java.util.Random;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

//
// Patron distribucion one-way data, Publicador Suscriptor
// Tomado de: http://zguide.zeromq.org/java:chapter1
//  Actualizacion del clima server en Java
//  Ata el socket PUB a tcp://*:5556
//  Publica actualizaciones del clima aleatorias
//
public class Publisher
{
    public static void main(String[] args) throws Exception
    {
    	
        //Establece el ambiente o contexto zeromq
        try (ZContext context = new ZContext()) {
            //Crea un socket tipo PUB
            ZMQ.Socket publisher = context.createSocket(SocketType.PUB);
            //Ata el socket a un puerto
            publisher.bind("tcp://*:5556");
            publisher.bind("ipc://weather");

            //  Inicializa los numeros al azar
            Random srandom = new Random(System.currentTimeMillis());
       	 
            while (!Thread.currentThread().isInterrupted()) {
                //  Obtiene los valores
                int zipcode, temperature, relhumidity;
                zipcode = 10000 + srandom.nextInt(10000);
                temperature = srandom.nextInt(215) - 80 + 1;
                relhumidity = srandom.nextInt(50) + 10 + 1;
           	 	
  
                // Crea el mensaje en cadena de caracteres
                String update = String.format(
                    "%05d %d %d", zipcode, temperature, relhumidity
                );
                
                 //Envia el mensaje a todos los suscriptores
                publisher.send(update, 0);
            }
        }
    }
}