package com.example.cmsmanagemodel.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModelResponse {
    String id;
    String modelCode;
    String displayName;
    Boolean enabled;
    ProviderResponse provider;
}
