package com.sgic.library.services;
//public class BookServiceTests {
//}

import com.sgic.library.entities.Book;
import com.sgic.library.repositories.BookRepositories;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class BookServiceTests {
    @Mock
    private BookRepositories bookRepositories;
    @InjectMocks
    private BookService bookService =new BookServiceImpl();
    @Test
    public void shouldReturnAllBooks() {
        List<Book> books = new ArrayList();
        books.add(new Book());
        given(bookRepositories.findAll()).willReturn(books);
        List<Book> expected = bookService.getAllBook();
        assertEquals(expected, books);
        verify(bookRepositories).findAll();
    }

}