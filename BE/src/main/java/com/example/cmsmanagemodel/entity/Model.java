package com.example.cmsmanagemodel.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String original_name;
    String display_name;
    String api_key;
    Boolean is_enabled;
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

    @ManyToOne
    @JoinColumn(name = "provider_id")
    @JsonBackReference
    Provider provider;

}
