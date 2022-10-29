package com.ahmetemre90.bookapp.service;

import com.ahmetemre90.bookapp.dto.BookListItemResponse;
import com.ahmetemre90.bookapp.dto.BookResponse;
import com.ahmetemre90.bookapp.dto.CategoryType;
import com.ahmetemre90.bookapp.dto.SaveBookRequest;
import com.ahmetemre90.bookapp.model.Book;
import com.ahmetemre90.bookapp.model.BookStatus;
import com.ahmetemre90.bookapp.model.Category;
import com.ahmetemre90.bookapp.repository.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    public BookService(BookRepository bookRepository, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
    }

    private BookResponse convertToBookResponse(Book model) {
        return BookResponse.builder()
                .title(model.getTitle())
                .author(model.getAuthor())
                //.imageUrl(model.getImage().getImageUrl())

                .id((model.getId()))

                .build();
    }

    @Transactional
    public BookListItemResponse saveBook(SaveBookRequest saveBookRequest) {

        Category category = categoryService.getCategory(saveBookRequest.getCategoryId());

        Book book = Book.builder()
                .title(saveBookRequest.getTitle())
                .author(saveBookRequest.getAuthor())
                .publisher(saveBookRequest.getPublisher())
                .lastPage(saveBookRequest.getLastPage())
                .totalPage(saveBookRequest.getTotalPage())
                .bookStatus(saveBookRequest.getBookStatus())
                .category(category)
                .build();

        Book fromDb = bookRepository.save(book);

        return BookListItemResponse.builder()
                .id(fromDb.getId())
                .title(fromDb.getTitle())
                .author(fromDb.getAuthor())
                .publisher(fromDb.getPublisher())
                .lastPage(fromDb.getLastPage())
                .totalPage(fromDb.getTotalPage())
                .bookStatus(fromDb.getBookStatus())
                .categoryName(fromDb.getCategory().getName())
                .build();
    }

    public List<BookResponse> listBooks(int page, int size) {

        return bookRepository.findAll(PageRequest.of(page, size))
                .get()
                .map(this::convertToBookResponse)
                .collect(Collectors.toList());
    }

    public List<BookResponse> searchByCategory(CategoryType categoryType) {

        Category category = categoryService.getCategory(categoryType.getValue());

        return category.getBooks()
                .stream()
                .map(this::convertToBookResponse)
                .collect(Collectors.toList());
    }

    public List<BookResponse> searchByBookStatus(BookStatus bookStatus) {

        return bookRepository.findByBookStatus(bookStatus)
                .stream()
                .map(book -> BookResponse.builder()
                        .id(book.getId())
                        //.imageUrl(book.getImage().getImageUrl())
                        .build())
                .collect(Collectors.toList());
    }

    public List<BookResponse> searchByBookTitle(String bookTitle) {

        return bookRepository.findByTitleContainsIgnoreCase(bookTitle)
                .stream()
                .map(book -> BookResponse.builder()
                        .id(book.getId())
                        //.imageUrl(book.getImage().getImageUrl())
                        .build())
                .collect(Collectors.toList());
    }

}
