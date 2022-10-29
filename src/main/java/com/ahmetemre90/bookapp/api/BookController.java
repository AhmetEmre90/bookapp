package com.ahmetemre90.bookapp.api;

import com.ahmetemre90.bookapp.dto.BookListItemResponse;
import com.ahmetemre90.bookapp.dto.BookResponse;
import com.ahmetemre90.bookapp.dto.CategoryType;
import com.ahmetemre90.bookapp.dto.SaveBookRequest;
import com.ahmetemre90.bookapp.model.BookStatus;
import com.ahmetemre90.bookapp.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/book")
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/save")
    public ResponseEntity<BookListItemResponse> saveBook(@Valid @RequestBody SaveBookRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.saveBook(request));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> listBook(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.listBooks(page, size));
    }

    @GetMapping("list/category/{categoryType}")
    public ResponseEntity<List<BookResponse>> listByCategory(@PathVariable CategoryType categoryType) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.searchByCategory(categoryType));
    }

    @GetMapping("list/book-status/{bookStatus}")
    public ResponseEntity<List<BookResponse>> listByBookStatus(@PathVariable BookStatus bookStatus) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.searchByBookStatus(bookStatus));
    }

    @GetMapping("list/book-title/{bookTitle}")
    public ResponseEntity<List<BookResponse>> listByBookTitle(@PathVariable String bookTitle) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookService.searchByBookTitle(bookTitle));
    }

}
