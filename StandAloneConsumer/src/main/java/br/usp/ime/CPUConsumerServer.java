package br.usp.ime;



public class CPUConsumerServer 
{
	public final String NAME = "CPUConsumer";
    public static String URL = "http://localhost:9100/CPUConsumer/";
    private RESTServer restServer;
	
    public CPUConsumerServer() {

        this.restServer = new RESTServer(NAME, URL, new Class[] {ConsumerThreads.class});
    }
	
	public void start() {

        this.restServer.start();
    }

    public void stop() {

        this.restServer.stop();
    }

	
	
    public static void main( String[] args )
    {
		CPUConsumerServer server = new CPUConsumerServer();
		server.start();
    }
}
