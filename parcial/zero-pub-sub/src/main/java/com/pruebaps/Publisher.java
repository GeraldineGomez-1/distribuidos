package com.pruebaps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

import model.Artist;
import model.Crick;

import org.zeromq.ZContext;
import java.util.Scanner;
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
		int auxId = 1;
		Scanner sc = new Scanner(System.in);
		List<Artist> artistas = new ArrayList<>();
		System.out.println("BIENVENIDO A CRICKER\n");


		try {
			FileWriter myWriter = new FileWriter("Artistas.txt");
			myWriter.close();
		}catch(IOException e) {
			System.out.println("An error occurred.");
		}


		//Establece el ambiente o contexto zeromq
		try (ZContext context = new ZContext()) {
			//Crea un socket tipo PUB
			ZMQ.Socket publisher = context.createSocket(SocketType.PUB);
			//Ata el socket a un puerto
			publisher.bind("tcp://*:5556");
			publisher.bind("ipc://weather");
			while (!Thread.currentThread().isInterrupted()) {

				System.out.println("Menú");
				System.out.println("1. Registrar nuevo artista");
				System.out.println("2. Enviar mensaje a fans");
				System.out.print("Opción: ");

				String opcion = sc.next();
				sc.nextLine();

				if(opcion.equals("1")) {
					//ingreso de datos y creo el artista
					System.out.println("\nIngresa los datos del nuevo artista");
					System.out.print("Nombre: ");
					String nombre = sc.nextLine();
					Artist artist = new Artist();
					artist.setName(nombre);
					artist.setId(auxId);
					artistas.add(artist);
					auxId++;

					//Agrego el artista a un archivo de texto para tener pesistencia
					try {
						FileWriter writer = new FileWriter("Artistas.txt", true);
						BufferedWriter bufferedWriter = new BufferedWriter(writer);

						bufferedWriter.write(nombre);
						bufferedWriter.newLine();

						bufferedWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}


					String nuevoArtista = String.format(
							"%s,%s,%d", "Artistas", artist.getName(), artist.getId()
							);
		
					//Envia el mensaje a todos los suscriptores
					publisher.send(nuevoArtista, 0);

					System.out.println("\nAhora " + nombre + " puede publicar mensajes\n");
									
				}

				else if(opcion.equals("2")) {
					System.out.println("\nArtistas");
					System.out.print("Id   ");
					System.out.println("Nombre");
					for(Artist a: artistas) {
						System.out.print(a.getId()+"    ");
						System.out.println(a.getName());
					}
					
					System.out.print("\nSeleccionar artista (ID): ");
					
					int id;
					id = sc.nextInt();
					Artist artist = new Artist();
					for(Artist a: artistas) {
						if(a.getId() == id) {
							artist = a;
						}
					}
					System.out.println("\n¿Qué mensaje le quieres compartir hoy " + artist.getName() + " a tus seguidores?");
					System.out.print("Mensaje: ");
					sc.nextLine();
                    				
					String mensaje;
					mensaje = sc.nextLine();
					Crick crick = new Crick();
					crick.setArtist(artist);
					crick.setMessage(mensaje);

					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
					String mensajeCrick = String.format(
							"%s, %s, %s, %s", crick.getArtist().getName(), crick.getMessage(), crick.getDatePublishing(), crick.getTimePublishing().format(dtf)
							);
					//Envia el mensaje a todos los suscriptores
					publisher.send(mensajeCrick, 0);
					System.out.println();
				}else {
					System.out.println("\nOpcion no valida\n");
				}


			}
		}
	}
}