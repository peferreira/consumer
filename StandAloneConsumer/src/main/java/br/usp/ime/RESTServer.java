package br.usp.ime;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.log4j.Logger;

/**
 * Stand alone server that makes REST APIs available to clients.
 * 
 * @author leonardo, alfonso, daniel
 * 
 */
public class RESTServer implements Runnable {

    private Logger logger = Logger.getLogger(RESTServer.class);;

    private String name;
    private String url;
    private Class<?>[] classes;
    private boolean running = false;

    /**
     * 
     * @param name
     *            ex: CPUConsumer
     * @param url
     *            ex: http://localhost:9100/CPUConsumer/
     * @param classes
     *            the classes with the JAXRS annotations
     */
    public RESTServer(String name, String url, Class<?>[] classes) {

        this.name = name;
        this.url = url;
        this.classes = classes;
    }

    public void start() {

        new Thread(this).start();
        while (!running) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }

    }

    public void stop() {
        running = false;
    }

    public void run() {

        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        for (Class<?> cls : this.classes) {
            sf.setResourceClasses(cls);
        }
        sf.setAddress(this.url);
        sf.create();
        logger.info(this.name + " has started [" + this.url + "]");
        running = true;

        while (running) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info(this.name + " has stopped");
    }

}