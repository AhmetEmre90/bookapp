package com.ahmetemre90.bookapp.repository;

import com.ahmetemre90.bookapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
