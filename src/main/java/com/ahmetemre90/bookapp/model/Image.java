package com.ahmetemre90.bookapp.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "image")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image extends BaseEntity {

    private String imageUrl;
}
