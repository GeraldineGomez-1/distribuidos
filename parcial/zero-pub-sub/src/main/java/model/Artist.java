package model;


public class Artist {

    
	private int id;
	
	private String name;
	
	private boolean  subscribed;

	public Artist() {

	}

	public Artist( int pId, String pName) {
	   
		id = pId;
		name = pName;
		subscribed = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}


}
