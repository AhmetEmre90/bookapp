package com.ahmetemre90.bookapp.repository;

import com.ahmetemre90.bookapp.model.Book;
import com.ahmetemre90.bookapp.model.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainsIgnoreCase(String bookTitle);

    List<Book> findByBookStatus(BookStatus bookStatus);

}
