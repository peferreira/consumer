package br.usp.ime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path(value = "/threads")
public class ConsumerThreads {
	
	private static Monitor monitor = new Monitor();

	@GET
	@Produces(value = "text/plain")
	public String status() {
		if (monitor.getState())
			return "true";
		else
			return "false";
	}

	@GET
	@Produces(value = "text/plain")
	@Path(value = "/status")
	public String status1() {
		if (monitor.getState())
			return "true";
		else
			return "false";
	}

	
	@GET
	@Produces(value = "text/plain")
	@Path(value = "/enable")
	public String enable() {
		Thread t;
		monitor.setTrue();
		for (int i = 0; i < 5; i++) {
			t = new Thread(new RunWhileTrue(monitor));
			t.start();
		}
		if (monitor.getState())
			return "true";
		else
			return "false";
	}

	@GET
	@Produces(value = "text/plain")
	@Path(value = "/disable")
	public String disable() {
		monitor.setFalse();
		if (monitor.getState())
			return "true";
		else
			return "false";
	}

}
