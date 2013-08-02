package br.usp.ime;

public class RunWhileTrue implements Runnable{
	private Monitor monitor;

	RunWhileTrue(Monitor monitor) {
		this.monitor = monitor;
		
	}
	public void run() {
		/*int i = 0;*/
		while (monitor.getState() == true) {
			/*i++;
			i = i % 1000000;*/
		}
		/*System.out.println(i);*/
	}
}

