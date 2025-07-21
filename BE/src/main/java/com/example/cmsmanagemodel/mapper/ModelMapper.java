package com.example.cmsmanagemodel.mapper;

import com.example.cmsmanagemodel.dto.response.ModelResponse;
import com.example.cmsmanagemodel.entity.Model;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ModelMapper {
    ModelResponse toModelResponse(Model model);
}
