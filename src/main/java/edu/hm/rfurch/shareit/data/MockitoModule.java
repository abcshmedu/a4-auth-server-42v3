package edu.hm.rfurch.shareit.data;

import com.google.inject.AbstractModule;
import static org.mockito.Mockito.*;

public class MockitoModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IData.class).toInstance(mock(IData.class));
	}

}
