package be.odisee.domain;

import java.io.Serializable;

public class TimeSlot implements Serializable{
	
	private int ID;
	
	public TimeSlot(int ID){
		this.ID  = ID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}
		
}
