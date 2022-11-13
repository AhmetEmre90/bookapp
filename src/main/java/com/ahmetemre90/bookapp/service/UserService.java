package com.ahmetemre90.bookapp.service;

import com.ahmetemre90.bookapp.dto.ErrorCode;
import com.ahmetemre90.bookapp.dto.UserResponse;
import com.ahmetemre90.bookapp.exception.GenericException;
import com.ahmetemre90.bookapp.model.User;
import com.ahmetemre90.bookapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // kullanıcının şifresini encode edilmiş şekilde kaydediyoruz.

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .username(savedUser.getUsername())
                .role(savedUser.getRole())
                .id(savedUser.getId())
                .build();
    }

    public UserResponse getUser(String username) {
        User foundUser = findUserByUsername(username);

        UserResponse userResponse = UserResponse.builder()
                .username(foundUser.getUsername())
                .role(foundUser.getRole())
                .id(foundUser.getId())
                .build();
        return userResponse;
    }

    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> GenericException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .errorCode(ErrorCode.USER_NOT_FOUND)
                        .errorMessage("user not found by given username!")
                        .build());
        return user;
    }
}
