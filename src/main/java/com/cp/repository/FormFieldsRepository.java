package com.cp.repository;

import com.cp.entity.FormFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormFieldsRepository extends JpaRepository<FormFields, Long> {
    @Query(value = "SELECT DISTINCT ff.* FROM field_roles fr " +
            "JOIN fields ff ON fr.field_id = ff.id " +
            "JOIN users_roles ur ON fr.role_id = ur.role_id " +
            "WHERE ur.user_id = :userId " +
            "ORDER BY ff.id",
            nativeQuery = true)
    List<FormFields> findFormFieldsByUserId(@Param("userId") Long userId);
}
