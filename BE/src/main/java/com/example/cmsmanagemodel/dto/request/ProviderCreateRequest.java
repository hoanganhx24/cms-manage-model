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
public class ProviderCreateRequest {
    @NotBlank
    String name;

    @NotBlank
    String apiKey;

    @NotBlank
    String baseUrl;
}
