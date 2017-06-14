/**package edu.hm.rfurch.shareit.data;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.hm.rfurch.shareit.model.Book;
import edu.hm.rfurch.shareit.model.IBook;
import edu.hm.rfurch.shareit.model.IMedium;

public class ResourceMockTest {

	private IData data;
	@Before
	public void setup() {
		Injector injector = Guice.createInjector(new MockitoModule());
		this.data = injector.getInstance(IData.class);
		when(data.getBooks()).thenCallRealMethod();
		when(data.getDiscs()).thenCallRealMethod();
		when(data.getBook(anyString())).thenCallRealMethod();
		when(data.getDisc(anyString())).thenCallRealMethod();
	}
	
	@Test
	public void getAllMediaEmptyListTest() {
		List<IMedium> media = new ArrayList<>();
		when(data.getMediums()).thenReturn(media);
		assertEquals(media, data.getMediums());
	}
	
	@Test
	public void getAllMediaTest() {
		List<IMedium> media = new ArrayList<>();
		media.add(new Book("Die Eule mit der Beule", "Susanne Weber", "978-3789167065"));
		when(data.getMediums()).thenReturn(media);
		assertEquals(media, data.getMediums());
	}
	
	@Test
	public void getBookIsbnWithSeperatorTest() {
		final IBook dieEuleMitDerBeule = new Book("Die Eule mit der Beule", "Susanne Weber", "978-3789167065"); 
		List<IMedium> media = new ArrayList<>();
		media.add(dieEuleMitDerBeule);
				
		when(data.getMediums()).thenReturn(media);
		
		Optional<IMedium> optionalBookFromData = data.getBook("978-3789167065");
		assertTrue(optionalBookFromData.isPresent());
		IBook bookFromData = (IBook)optionalBookFromData.get();
		
		assertEquals(dieEuleMitDerBeule, bookFromData);
	}
	
	@Test
	public void getBookIsbnWithoutSeperatorTest() {
		final IBook dieEuleMitDerBeule = new Book("Die Eule mit der Beule", "Susanne Weber", "978-3789167065"); 
		List<IMedium> media = new ArrayList<>();
		media.add(dieEuleMitDerBeule);
				
		when(data.getMediums()).thenReturn(media);
		
		Optional<IMedium> optionalBookFromData = data.getBook("9783789167065");
		assertTrue(optionalBookFromData.isPresent());
		IBook bookFromData = (IBook)optionalBookFromData.get();
		
		assertEquals(dieEuleMitDerBeule, bookFromData);
	}

}
**/