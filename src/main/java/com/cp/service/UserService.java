package com.cp.service;

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

    public User createUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ErrorWhileSavingRecordException("Error while creating user, Please check the request!");
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found with id: " + id));
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoRecordsFoundException("No Records Found!");
        }
        return users;
    }

    public List<FormFields> getAllFormFieldsByUserId(Long userId) {
        List<FormFields> fieldsByUserId = formFieldsRepository.findFormFieldsByUserId(userId);
        if (fieldsByUserId.isEmpty()) {
            throw new NoRecordsFoundException("No Records Found!");
        }
        return fieldsByUserId;
    }
}