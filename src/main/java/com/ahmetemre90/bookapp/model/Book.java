package com.ahmetemre90.bookapp.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity {

    private String title;

    private String author;

    private String publisher;

    private Integer lastPage;

    private Integer totalPage;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne
    private Image image;

    private Long userId;
}
