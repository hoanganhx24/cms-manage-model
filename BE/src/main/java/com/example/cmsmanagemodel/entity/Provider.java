package com.example.cmsmanagemodel.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;
    String base_url;

    LocalDateTime created_at;
    LocalDateTime updated_at;

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now();
        updated_at = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updated_at = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "provider")
    @JsonManagedReference
    List<Model> models;

}
