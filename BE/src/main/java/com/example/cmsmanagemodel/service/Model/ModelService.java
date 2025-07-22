package com.example.cmsmanagemodel.service.Model;

import com.example.cmsmanagemodel.dto.request.ModelCreateRequest;
import com.example.cmsmanagemodel.dto.request.ModelUpdateInfoRequest;
import com.example.cmsmanagemodel.dto.response.ModelDetailResponse;
import com.example.cmsmanagemodel.dto.response.ModelResponse;
import com.example.cmsmanagemodel.dto.response.PageResponse;

public interface ModelService {
    ModelResponse createModel(ModelCreateRequest request);
    PageResponse<ModelResponse> getModels(String keyword, String providerId, int page, int pageSize);
    ModelResponse enableModel(String modelId);
    ModelResponse disableModel(String modelId);
    ModelResponse updateInfoModel(String modelId, ModelUpdateInfoRequest request);
}
