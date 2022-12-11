package com.sgic.library.services;

import com.sgic.library.entities.Book;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.List;
@Configurable
public interface BookService {
	void saveBook(Book book);// save book
	List<Book> getAllBook();		//	Get All Book
	Book findBookById(String id); // find book by id - bookID
	Book deleteBookById(String id);	//	Delete Book
	void updateBook(Book book);		//	Update Book
	
}
