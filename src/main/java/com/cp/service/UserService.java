package com.cp.service;

import com.cp.dtos.UserRequestDTO;
import com.cp.dtos.UserResponseDTO;
import com.cp.entity.FormFields;
import com.cp.entity.User;
import com.cp.exception.exceptions.ErrorWhileSavingRecordException;
import com.cp.exception.exceptions.NoRecordsFoundException;
import com.cp.exception.exceptions.ResourceNotFoundException;
import com.cp.repository.FormFieldsRepository;
import com.cp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FormFieldsRepository formFieldsRepository;

    public User createUser(UserRequestDTO userRequestDTO) {
        try {
            return userRepository.save(User.builder()
                    .name(userRequestDTO.getName())
                    .email(userRequestDTO.getEmail())
                    .password(userRequestDTO.getPassword())
                    .build());
        } catch (Exception e) {
            throw new ErrorWhileSavingRecordException("Error while creating user, Please check the request!");
        }
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found with id: " + id));
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .roles(user.getRoles())
                .build();
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoRecordsFoundException("No Records Found!");
        }
        return users.stream().map(user -> UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .createdAt(user.getCreatedAt())
                .roles(user.getRoles())
                .build()).toList();
    }

    public List<FormFields> getAllFormFieldsByUserId(Long userId) {
        List<FormFields> fieldsByUserId = formFieldsRepository.findFormFieldsByUserId(userId);
        if (fieldsByUserId.isEmpty()) {
            throw new NoRecordsFoundException("No Records Found!");
        }
        return fieldsByUserId;
    }
}