package com.example.cmsmanagemodel.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModelDetailResponse {
    String id;
    String modelCode;
    String displayName;
    Boolean enabled;
    ProviderResponse provider;
}
