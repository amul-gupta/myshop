package com.myshop.user.service.impl;

import com.myshop.user.dto.UserRequestDto;
import com.myshop.user.dto.UserResponseDto;
import com.myshop.user.entity.User;
import com.myshop.user.exception.ResourceNotFoundException;
import com.myshop.user.mapper.UserMapper;
import com.myshop.user.repository.UserRepository;
import com.myshop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new ResourceNotFoundException(
                    "User already exists with email: " + userRequestDto.getEmail()
            );
        }

        User user = UserMapper.toEntity(userRequestDto);

        User savedUser = userRepository.save(user);

        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserResponseDto getUserById(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id
                        ));

        return UserMapper.toDto(user);
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with email: " + email
                        ));

        return UserMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    @Override
    public UserResponseDto updateUser(UUID id, UserRequestDto userRequestDto) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id
                        ));

        if (!existingUser.getEmail().equals(userRequestDto.getEmail())
                && userRepository.existsByEmail(userRequestDto.getEmail())) {

            throw new ResourceNotFoundException(
                    "User already exists with email: " + userRequestDto.getEmail()
            );
        }

        UserMapper.updateEntity(existingUser, userRequestDto);

        User updatedUser = userRepository.save(existingUser);

        return UserMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id
                        ));

        userRepository.delete(user);
    }
}