package com.cp.controller;

import com.cp.dtos.UserRequestDTO;
import com.cp.dtos.UserResponseDTO;
import com.cp.entity.FormFields;
import com.cp.entity.User;
import com.cp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.createUser(userRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/form-fields")
    public ResponseEntity<List<FormFields>> getFormFields(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.getAllFormFieldsByUserId(user.getId()));
    }
}
