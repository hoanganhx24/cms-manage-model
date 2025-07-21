package com.example.cmsmanagemodel.service.Provider;

import com.example.cmsmanagemodel.dto.request.ProviderCreateRequest;
import com.example.cmsmanagemodel.dto.response.ProviderResponse;

public interface ProviderService {
    ProviderResponse createProvider(ProviderCreateRequest request);
}
