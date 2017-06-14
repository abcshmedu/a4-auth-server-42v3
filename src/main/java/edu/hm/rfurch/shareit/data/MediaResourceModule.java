package edu.hm.rfurch.shareit.data;

import com.google.inject.AbstractModule;
import edu.hm.rfurch.shareit.logic.IMediaService;
import edu.hm.rfurch.shareit.logic.MediaService;

public class MediaResourceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IMediaService.class).to(MediaService.class);
		bind(IData.class).to(MediaResource.class);	
	}

}
