package com.seth.BookClub.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seth.BookClub.Models.Book;
import com.seth.BookClub.Repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepo;
	
	public List<Book> findAll() {
		return bookRepo.findAll();
	}
	
	public Book createBook(Book b) {
		return bookRepo.save(b);
	}
	
	public Book findById(Long id) {
		Optional<Book> book = bookRepo.findById(id);
		if(book.isPresent()) {
			return book.get();
		}
		else {
			return null;
		}
	}
}
