package com.sgic.library.repositories;

import com.sgic.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositories extends JpaRepository<Book, String> {
	Book findBookByBookId(String id);
}
