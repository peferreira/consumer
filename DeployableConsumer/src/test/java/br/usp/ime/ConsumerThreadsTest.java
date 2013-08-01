package br.usp.ime;

import static org.junit.Assert.assertEquals;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConsumerThreadsTest {
	private static String ENDPOINT = "http://localhost:8080/DeployableConsumer/";
	private static Server server;

	@BeforeClass
	public static void beforeClass() {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(ConsumerThreads.class);
		sf.setAddress(ENDPOINT);
		server = sf.create();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@AfterClass
	public static void afterClass() throws Exception {
		server.stop();
		server.destroy();
	}

	@Test
	public void testStatusOnStartShouldReturnFalse() {
		WebClient client = WebClient.create(ENDPOINT + "threads/");

		client.path(ENDPOINT + "threads/status");
		String actual = client.accept("text/plain").get(String.class);
		assertEquals("false", actual);

		client.path(ENDPOINT + "threads/enable");
		actual = client.get(String.class);
		assertEquals("true", actual);

		client.path(ENDPOINT + "threads/disable");
		actual = client.get(String.class);
		assertEquals("false", actual);
	}
}
