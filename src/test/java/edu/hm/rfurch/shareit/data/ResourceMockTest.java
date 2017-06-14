package edu.hm.rfurch.shareit.data;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.hm.rfurch.shareit.model.Book;
import edu.hm.rfurch.shareit.model.IBook;
import edu.hm.rfurch.shareit.model.IMedium;

public class ResourceMockTest {

	@BeforeClass
	public static void setup() {
		final Injector injector = Guice.createInjector(new MockitoModule());
		injector.injectMembers(ResourceManager.getResourceManager());
		when(ResourceManager.dataAccess().getBooks()).thenCallRealMethod();
		when(ResourceManager.dataAccess().getDiscs()).thenCallRealMethod();
		when(ResourceManager.dataAccess().getBook(anyString())).thenCallRealMethod();
		when(ResourceManager.dataAccess().getDisc(anyString())).thenCallRealMethod();
	}
	
	@Test
	public void getAllMediaEmptyListTest() {
		List<IMedium> media = new ArrayList<>();
		when(ResourceManager.dataAccess().getMediums()).thenReturn(media);
		assertEquals(media, ResourceManager.dataAccess().getMediums());
	}
	
	@Test
	public void getAllMediaTest() {
		List<IMedium> media = new ArrayList<>();
		media.add(new Book("Die Eule mit der Beule", "Susanne Weber", "978-3789167065"));
		when(ResourceManager.dataAccess().getMediums()).thenReturn(media);
		assertEquals(media, ResourceManager.dataAccess().getMediums());
	}
	
	@Test
	public void getBookIsbnWithSeperatorTest() {
		final IBook dieEuleMitDerBeule = new Book("Die Eule mit der Beule", "Susanne Weber", "978-3789167065"); 
		List<IMedium> media = new ArrayList<>();
		media.add(dieEuleMitDerBeule);
				
		when(ResourceManager.dataAccess().getMediums()).thenReturn(media);
		
		Optional<IMedium> optionalBookFromData = ResourceManager.dataAccess().getBook("978-3789167065");
		assertTrue(optionalBookFromData.isPresent());
		IBook bookFromData = (IBook)optionalBookFromData.get();
		
		assertEquals(dieEuleMitDerBeule, bookFromData);
	}
	
	@Test
	public void getBookIsbnWithoutSeperatorTest() {
		final IBook dieEuleMitDerBeule = new Book("Die Eule mit der Beule", "Susanne Weber", "978-3789167065"); 
		List<IMedium> media = new ArrayList<>();
		media.add(dieEuleMitDerBeule);
				
		when(ResourceManager.dataAccess().getMediums()).thenReturn(media);
		
		Optional<IMedium> optionalBookFromData = ResourceManager.dataAccess().getBook("9783789167065");
		assertTrue(optionalBookFromData.isPresent());
		IBook bookFromData = (IBook)optionalBookFromData.get();
		
		assertEquals(dieEuleMitDerBeule, bookFromData);
	}

}
