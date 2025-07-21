package com.example.cmsmanagemodel.service.Model;

import com.example.cmsmanagemodel.dto.request.ModelCreateRequest;
import com.example.cmsmanagemodel.dto.request.ModelUpdateInfoRequest;
import com.example.cmsmanagemodel.dto.response.ModelDetailResponse;
import com.example.cmsmanagemodel.dto.response.ModelResponse;
import com.example.cmsmanagemodel.dto.response.PageResponse;

public interface ModelService {
    ModelResponse createModel(ModelCreateRequest request);
    PageResponse<ModelDetailResponse> getModels(String display_name, String provider_id, int page, int pageSize);
    ModelResponse enableModel(String model_id);
    ModelResponse disableModel(String model_id);
    ModelResponse updateInfoModel(String model_id, ModelUpdateInfoRequest request);
}
