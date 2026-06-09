package com.myshop.user.service;

import com.myshop.user.dto.UserRequestDto;
import com.myshop.user.dto.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUserById(UUID id);

    UserResponseDto getUserByEmail(String email);

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto);

    void deleteUser(UUID id);
}