package com.ahmetemre90.bookapp.dto;

import com.ahmetemre90.bookapp.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String username;

    private Role role;

    private Long id;
}
