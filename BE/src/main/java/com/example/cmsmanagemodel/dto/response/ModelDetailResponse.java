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
    String display_name;
    String original_name;
    String api_key;
    Boolean is_enabled;
    ProviderResponse provider;
}
