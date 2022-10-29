package com.ahmetemre90.bookapp.dto;

import com.ahmetemre90.bookapp.model.BookStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.File;

@Data
@SuperBuilder
@NoArgsConstructor
public class BookResponse {

    private Long id;

    private String title;

    private String author;

    private String publisher;

    private Integer lastPage;

    private Integer totalPage;

    private BookStatus bookStatus;

    private Long categoryId;

    private File image;

    private String imageUrl;
}
