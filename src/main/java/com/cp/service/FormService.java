package com.cp.service;

import com.cp.entity.Forms;
import com.cp.exception.exceptions.ErrorWhileSavingRecordException;
import com.cp.exception.exceptions.ResourceNotFoundException;
import com.cp.repository.FormsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormService {

    private final FormsRepository formsRepository;

    public Forms createForm(Forms forms) {
        try {
            return formsRepository.save(forms);
        } catch (Exception e) {
            throw new ErrorWhileSavingRecordException("Error while creating form, Please check the request!");
        }
    }

    public Forms getFormById(Long id) {
        return formsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Form Not Found with id: " + id)
        );
    }
}
