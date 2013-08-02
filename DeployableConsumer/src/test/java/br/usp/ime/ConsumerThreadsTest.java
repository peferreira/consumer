package br.usp.ime;

import static org.junit.Assert.assertEquals;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConsumerThreadsTest {
	private static String ENDPOINT = "http://localhost:8080/DeployableConsumer/";
	private Server server;
	private static WebClient client;

	
	@BeforeClass
	public static void initialize() {
		client = WebClient.create(ENDPOINT + "threads/");
	}
	
	@Before 
	public void startServer() {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(ConsumerThreads.class);
		sf.setAddress(ENDPOINT);
		server = sf.create();
	}
	
	@After 
	public void stopServer() {
		server.stop();
		server.destroy();
	}

	@Test
	public void initialStatusShouldBeFalse() {
		client.path(ENDPOINT + "threads/status");
		String actual = client.accept("text/plain").get(String.class);
		assertEquals("false", actual);
	}
	@Test
	public void statusShouldBeTrueAfterEnable() {
		client.path(ENDPOINT + "threads/enable");
		String actual = client.accept("text/plain").get(String.class);
		assertEquals("true", actual);
	}
	
	@Test
	public void  statusShouldBeFalseAfterDisable() {
     	client.path(ENDPOINT + "threads/disable");
		String actual = client.accept("text/plain").get(String.class);
		assertEquals("false", actual);
	}
}
