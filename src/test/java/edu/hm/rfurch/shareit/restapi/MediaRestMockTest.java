package edu.hm.rfurch.shareit.restapi;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import edu.hm.rfurch.shareit.logic.IMediaService;
import edu.hm.rfurch.shareit.model.Disc;
import edu.hm.rfurch.shareit.model.IBook;
import edu.hm.rfurch.shareit.model.IDisc;

import org.junit.Before;
import org.junit.Test;

import edu.hm.rfurch.shareit.model.Book;
import edu.hm.rfurch.shareit.logic.MediaServiceResult;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

public class MediaRestMockTest {

    final IBook b = new Book("Title1", "Author1", "978-3-86680-192-9");
    final IDisc d = new Disc("Disc1", "4059251015567", "Dirctor1", 1);
    private static final String MAGIC_TOKEN = "MAGICTOKEN";
    private IMediaService mockService;
    private MediaRest rest;
    
    @Before
    public void setup() {
        this.mockService = mock(IMediaService.class);
        this.rest = new MediaRest(mockService);
    }
    
    @Test
    public void getBooksTest() {
        Optional<MediaServiceResult> mockResponse = Optional.of(MediaServiceResult.OK.setResponseData(b));
        when(mockService.getBooks()).thenReturn(mockResponse);
        
        Response response = rest.getBooks(MAGIC_TOKEN);
        String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"Author1\",\"isbn\":\"9783866801929\",\"title\":\"Title1\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
        String actuals = response.getEntity().toString();
        assertEquals(expected, actuals);
    }
    
    @Test
    public void getBookTest() {
        Optional<MediaServiceResult> mockResponse = Optional.of(MediaServiceResult.OK.setResponseData(b));
        when(mockService.getBook(b.getIsbn())).thenReturn(mockResponse);
        
        Response response = rest.getBook(b.getIsbn(), MAGIC_TOKEN);
        String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"author\":\"Author1\",\"isbn\":\"9783866801929\",\"title\":\"Title1\"}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
        String actual = response.getEntity().toString();
        assertEquals(expected, actual);
    }


    
    @Test
    public void getBookBadResponseIsbnTest() {
        Optional<MediaServiceResult> mockResponse = Optional.empty();
        when(mockService.getBook("")).thenReturn(mockResponse);
        
        Response response = rest.getBook("", MAGIC_TOKEN);
        String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+400\",\"data\":\"error\",\"message\":\"Bad Request\",\"data-length\":-1,\"status\":400}";
        String actual = response.getEntity().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void getBookBadResponseNullTest() {
        when(mockService.getBook(null)).thenThrow(new NullPointerException());
        
        Response response = rest.getBook(null, MAGIC_TOKEN);
        String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+400\",\"data\":\"error\",\"message\":\"Bad Request\",\"data-length\":-1,\"status\":400}";
        String actual = response.getEntity().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void addBookTest() {
        Book testObject = new Book("test", "test", "978-3-12-732320-7");
        Optional<MediaServiceResult> mockResponse = Optional.of(MediaServiceResult.OK);
        when(mockService.addBook(testObject)).thenReturn(mockResponse);
        
        Response response = rest.addBook(testObject, MAGIC_TOKEN);
        String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":\"error\",\"message\":\"OK\",\"data-length\":-1,\"status\":200}";
        String actual = response.getEntity().toString();
        assertEquals(expected, actual);
        verify(mockService).addBook(testObject);
    }
    
    @Test
    public void updateBookTest() {
        Book modified = new Book("TEST", "TEST", b.getIsbn());
        Optional<MediaServiceResult> mockResponse = Optional.of(MediaServiceResult.OK);
        when(mockService.updateBook(modified)).thenReturn(mockResponse);
        
        Response response = rest.updateBook(modified, b.getIsbn(), MAGIC_TOKEN);
        String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":\"error\",\"message\":\"OK\",\"data-length\":-1,\"status\":200}";
        String actual = response.getEntity().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void addDiscTest() {
        Disc testObject = new Disc("Disc1", "5055011702189", "Dirctor1", 1);
        Optional<MediaServiceResult> mockResponse = Optional.of(MediaServiceResult.OK);
        when(mockService.addDisc(testObject)).thenReturn(mockResponse);
        
        Response response = rest.addDisc(testObject, MAGIC_TOKEN);
        String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":\"error\",\"message\":\"OK\",\"data-length\":-1,\"status\":200}";
        String actual = response.getEntity().toString();
        assertEquals(expected, actual);
        verify(mockService).addDisc(testObject);
    }

    @Test
    public void updateDiscTest() {
        Disc modified = new Disc("Disc1", d.getBarcode(), "Dirctor2", 2);
        Optional<MediaServiceResult> mockResponse = Optional.of(MediaServiceResult.OK);
        when(mockService.updateDisc(modified)).thenReturn(mockResponse);
        
        Response response = rest.updateDisc(modified, d.getBarcode(), MAGIC_TOKEN);
        String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":\"error\",\"message\":\"OK\",\"data-length\":-1,\"status\":200}";
        String actual = response.getEntity().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void getDiscsTest() {
        Optional<MediaServiceResult> mockResponse = Optional.of(MediaServiceResult.OK.setResponseData(d));
        when(mockService.getDiscs()).thenReturn(mockResponse);
        
        Response response = rest.getDiscs(MAGIC_TOKEN);
        String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"director\":\"Dirctor1\",\"title\":\"Disc1\",\"barcode\":\"4059251015567\",\"fsk\":1}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
        String actual = response.getEntity().toString();
        assertEquals(expected, actual);
    }
    
    @Test
    public void getDiscTest() {
        Optional<MediaServiceResult> mockResponse = Optional.of(MediaServiceResult.OK.setResponseData(d));
        when(mockService.getDisc(d.getBarcode())).thenReturn(mockResponse);
        
        Response response = rest.getDisc(d.getBarcode(), MAGIC_TOKEN);
        String expected = "{\"help\":\"http://lmgtfy.com/?q=http+statuscode+200\",\"data\":[{\"director\":\"Dirctor1\",\"title\":\"Disc1\",\"barcode\":\"4059251015567\",\"fsk\":1}],\"message\":\"OK\",\"data-length\":1,\"status\":200}";
        String actual = response.getEntity().toString();
        assertEquals(expected, actual);
    }
}
