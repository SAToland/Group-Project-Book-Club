package com.seth.BookClub.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.seth.BookClub.Models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>{

	List<Book> findAll();
}
