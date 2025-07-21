package com.example.cmsmanagemodel.mapper;

import com.example.cmsmanagemodel.dto.response.ProviderResponse;
import com.example.cmsmanagemodel.entity.Provider;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ProviderMapper {
    ProviderResponse toProviderResponse(Provider provider);
}
