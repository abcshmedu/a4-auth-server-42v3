package edu.hm.rfurch.shareit.restapi;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

import edu.hm.rfurch.shareit.data.ResourceManager;
import edu.hm.rfurch.shareit.model.Book;
import edu.hm.rfurch.shareit.model.Disc;
import edu.hm.rfurch.shareit.model.IBook;

public class MediaRestTest {

	@Test
	public void getBooksTest() {
		Book[] expecteds = new Book[1];
		expecteds[0] = new Book("Title1", "Author1", "978-3-86680-192-9");
		Book[] actuals = new MediaRest().getBooks();
		assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public void getBookTest() {
		Response response = new MediaRest().getBook("978-3-86680-192-9");
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"Author1\",\"isbn\":\"978-3-86680-192-9\",\"title\":\"Title1\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String actual = response.getEntity().toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getBookBadResonseIsbnTest() {
		Response response = new MediaRest().getBook("");
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+400\",\"data\":\"error\",\"message\":\"Bad Request\",\"data-length\":-1,\"status\":400}";
		String actual = response.getEntity().toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getBookBadResonseNullTest() {
		Response response = new MediaRest().getBook(null);
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+400\",\"data\":\"error\",\"message\":\"Bad Request\",\"data-length\":-1,\"status\":400}";
		String actual = response.getEntity().toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void addBookTest() {
		Book testObject = new Book("test", "test", "978-3-12-732320-7");
		Response response = new MediaRest().addBook(testObject);
		//String expected1 = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"Author1\",\"isbn\":\"978-3-86680-192-9\",\"title\":\"Title1\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String expected2 = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"test\",\"isbn\":\"978-3-12-732320-7\",\"title\":\"test\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		//String actual1 = response.getEntity().toString();
		String actual2 = new MediaRest().getBook("978-3-12-732320-7").getEntity().toString();
		ResourceManager.dataAccess().remove(testObject);
		//assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void updateBookTest() {
		Book orginal = new Book("test", "test", "978-3-12-732320-7");
		Book modified = new Book("TEST", "TEST", "978-3-12-732320-7");
		new MediaRest().addBook(orginal);
		String actual1 = new MediaRest().getBook("978-3-12-732320-7").getEntity().toString();
		new MediaRest().updateBook(modified, "978-3-12-732320-7");
		String actual2 = new MediaRest().getBook("978-3-12-732320-7").getEntity().toString();
		String expected1 = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"test\",\"isbn\":\"978-3-12-732320-7\",\"title\":\"test\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String expected2 = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"TEST\",\"isbn\":\"978-3-12-732320-7\",\"title\":\"TEST\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";		
		ResourceManager.dataAccess().remove(modified);
		System.out.println(actual1);
		System.out.println(expected1);
		System.out.println(actual2);
		System.out.println(expected2);
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}
	
	@Test
	public void getDiscsTest() {
		Disc[] expecteds = new Disc[1];
		expecteds[0] = new Disc("Disc1", "4059251015567", "Dirctor1", 1);
		Disc[] actuals = new MediaRest().getDiscs();
		assertArrayEquals(expecteds, actuals);
	}
	
	@Test
	public void getDiscTest() {
		Response response = new MediaRest().getDisc("4059251015567");
		String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"director\":\"Dirctor1\",\"title\":\"Disc1\",\"barcode\":\"4059251015567\",\"fsk\":1}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
		String actual = response.getEntity().toString();
		assertEquals(expected, actual);
	}

}
