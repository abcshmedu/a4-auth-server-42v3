package edu.hm.schmid71.shareit.jackson;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.hm.rfurch.shareit.model.Book;

public class JacksonMappingTest {
	private final Book book = new Book("Buch1", "Autor1", "ISBN1");
	private final String json1 = "{\"title\":\"Buch1\",\"author\":\"Autor1\",\"isbn\":\"ISBN1\"}";
	private final String json2 = "{\"author\":\"Autor1\",\"isbn\":\"ISBN1\",\"title\":\"Buch1\"}";
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testToJson() throws JsonProcessingException {
		String s = mapper.writeValueAsString(book);
		System.out.println(json1);
		System.out.println(s);
		assertEquals(json1,s);
	}
	
	@Test
	public void testFromJson1() throws IOException {
		Book b = mapper.readValue(json1, Book.class);
		assertEquals(book, b);
	}
	
	@Test
	public void testFromJson2() throws IOException {
		Book b = mapper.readValue(json2, Book.class);
		assertEquals(book, b);
	}
}
