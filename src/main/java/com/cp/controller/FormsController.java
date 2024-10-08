package com.cp.controller;

import com.cp.entity.Forms;
import com.cp.service.FormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/forms")
public class FormsController {

    private final FormService formService;

    @GetMapping("/{id}")
    public ResponseEntity<Forms> getFormById(@PathVariable Long id) {
        return ResponseEntity.ok(formService.getFormById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Forms> createForm(@RequestBody Forms forms) {
        return ResponseEntity.ok(formService.createForm(forms));
    }
}
