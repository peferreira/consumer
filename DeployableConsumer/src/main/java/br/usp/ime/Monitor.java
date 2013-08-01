package br.usp.ime;

public class Monitor {
	volatile private boolean state = false;
	
	Monitor(){
				
	}
	boolean getState() {
		return state;
	}
	
	void setTrue() {
		state = true;
	}
	void setFalse() {
		state = false;
	}
}
