package com.pruebaps;

public final class SubscriberSingleton {
	
	Subscriber subscriber;
	
	private final static SubscriberSingleton INSTANCE = new SubscriberSingleton();

	private  SubscriberSingleton() {}

	public static SubscriberSingleton getInstance() {
		return INSTANCE;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

}
