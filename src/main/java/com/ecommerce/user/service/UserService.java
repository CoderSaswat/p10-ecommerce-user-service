package com.ecommerce.user.service;

import com.ecommerce.user.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(UUID id);
    List<UserDto> getAllUsers();
}
