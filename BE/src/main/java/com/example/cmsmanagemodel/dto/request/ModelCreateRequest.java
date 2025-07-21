package com.example.cmsmanagemodel.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModelCreateRequest {
    @NotBlank
    String original_name;

    @NotBlank
    String display_name;

    @NotBlank
    String api_key;

    @NotBlank
    String provider_id;
}
