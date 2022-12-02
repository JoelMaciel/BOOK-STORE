package br.com.joel.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.joel.controllers.BookController;
import br.com.joel.controllers.PersonController;
import br.com.joel.data.vo.v1.BookVO;
import br.com.joel.exceptions.RequiredObjectIsNullException;
import br.com.joel.exceptions.ResourceNotFoundException;
import br.com.joel.mapper.DozerMapper;
import br.com.joel.model.Book;
import br.com.joel.repositories.BookRepository;

@Service
public class BookService {
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());

	@Autowired
	private BookRepository bookRepository;

	public List<BookVO> findAll() {
		logger.info("Finding all book");

		var books = DozerMapper.parseListObjects(bookRepository.findAll(), BookVO.class);
		books.stream().forEach(book -> book
				.add(linkTo(methodOn(PersonController.class).findById(book.getBookId())).withSelfRel()));

		return books;
	}

	public BookVO findById(Long bookId) {

		logger.info("Finding one BookVO !");

		var book = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID !"));

		var bookVO = DozerMapper.parseObject(book, BookVO.class);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookId)).withSelfRel());
		return bookVO;
	}

	@Transactional
	public BookVO create(BookVO bookVo) {

		if (bookVo == null) {
			throw new RequiredObjectIsNullException();
		} else {

			logger.info("Creating one BookVO!");

			var book = DozerMapper.parseObject(bookVo, Book.class);
			var newBookVo = DozerMapper.parseObject(bookRepository.save(book), BookVO.class);
			newBookVo.add(linkTo(methodOn(PersonController.class)
					.findById(newBookVo.getBookId())).withSelfRel());
			return newBookVo;
		}
	}


	@Transactional
	public BookVO update(BookVO bookVo) {

		if (bookVo == null) {
			throw new RequiredObjectIsNullException();
		} else {
			logger.info("Update one BookVO");

			var book = bookRepository.findById(bookVo.getBookId())
					.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID !"));

			book.setAuthor(bookVo.getAuthor());
			book.setLaunchDate(bookVo.getLaunchDate());
			book.setPrice(bookVo.getPrice());
			book.setTitle(bookVo.getTitle());

			var newBookVo = DozerMapper.parseObject(bookRepository.save(book), BookVO.class);
			newBookVo.add(linkTo(methodOn(BookController.class).findById(newBookVo.getBookId())).withSelfRel());

			return newBookVo;

		}
	}

	public void delete(Long bookId) {
		logger.info("Delete one BookVO");

		var BookVO = bookRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID !"));

		bookRepository.delete(BookVO);
	}

}
