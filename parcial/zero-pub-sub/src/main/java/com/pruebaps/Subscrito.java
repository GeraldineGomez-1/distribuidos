package com.pruebaps;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.zeromq.ZMQ;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import model.Artist;
import model.Crick;

public class Subscrito extends Task {

	private ZMQ.Socket subscriber;

	public ArrayList<String> mensajes = new ArrayList<String>();

	private ObservableList<Crick> cricks;


	public Subscrito(ZMQ.Socket subscriber) {
		this.subscriber = subscriber;
		cricks = FXCollections.observableArrayList(); 
	}
   
	
    public Subscrito() {
    	this.subscriber = SubscriberSingleton.getInstance().getSubscriber().getSubscriber();
	}
	
    protected Object call() throws Exception {
		/*context = new ZContext();
    	ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);*/
    	//subscriber.connect("tcp://192.168.0.126:5556");
    	
		while(!Thread.currentThread().isInterrupted()) {
		
			String string = subscriber.recvStr(0).trim();
	
			mensajes.add(string);
			ArrayList<String> local = getMensajes();
			for(String it: local) {
				System.out.println(it);
			}
			System.out.println("toy aqui");
			//for de todos los "mensajes"
			StringTokenizer sscanf = new StringTokenizer(string, ",");
			String nombreArtista= sscanf.nextToken();
			String mensaje = sscanf.nextToken();
			String fecha = sscanf.nextToken();

			if(nombreArtista.compareTo("Artistas") == 0) {
				System.out.println("HAY UN NUEVO ARTISTA AL QUE TE PUEDES SUBSCRIBIR");
				System.out.println(mensaje);
				SubscriberSingleton.getInstance().getSubscriber().getArtists().add(new Artist(12,mensaje));
				
			}
			else {

				
				//cricks.add(newCrick);
				System.out.println("Nuevo mensaje");
				System.out.println("Artista: " + nombreArtista);
				System.out.println("Mensaje: " + mensaje);
				System.out.println("Fecha: " + fecha);
				String dateTime [] = fecha.split(" ");
				Crick newCrick = new Crick(mensaje,nombreArtista, LocalDate.parse(dateTime[1]), LocalTime.parse(dateTime[2]));
				cricks.add(newCrick);
				SubscriberSingleton.getInstance().getSubscriber().getCricks().add(newCrick);
				System.out.println("Size: " + SubscriberSingleton.getInstance().getSubscriber().getCricks().size());
				System.out.println(dateTime[2]);
				
			}
		}
		return null;
	}

	public ArrayList<String> getMensajes(){
		return this.mensajes;
	}

	public ObservableList<Crick> getCricks() {
		return cricks;
	}

	public void setCricks(ObservableList<Crick> cricks) {
		this.cricks = cricks;
	}
	
	public ZMQ.Socket getSubscriber() {
		return this.subscriber;
	}
	
	public void setSubscriber(ZMQ.Socket subscriber) {
		this.subscriber = subscriber; 
	}


}
