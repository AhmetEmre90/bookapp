package com.ahmetemre90.bookapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "category") // book entity'sindeki ilişkili olduğu property'nin adı
    @JsonIgnore
    private List<Book> books;
}
