package com.cp.entity;

import com.cp.dtos.FormFieldTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "fields")
public class FormFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fieldName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FormFieldTypes fieldType;

    @ManyToMany
    @JoinTable(name = "field_roles",
            joinColumns = @JoinColumn(name = "field_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Roles> roles;

    @JsonIgnore
    @ManyToMany(mappedBy = "formFields")
    private List<Forms> forms;
}
