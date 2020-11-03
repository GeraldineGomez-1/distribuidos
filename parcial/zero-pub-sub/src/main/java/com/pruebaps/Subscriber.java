package com.pruebaps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import model.Artist;
import model.Crick;


import org.zeromq.ZContext;

public class Subscriber extends Task
{

	private ObservableList<Artist> artists;

	private ObservableList<Crick> cricks;

	private ZMQ.Socket subscriber;

	public Subscriber() {

		artists =  FXCollections.observableArrayList();
		cricks = FXCollections.observableArrayList();

	}

	public void suscribe(String artist) {
	
		if (artist != null) {
			SubscriberSingleton.getInstance().getSubscriber().getSubscriber().subscribe(artist.getBytes(ZMQ.CHARSET));

		}
	}
	
	public ObservableList<Artist> getArtists() throws Exception {

		FileReader reader = new FileReader("Artistas.txt");
		BufferedReader bufferedReader = new BufferedReader(reader);

		String line;
		int i = 1;

		while ((line = bufferedReader.readLine()) != null) {
			Artist newArtist = new Artist();
			newArtist.setName(line);
			newArtist.setId(i);
			artists.add(newArtist);
			i++;
		}

		reader.close();
		return artists;
	}

	@Override
	protected Object call() throws Exception {

		try (ZContext context = new ZContext()) {
		
			subscriber = context.createSocket(SocketType.SUB);
			subscriber.connect("tcp://192.168.126.1:5556");

			String filter = "Artistas,";
			subscriber.subscribe(filter.getBytes(ZMQ.CHARSET));
			while(!Thread.currentThread().isInterrupted()) {

				String string = subscriber.recvStr(0).trim();

				ArrayList<String> local = new ArrayList<String>();

				for(String it: local) {
					System.out.println(it);
				}
				//for de todos los "mensajes"
				StringTokenizer sscanf = new StringTokenizer(string, ",");
				String nombreArtista= sscanf.nextToken();
				String mensaje = sscanf.nextToken();


				if(nombreArtista.compareTo("Artistas") == 0) {
					String id = sscanf.nextToken();
					artists.add(new Artist(Integer.parseInt(id), mensaje));

				}
				else {
					
					String fecha = sscanf.nextToken();
					String hora = sscanf.nextToken();			
					Crick newCrick = new Crick(mensaje,nombreArtista, LocalDate.parse(fecha.trim()), LocalTime.parse(hora.trim()));
					cricks.add(newCrick);
					
				}
			}

		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	public ObservableList<Artist> getListArtists() {
		return artists;
	}

	public void setArtists(ObservableList<Artist> artists) {
		this.artists = artists;
	}

	public ObservableList<Crick> getCricks() {
		return cricks;
	}

	public void setCricks(ObservableList<Crick> cricks) {
		this.cricks = cricks;
	}

	public ZMQ.Socket getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(ZMQ.Socket subscriber) {
		this.subscriber = subscriber;
	}



}

