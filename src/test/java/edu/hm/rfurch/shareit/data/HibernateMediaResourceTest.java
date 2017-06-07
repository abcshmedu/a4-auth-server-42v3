package edu.hm.rfurch.shareit.data;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class HibernateMediaResourceTest {

	@BeforeClass
	public static void setup() {
		final ResourceManager manager = new ResourceManager();
		final Injector injector = Guice.createInjector(new HibernateMediaResourceModule());
		injector.injectMembers(manager);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
