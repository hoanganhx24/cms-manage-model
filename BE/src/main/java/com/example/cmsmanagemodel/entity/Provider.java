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
    String apiKey;
    String baseUrl;

    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Model> models;

}
