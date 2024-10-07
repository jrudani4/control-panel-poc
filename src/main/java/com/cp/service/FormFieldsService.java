package com.cp.service;

import com.cp.dtos.FormFieldTypes;
import com.cp.entity.FormFields;
import com.cp.exception.exceptions.ErrorWhileSavingRecordException;
import com.cp.exception.exceptions.NoRecordsFoundException;
import com.cp.exception.exceptions.ResourceNotFoundException;
import com.cp.repository.FormFieldsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormFieldsService {

    private final FormFieldsRepository formFieldsRepository;

    public List<FormFields> getAllFormFields() {
        List<FormFields> fieldsList = formFieldsRepository.findAll();
        if (fieldsList.isEmpty()) {
            throw new NoRecordsFoundException("No Records Found!");
        }
        return fieldsList;
    }

    public FormFields getFormFieldsById(Long id) {
        return formFieldsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Form field Not Found with id: " + id));
    }

    public FormFields createFormField(FormFields formFields) {
        try {
            return formFieldsRepository.save(formFields);
        } catch (Exception e) {
            throw new ErrorWhileSavingRecordException("Error while creating form field, Please check the request!");
        }
    }

    public List<FormFieldTypes> getAllFormFieldTypes() {
        if (List.of(FormFieldTypes.values()).isEmpty()) {
            throw new NoRecordsFoundException("No Records Found!");
        }
        return List.of(FormFieldTypes.values());
    }
}
