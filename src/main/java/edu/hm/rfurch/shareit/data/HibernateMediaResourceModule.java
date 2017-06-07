package edu.hm.rfurch.shareit.data;

import com.google.inject.AbstractModule;

public class HibernateMediaResourceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IData.class).to(HibernateMediaResource.class);	
	}

}
