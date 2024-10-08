package com.cp.service;

import com.cp.entity.FormFields;
import com.cp.entity.Roles;
import com.cp.entity.User;
import com.cp.exception.exceptions.ErrorWhileSavingRecordException;
import com.cp.exception.exceptions.NoRecordsFoundException;
import com.cp.exception.exceptions.ResourceNotFoundException;
import com.cp.repository.RolesRepository;
import com.cp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolesService {

    private final RolesRepository rolesRepository;
    private final FormFieldsService formFieldsService;
    private final UserRepository userRepository;

    public Roles createRole(Roles role) {
        try {
            return rolesRepository.save(role);
        } catch (Exception e) {
            throw new ErrorWhileSavingRecordException("Error while creating role, Please check the request!");
        }
    }

    public Roles getRoleById(Long id) {
        return rolesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role Not Found with id: " + id));
    }

    public List<Roles> getAllRoles() {
        List<Roles> roles = rolesRepository.findAll();
        if (roles.isEmpty()) {
            throw new NoRecordsFoundException("No Records Found!");
        }
        return roles;
    }

    public User assignRolesToUser(Long roleId, Long userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found with id: " + userId));
            Roles roles = getRoleById(roleId);
            List<Roles> rolesSet = user.getRoles();
            rolesSet.add(roles);
            user.setRoles(rolesSet);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ErrorWhileSavingRecordException("Error while assigning role to user, Please check the request!");
        }
    }

    public FormFields assignRolesToFormField(Long formFieldId, Long roleId) {
        try {
            FormFields formFields = formFieldsService.getFormFieldsById(formFieldId);
            Roles roles = getRoleById(roleId);
            List<Roles> rolesSet = formFields.getRoles();
            rolesSet.add(roles);
            formFields.setRoles(rolesSet);
            return formFieldsService.createFormField(formFields);
        } catch (Exception e) {
            throw new ErrorWhileSavingRecordException("Error while assigning role to formField, Please check the request!");
        }
    }
}