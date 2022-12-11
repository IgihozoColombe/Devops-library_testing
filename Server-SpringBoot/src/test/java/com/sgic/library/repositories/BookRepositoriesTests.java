package com.sgic.library.repositories;

import com.sgic.library.entities.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class BookRepositoriesTests {

    @Autowired
    private BookRepositories bookRepositories;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void createBookText(){

        Book book = new Book();
        book.setBookId("1");
        book.setBookName("Harry potter");
        book.setBookISBN("252");

        Book bookUUpdated =  bookRepositories.save(book);

        Assertions.assertThat(bookUUpdated.getBookISBN()).isEqualTo("252");

    }

    @Test
    @Order(2)
    public void getBookTest(){

        Book book = bookRepositories.findById("2").get();

        Assertions.assertThat(book.getBookISBN()).isEqualTo("364");
    }

    @Test
    @Order(3)
    public void getListOfBookTest(){

        List<Book> books = bookRepositories.findAll();

        Assertions.assertThat(books.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateBookTest(){

        Book book = bookRepositories.findById("2").get();

        book.setBookName("Harry potter");
        book.setBookISBN("364");

        Book bookUpdated =  bookRepositories.save(book);

        Assertions.assertThat(bookUpdated.getBookISBN()).isEqualTo("364");

    }

    @Test
    @Order(5)
    @Rollback(value = true)
    public void deleteBookTest(){

        Book book = bookRepositories.findById("2").get();

        bookRepositories.delete(book);



        Book book1 = null;

        Optional<Book> optionalBook = bookRepositories.findById("2");

        if(optionalBook.isPresent()){
            book1 = optionalBook.get();
        }

        Assertions.assertThat(book1).isNull();
    }

}