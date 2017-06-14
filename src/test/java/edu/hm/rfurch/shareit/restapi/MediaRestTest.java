package edu.hm.rfurch.shareit.restapi;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import edu.hm.rfurch.shareit.logic.IMediaService;
import edu.hm.rfurch.shareit.model.Disc;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.hm.rfurch.shareit.data.MediaResourceModule;
import edu.hm.rfurch.shareit.model.Book;

public class MediaRestTest {

	private static final String MAGIC_TOKEN = "MAGICTOKEN";
	private IMediaService service;
	@Before
	public void setup() {
		Injector injector = Guice.createInjector(new MediaResourceModule());
		this.service = injector.getInstance(IMediaService.class);
	}
	
	@Test
	public void getBooksTest() {
		Response response = new MediaRest(service).getBooks(MAGIC_TOKEN);
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"Author1\",\"isbn\":\"9783866801929\",\"title\":\"Title1\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String actuals = response.getEntity().toString();
		assertEquals(expected, actuals);
	}
	
	@Test
	public void getBookTest() {
		Response response = new MediaRest(service).getBook("978-3-86680-192-9", MAGIC_TOKEN);
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"Author1\",\"isbn\":\"9783866801929\",\"title\":\"Title1\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String actual = response.getEntity().toString();
		assertEquals(expected, actual);
	}


	
	@Test
	public void getBookBadReasonseIsbnTest() {
		Response response = new MediaRest(service).getBook("", MAGIC_TOKEN);
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+400\",\"data\":\"error\",\"message\":\"Bad Request\",\"data-length\":-1,\"status\":400}";
		String actual = response.getEntity().toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getBookBadReasonseNullTest() {
		Response response = new MediaRest(service).getBook(null, MAGIC_TOKEN);
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+400\",\"data\":\"error\",\"message\":\"Bad Request\",\"data-length\":-1,\"status\":400}";
		String actual = response.getEntity().toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void addBookTest() {
		Book testObject = new Book("test", "test", "978-3-12-732320-7");
		Response response = new MediaRest(service).addBook(testObject, MAGIC_TOKEN);
		//String expected1 = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"Author1\",\"isbn\":\"978-3-86680-192-9\",\"title\":\"Title1\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String expected2 = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"test\",\"isbn\":\"9783127323207\",\"title\":\"test\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		//String actual1 = response.getEntity().toString();
		String actual2 = new MediaRest(service).getBook("978-3-12-732320-7", MAGIC_TOKEN).getEntity().toString();
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void updateBookTest() {
		Book orginal = new Book("test", "test", "978-3-12-732320-7");
		Book modified = new Book("TEST", "TEST", "978-3-12-732320-7");
		new MediaRest(service).addBook(orginal, MAGIC_TOKEN);
		String actual1 = new MediaRest(service).getBook("978-3-12-732320-7", MAGIC_TOKEN).getEntity().toString();
		new MediaRest(service).updateBook(modified, "978-3-12-732320-7", MAGIC_TOKEN);
		String actual2 = new MediaRest(service).getBook("978-3-12-732320-7", MAGIC_TOKEN).getEntity().toString();
		String expected1 = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"test\",\"isbn\":\"9783127323207\",\"title\":\"test\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String expected2 = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"TEST\",\"isbn\":\"9783127323207\",\"title\":\"TEST\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void addDiscTest() {
		Disc testObject = new Disc("Disc1", "5055011702189", "Dirctor1", 1);
		Response response = new MediaRest(service).addDisc(testObject, MAGIC_TOKEN);
		String expected2 = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"director\":\"Dirctor1\",\"title\":\"Disc1\",\"barcode\":\"5055011702189\",\"fsk\":1}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String actual2 = new MediaRest(service).getDisc("5055011702189", MAGIC_TOKEN).getEntity().toString();
		assertEquals(expected2, actual2);
	}

	@Test
	public void updateDiscTest() {
		Disc orginal = new Disc("Disc1", "5055011702189", "Dirctor1", 1);
		Disc modified = new Disc("Disc1", "5055011702189", "Dirctor2", 2);
		new MediaRest(service).addDisc(orginal, MAGIC_TOKEN);
		String actual1 = new MediaRest(service).getDisc("5055011702189", MAGIC_TOKEN).getEntity().toString();
		new MediaRest(service).updateDisc(modified, "5055011702189", MAGIC_TOKEN);
		String actual2 = new MediaRest(service).getDisc("5055011702189", MAGIC_TOKEN).getEntity().toString();
		String expected1 = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"director\":\"Dirctor1\",\"title\":\"Disc1\",\"barcode\":\"5055011702189\",\"fsk\":1}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		assertEquals(expected1, actual1);
	}
	
	@Test
	public void getDiscsTest() {
		Response response = new MediaRest(service).getDiscs(MAGIC_TOKEN);
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"director\":\"Dirctor1\",\"title\":\"Disc1\",\"barcode\":\"4059251015567\",\"fsk\":1}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String actual = response.getEntity().toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getDiscTest() {
		Response response = new MediaRest(service).getDisc("4059251015567", MAGIC_TOKEN);
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"director\":\"Dirctor1\",\"title\":\"Disc1\",\"barcode\":\"4059251015567\",\"fsk\":1}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String actual = response.getEntity().toString();
		assertEquals(expected, actual);
	}


	// wrong tokens
	@Test
	public void getBookTestInvalidToken() {
		Response response = new MediaRest(service).getBook("978-3-86680-192-9", "lulu");
		assertTrue(response.getEntity().toString().contains("418"));
	}

	@Test
	public void getBooksTestInvalidToken() {
		Response response = new MediaRest(service).getBooks("lulu");
		assertTrue(response.getEntity().toString().contains("418"));
	}

	@Test
	public void getDiscsTestInvalidToken() {
		Response response = new MediaRest(service).getDiscs("lulu");
		assertTrue(response.getEntity().toString().contains("418"));
	}
	@Test
	public void getDiscTestInvalidToken() {
		Response response = new MediaRest(service).getBook("4059251015567", "lulu");
		assertTrue(response.getEntity().toString().contains("418"));
	}
}
