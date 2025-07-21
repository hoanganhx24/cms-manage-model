package com.example.cmsmanagemodel.service.Model;

import com.example.cmsmanagemodel.dto.request.ModelCreateRequest;
import com.example.cmsmanagemodel.dto.response.ModelResponse;

public interface ModelService {
    ModelResponse createModel(ModelCreateRequest request);
}
