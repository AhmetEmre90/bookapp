package com.ahmetemre90.bookapp.dto;

import com.ahmetemre90.bookapp.model.BookStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;

@Data
public final class SaveBookRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String publisher;

    @NotNull
    private Integer lastPage;

    @NotNull
    private Integer totalPage;

    @NotNull
    private BookStatus bookStatus;

    @NotNull
    private Long categoryId;

    private File image;

}
