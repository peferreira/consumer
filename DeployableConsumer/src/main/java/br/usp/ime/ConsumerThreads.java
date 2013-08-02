package br.usp.ime;

import javax.jws.WebParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("threads")
public class ConsumerThreads {
	
	private static Monitor monitor = new Monitor();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String status() {
		if (monitor.getState())
			return "true";
		else
			return "false";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "/status")
	public String status1() {
		return status();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/enable")
	public String enable4() {
		return enable("4");
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/enable/{id}")
	public String enable(@PathParam("id") String id) {
		Thread t;
		int numThreads = Integer.parseInt(id);
		monitor.setTrue();
		for (int i = 0; i < numThreads; i++) {
			t = new Thread(new RunWhileTrue(monitor));
			t.start();
		}
		if (monitor.getState())
			return "true";
		else
			return "false";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path(value = "/disable")
	public String disable() {
		monitor.setFalse();
		if (monitor.getState())
			return "true";
		else
			return "false";
	}

}
