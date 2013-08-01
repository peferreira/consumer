package br.usp.ime;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class ConsumerThreadsApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(ConsumerThreads.class);
		return classes;
	}

}
