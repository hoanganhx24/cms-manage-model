package com.example.cmsmanagemodel.mapper;

import com.example.cmsmanagemodel.dto.response.ModelDetailResponse;
import com.example.cmsmanagemodel.dto.response.ModelResponse;
import com.example.cmsmanagemodel.entity.Model;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProviderMapper.class)
@Component
public interface ModelMapper {
    ModelResponse toModelResponse(Model model);
    ModelDetailResponse toModelDetailResponse(Model model);
    List<ModelDetailResponse> toModelDetailResponseList(List<Model> models);
}
