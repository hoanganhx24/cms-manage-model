package com.example.cmsmanagemodel.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModelUpdateInfoRequest {
    String displayName;
    String modelCode;
}
