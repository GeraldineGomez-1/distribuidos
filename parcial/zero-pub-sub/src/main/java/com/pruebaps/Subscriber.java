package com.pruebaps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;
import javafx.stage.Stage;
import model.Artist;
import view.MainController;
import view.MessageViewController;
import view.SubscribeViewController;

import org.zeromq.ZContext;

//
// Patron distribucion one-way data, Publicador Suscriptor
// Tomado de: http://zguide.zeromq.org/java:chapter1
//  Weather update client in Java
//  Connects SUB socket to tcp://localhost:5556
//  Collects weather updates and finds avg temp in zipcode
//



public class Subscriber
{
	
    public static void main(String[] args)
    {
    	
    	Scanner sc = new Scanner(System.in);
    	
    	//List<String> mensajes = new ArrayList<>();
    	
    	
    	
    	 //Establece el ambiente o contexto zeromq
    	try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Bienvenido a Cricker");
            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
            //Conecta el socket a un puerto
            //subscriber.connect("tcp://localhost:5556");
            //Prueba red domestica
            subscriber.connect("tcp://192.168.0.126:5556");
            
            String filter = (args.length > 0) ? args[0] : "Artistas ";
            //Se suscribe con codigo especial que le permitira filtar los mensajes del publicador
            subscriber.subscribe(filter.getBytes(ZMQ.CHARSET));
            
            //crear el hilo
            //el hilo se queda leyendo
            //guardo lo que me llegue a "mensajes"
            Subscrito t = new Subscrito(subscriber);
            
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("Elige una opcion");
                System.out.println("1. Suscribirme a un artista");
                System.out.println("2. Ver mensajes");
                
                int opcion = sc.nextInt();
                sc.nextLine();
                if(opcion == 1) {
                	System.out.println("Estos son los artistas a los que te puedes subscribir");
                	
                	//lectura del archivo
                	try {
                        FileReader reader = new FileReader("Artistas.txt");
                        BufferedReader bufferedReader = new BufferedReader(reader);
             
                        String line;
             
                        while ((line = bufferedReader.readLine()) != null) {
                            System.out.println(line);
                        }
                        reader.close();
             
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                	
                	System.out.println("Me quiero subscribir a:");
                	String artista = sc.nextLine();
                	filter = artista;
                    subscriber.subscribe(filter.getBytes(ZMQ.CHARSET));
                }else if(opcion == 2) {
                	if(!t.isAlive()) {
                		t.start();
                	}
                }	
            }
        }
    }  
}
class Subscrito extends Thread {
	
	private ZMQ.Socket subscriber;
	public ArrayList<String> mensajes = new ArrayList<String>();

	public Subscrito(ZMQ.Socket subscriber) {
		this.subscriber = subscriber;
	}
	
	@Override
	public void run() {
		/*context = new ZContext();
    	ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
    	subscriber.connect("tcp://192.168.0.126:5556");*/
    	while(!Thread.currentThread().isInterrupted()) {
    		String string = subscriber.recvStr(0).trim();
            mensajes.add(string);
            ArrayList<String> local = getMensajes();
            for(String it: local) {
            	System.out.println(it);
            }
    		//for de todos los "mensajes"
            StringTokenizer sscanf = new StringTokenizer(string, ",");
            String nombreArtista= sscanf.nextToken();
            String mensaje = sscanf.nextToken();
            String fecha = sscanf.nextToken();
            
            if(nombreArtista.compareTo("Artistas") == 0) {
            	System.out.println("HAY UN NUEVO ARTISTA AL QUE TE PUEDES SUBSCRIBIR");
            	System.out.println(mensaje);
            }
            else {
            	 System.out.println("Nuevo mensaje");
                 System.out.println("Artista: " + nombreArtista);
                 System.out.println("Mensaje: " + mensaje);
                 System.out.println("Fecha: " + fecha);
            }
    	}
	}
	
	public ArrayList<String> getMensajes(){
		return this.mensajes;
	}
}
