package com.ahmetemre90.bookapp.configure;

import com.ahmetemre90.bookapp.model.Role;
import com.ahmetemre90.bookapp.model.User;
import com.ahmetemre90.bookapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupConfig implements CommandLineRunner {

    private final UserService userService;

    public StartupConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = User.builder()
                .username("ahmett")
                .password("123456")
                .role(Role.ADMIN)
                .build();

//        User user1exist = userService.findUserByUsername(user1.getUsername());
//        if (user1exist == null) {
        userService.createUser(user1);
//        }


        User user2 = User.builder()
                .username("user1")
                .password("123456")
                .role(Role.USER)
                .build();

//        User user2exist = userService.findUserByUsername(user2.getUsername());
//        if (user2exist == null) {
        userService.createUser(user2);
//        }
    }
}
