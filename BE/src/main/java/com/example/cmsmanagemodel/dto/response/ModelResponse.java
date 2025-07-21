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
    String original_name;
    String display_name;
    String api_key;
    Boolean is_enabled;
    LocalDateTime created_at;
}
