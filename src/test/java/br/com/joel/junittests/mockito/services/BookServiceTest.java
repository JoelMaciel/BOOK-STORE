package br.com.joel.junittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.joel.data.vo.v1.BookVO;
import br.com.joel.exceptions.RequiredObjectIsNullException;
import br.com.joel.junittests.mapper.mocks.MockBook;
import br.com.joel.model.Book;
import br.com.joel.repositories.BookRepository;
import br.com.joel.services.BookService;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	
	MockBook mockBook;
	
	@InjectMocks
	private BookService bookService;
	
	@Mock
	BookRepository bookRepository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		mockBook = new MockBook();
		MockitoAnnotations.openMocks(this);
		
	}
	
	@Test
	void testFindById() {
		Book book = mockBook.mockEntity(1);
		book.setBookId(1L);
		
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		
		var result = bookService.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getBookId());
		assertNotNull(result.getLinks());
		
	
		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAuthor());
		assertEquals("Some Title1", result.getTitle());
		assertEquals(25D, result.getPrice());
		assertNotNull(result.getLaunchDate());
		
		
	}
	
	@Test
	void testCreate() {
		Book book = mockBook.mockEntity(1);
		book.setBookId(1L);
		
		Book persisted = book;
		persisted.setBookId(1L);
		
		BookVO bookVo = mockBook.mockVO(1);
		bookVo.setBookId(1L);
		
		when(bookRepository.save(book)).thenReturn(persisted);
		
		var result = bookService.create(bookVo);
		
		assertNotNull(result);
		assertNotNull(result.getBookId());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAuthor());
		assertEquals("Some Title1", result.getTitle());
		assertEquals(25D, result.getPrice());
		assertNotNull(result.getLaunchDate());
		
	}
	
	@Test
	void testCreateWithNullBook() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			bookService.create(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
		
	}
	
	@Test
	void testUpdate() {
		Book book = mockBook.mockEntity(1);
		book.setBookId(1L);
		
		Book persisted = book;
		persisted.setBookId(1L);
		
		BookVO bookVo = mockBook.mockVO(1);
		bookVo.setBookId(1L);
		
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		when(bookRepository.save(book)).thenReturn(persisted);
		
		var result = bookService.update(bookVo);
		
		assertNotNull(result);
		assertNotNull(result.getBookId());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", result.getAuthor());
		assertEquals("Some Title1", result.getTitle());
		assertEquals(25D, result.getPrice());
		assertNotNull(result.getLaunchDate());
	}
	
	@Test
	void testUpdateWithNullBook() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			bookService.update(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
		
	}
	
	@Test
	void testDelete() {
		Book book = mockBook.mockEntity(1);
		book.setBookId(1L);
		
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
		
		bookService.delete(1L);
	}
	
	@Test
	void testFindAll() {
		List<Book> books = mockBook.mockEntityList();
		
		when(bookRepository.findAll()).thenReturn(books);
		
		var book = bookService.findAll();
		
		assertNotNull(book);
		assertEquals(14, book.size());
		
		var bookOne = book.get(1);
		
		assertNotNull(bookOne);
		assertNotNull(bookOne.getBookId());
		assertNotNull(bookOne.getLinks());
		
		assertTrue(bookOne.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		assertEquals("Some Author1", bookOne.getAuthor());
		assertEquals("Some Title1", bookOne.getTitle());
		assertEquals(25D, bookOne.getPrice());
		assertNotNull(bookOne.getLaunchDate());
		
		var bookFour = book.get(4);
		
		assertNotNull(bookFour);
		assertNotNull(bookFour.getBookId());
		assertNotNull(bookFour.getLinks());
		
		assertTrue(bookFour.toString().contains("links: [</api/books/v1/4>;rel=\"self\"]"));
		assertEquals("Some Author4", bookFour.getAuthor());
		assertEquals("Some Title4", bookFour.getTitle());
		assertEquals(25D, bookFour.getPrice());
		assertNotNull(bookFour.getLaunchDate());
		
		var bookSeven = book.get(7);
		
		assertNotNull(bookSeven);
		assertNotNull(bookSeven.getBookId());
		assertNotNull(bookSeven.getLinks());
		
		assertTrue(bookSeven.toString().contains("links: [</api/books/v1/7>;rel=\"self\"]"));
		assertEquals("Some Author7", bookSeven.getAuthor());
		assertEquals("Some Title7", bookSeven.getTitle());
		assertEquals(25D, bookSeven.getPrice());
		assertNotNull(bookSeven.getLaunchDate());
	}
	

}





