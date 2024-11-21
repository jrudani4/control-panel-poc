package com.cp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<FormFields> formFields;

    @PrePersist
    @PreUpdate
    private void convertToUpperCase() {
        if (name != null) {
            name = name.toUpperCase();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
