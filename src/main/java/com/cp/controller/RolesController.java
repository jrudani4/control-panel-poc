package com.cp.controller;

import com.cp.entity.FormFields;
import com.cp.entity.Roles;
import com.cp.entity.User;
import com.cp.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RolesController {

    private final RolesService rolesService;

    @GetMapping
    public ResponseEntity<List<Roles>> getAllRoles() {
        return ResponseEntity.ok(rolesService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Roles> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(rolesService.getRoleById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Roles> createRole(@RequestBody Roles role) {
        Roles createdRole = rolesService.createRole(role);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PutMapping("/{roleId}/assign-to-user/{userId}")
    public ResponseEntity<User> assignRoleToUser(@PathVariable Long roleId, @PathVariable Long userId) {
        return ResponseEntity.ok(rolesService.assignRolesToUser(roleId, userId));
    }

    @PutMapping("/{roleId}/assign-to-field/{fieldId}")
    public ResponseEntity<FormFields> assignRoleToFormField(@PathVariable Long roleId, @PathVariable Long fieldId) {
        return ResponseEntity.ok(rolesService.assignRolesToFormField(fieldId, roleId));
    }
}