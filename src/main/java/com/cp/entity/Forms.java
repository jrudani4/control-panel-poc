package com.cp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "forms")
public class Forms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Long noOfFields;

    @CreationTimestamp
    private Date createAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "form_fields",
            joinColumns = @JoinColumn(name = "form_id"),
            inverseJoinColumns = @JoinColumn(name = "field_id")
    )
    private List<FormFields> formFields;

    @Override
    public String toString() {
        return "Forms{" +
                "createAt=" + createAt +
                ", noOfFields=" + noOfFields +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
