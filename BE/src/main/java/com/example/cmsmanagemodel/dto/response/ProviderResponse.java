package com.example.cmsmanagemodel.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProviderResponse {
    String id;
    String name;
    String apiKey;
    String baseUrl;
}
