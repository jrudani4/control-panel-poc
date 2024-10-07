package com.cp.controller;

import com.cp.dtos.FormFieldTypes;
import com.cp.entity.FormFields;
import com.cp.service.FormFieldsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/form-fields")
@RequiredArgsConstructor
public class FormFieldsController {

    private final FormFieldsService formFieldsService;

    @GetMapping
    public ResponseEntity<List<FormFields>> getAllFormFields() {
        return ResponseEntity.ok(formFieldsService.getAllFormFields());
    }

    @GetMapping("/types")
    public ResponseEntity<List<FormFieldTypes>> getAllFormFieldTypes() {
        return ResponseEntity.ok(formFieldsService.getAllFormFieldTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormFields> getFormFieldsById(@PathVariable Long id) {
        return ResponseEntity.ok(formFieldsService.getFormFieldsById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<FormFields> createFormFields(@RequestBody FormFields formFields) {
        return ResponseEntity.ok(formFieldsService.createFormField(formFields));
    }
}
