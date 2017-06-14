package edu.hm.rfurch.shareit.restapi;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.hm.rfurch.shareit.data.MediaResourceModule;
import edu.hm.rfurch.shareit.model.Book;

public class MediaRestTest {

	private static final String MAGIC_TOKEN = "MAGICTOKEN";
	
	@BeforeClass
	public static void setup() {
		final Injector injector = Guice.createInjector(new MediaResourceModule());
		injector.injectMembers(ResourceManager.getResourceManager());
	}
	
	@Test
	public void getBooksTest() {
		Response response = new MediaRest().getBooks(MAGIC_TOKEN);
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"Author1\",\"isbn\":\"9783866801929\",\"title\":\"Title1\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String actuals = response.getEntity().toString();
		assertEquals(expected, actuals);
	}
	
	@Test
	public void getBookTest() {
		Response response = new MediaRest().getBook("978-3-86680-192-9", MAGIC_TOKEN);
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"Author1\",\"isbn\":\"9783866801929\",\"title\":\"Title1\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String actual = response.getEntity().toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getBookBadResonseIsbnTest() {
		Response response = new MediaRest().getBook("", MAGIC_TOKEN);
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+400\",\"data\":\"error\",\"message\":\"Bad Request\",\"data-length\":-1,\"status\":400}";
		String actual = response.getEntity().toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getBookBadResonseNullTest() {
		Response response = new MediaRest().getBook(null, MAGIC_TOKEN);
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+400\",\"data\":\"error\",\"message\":\"Bad Request\",\"data-length\":-1,\"status\":400}";
		String actual = response.getEntity().toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void addBookTest() {
		Book testObject = new Book("test", "test", "978-3-12-732320-7");
		Response response = new MediaRest().addBook(testObject, MAGIC_TOKEN);
		//String expected1 = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"Author1\",\"isbn\":\"978-3-86680-192-9\",\"title\":\"Title1\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String expected2 = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"test\",\"isbn\":\"9783127323207\",\"title\":\"test\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		//String actual1 = response.getEntity().toString();
		String actual2 = new MediaRest().getBook("978-3-12-732320-7", MAGIC_TOKEN).getEntity().toString();
		ResourceManager.dataAccess().remove(testObject);
		//assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void updateBookTest() {
		Book orginal = new Book("test", "test", "978-3-12-732320-7");
		Book modified = new Book("TEST", "TEST", "978-3-12-732320-7");
		new MediaRest().addBook(orginal, MAGIC_TOKEN);
		String actual1 = new MediaRest().getBook("978-3-12-732320-7", MAGIC_TOKEN).getEntity().toString();
		new MediaRest().updateBook(modified, "978-3-12-732320-7", MAGIC_TOKEN);
		String actual2 = new MediaRest().getBook("978-3-12-732320-7", MAGIC_TOKEN).getEntity().toString();
		String expected1 = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"test\",\"isbn\":\"9783127323207\",\"title\":\"test\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String expected2 = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"TEST\",\"isbn\":\"9783127323207\",\"title\":\"TEST\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		ResourceManager.dataAccess().remove(modified);
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void getDiscsTest() {
		Response response = new MediaRest().getDiscs(MAGIC_TOKEN);
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"director\":\"Dirctor1\",\"title\":\"Disc1\",\"barcode\":\"4059251015567\",\"fsk\":1}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String actual = response.getEntity().toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getDiscTest() {
		Response response = new MediaRest().getDisc("4059251015567", MAGIC_TOKEN);
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"director\":\"Dirctor1\",\"title\":\"Disc1\",\"barcode\":\"4059251015567\",\"fsk\":1}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String actual = response.getEntity().toString();
		assertEquals(expected, actual);
	}

}
