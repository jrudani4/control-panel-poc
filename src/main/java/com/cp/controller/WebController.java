package com.cp.controller;

import com.cp.dtos.UserResponseDTO;
import com.cp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/app")
@RequiredArgsConstructor
public class WebController {

    private final UserService userService;

    @GetMapping("/users/all")
    public String allUsers(Model model) {
        List<UserResponseDTO> allUsers = userService.getAllUsers();
        model.addAttribute("title", "Control Panel - Users");
        model.addAttribute("users", allUsers);
        return "users-view";
    }

    @GetMapping("/user/{userId}/form-fields")
    public String formFieldsByUserId(Model model, @PathVariable Long userId) {
        model.addAttribute("title", "Control Panel - Form Fields");
        model.addAttribute("form_fields", userService.getAllFormFieldsByUserId(userId));
        return "form-fields-view";
    }
}
