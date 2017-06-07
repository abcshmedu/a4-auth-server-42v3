package edu.hm.rfurch.shareit.data;

import com.google.inject.AbstractModule;

public class MediaResourceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IData.class).to(MediaResource.class);	
	}

}
