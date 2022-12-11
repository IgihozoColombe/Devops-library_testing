package com.sgic.library.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sgic.library.entities.Book;
import com.sgic.library.services.BookServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BookControllerTests.class)
public class BookControllerTests{

    @MockBean
    private BookServiceImpl bookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void givenBookObject_whenCreateBook_thenReturnSavedBook() throws Exception{

        Book book = new Book();
        book.setBookId("12");
        book.setBookISBN("34344");
        book.setBookName("random name");
        ResultActions response = mockMvc.perform(post("/saveBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)));


        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookId",
                        is(book.getBookId())))
                .andExpect(jsonPath("$.bookName",
                        is(book.getBookName())))
                .andExpect(jsonPath("$.bookISBN",
                        is(book.getBookISBN())));

    }

    @Test
    public void findAll_Success() throws Exception {
        Book book=new Book();
        book.setBookId("10");
        book.setBookISBN("7667");
        book.setBookName("Beyond the lights");
        List<Book> asList = Arrays.asList(book);
        when(bookService.getAllBook()).thenReturn(asList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/findAll")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void givenBookId_whenGetBookId_thenReturnBookObject() throws Exception{
        Book book=new Book();
        book.setBookId("10");
        book.setBookISBN("7667");
        book.setBookName("Beyond the lights");
        bookService.saveBook(book);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/getBookById/{id}", book.getBookId()));


        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.bookId",
                        is(book.getBookId())))
                .andExpect(jsonPath("$.bookName",
                        is(book.getBookName())))
                .andExpect(jsonPath("$.bookISBN",
                        is(book.getBookISBN())));

    }


    @Test
    public void givenInvalidBookId_whenGetBookId_thenReturnEmpty() throws Exception{
        Book book=new Book();
        book.setBookId("10");
        book.setBookISBN("7667");
        book.setBookName("Beyond the lights");
        bookService.saveBook(book);

        ResultActions response = mockMvc.perform(get("/api/employees/{id}", book.getBookId()));

        response.andExpect(status().isNotFound())
                .andDo(print());

    }

    // JUnit test for update employee REST API - positive scenario
    @Test
    public void givenUpdatedBook_whenUpdateBook_thenReturnUpdateBookObject() throws Exception{
        Book book=new Book();
        book.setBookId("10");
        book.setBookISBN("7667");
        book.setBookName("Beyond the lights");
        bookService.saveBook(book);

        Book newbook=new Book();
        newbook.setBookId("13");
        newbook.setBookISBN("456");
        newbook.setBookName(" new Beyond the lights");

        ResultActions response = mockMvc.perform(put("/updateBook", book.getBookId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newbook)));


        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.bookId",
                        is(book.getBookId())))
                .andExpect(jsonPath("$.bookName",
                        is(book.getBookName())))
                .andExpect(jsonPath("$.bookISBN",
                        is(book.getBookISBN())));
    }

    @Test
    public void givenBookId_whenDeleteBook_thenReturn200() throws Exception{
        Book book=new Book();
        book.setBookId("13");
        book.setBookISBN("456");
        book.setBookName(" new Beyond the lights");
        bookService.saveBook(book);

        ResultActions response = mockMvc.perform(delete("/deleteBook/{id}", book.getBookId()));

        response.andExpect(status().isOk())
                .andDo(print());
    }
}