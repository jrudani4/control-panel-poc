package com.cp.repository;

import com.cp.entity.FormFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormFieldsRepository extends JpaRepository<FormFields, Long> {
    @Query(value = "SELECT DISTINCT ff.* FROM form_fields ff " +
            "JOIN field_roles fr ON ff.id = fr.field_id " +
            "JOIN users_roles ur ON fr.role_id = ur.role_id " +
            "WHERE ur.user_id = :userId",
            nativeQuery = true)
    List<FormFields> findFormFieldsByUserId(@Param("userId") Long userId);
}
