package com.sgic.library.services;

import com.sgic.library.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sgic.library.repositories.BookRepositories;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepositories bookRespository;
	
	public void saveBook(Book book) {
		bookRespository.save(book);
	}
	
	@Override
	public List<Book> getAllBook(){

		return bookRespository.findAll();
	}
	
	
	@Override
	public Book findBookById(String id) {
		return bookRespository.findBookByBookId(id);
	}
	
	public Book deleteBookById(String id) {
		bookRespository.deleteById(id);
		 return null;
	}

	@Override
	public void updateBook(Book book) {
		bookRespository.save(book);		
	}


}
