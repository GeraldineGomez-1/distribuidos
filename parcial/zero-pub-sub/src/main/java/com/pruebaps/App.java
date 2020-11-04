package com.pruebaps;

import javafx.application.Application;
import javafx.stage.Stage;
import view.MainController;

/**
 * Clase Aplicación
 *
 */
public class App extends Application
{
	public static void main(String[] args) throws Exception
	{
		Subscriber subscriber = new Subscriber();
		subscriber.getArtists();
		SubscriberSingleton.getInstance().setSubscriber(subscriber);
		Thread t = new Thread(SubscriberSingleton.getInstance().getSubscriber());
		t.setDaemon(true);
		t.start();
		launch();


	}

	@Override
	public void start(Stage primaryStage) throws Exception {
       
				
		MainController controller = new MainController();

		controller.start();

	}  


}
