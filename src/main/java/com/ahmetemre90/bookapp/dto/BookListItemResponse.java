package com.ahmetemre90.bookapp.dto;

import com.ahmetemre90.bookapp.model.BookStatus;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.File;

@Data
@SuperBuilder
public class BookListItemResponse {

    private Long id;

    private String title;

    private String author;

    private String publisher;

    private Integer lastPage;

    private Integer totalPage;

    private BookStatus bookStatus;

    private String categoryName;

    private File image;

}
