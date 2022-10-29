package com.ahmetemre90.bookapp.repository;

import com.ahmetemre90.bookapp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Book> {
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String categoryName);
}
