package com.example.cmsmanagemodel.service.Provider;

import com.example.cmsmanagemodel.dto.request.ProviderCreateRequest;
import com.example.cmsmanagemodel.dto.request.ProviderUpdateRequest;
import com.example.cmsmanagemodel.dto.response.PageResponse;
import com.example.cmsmanagemodel.dto.response.ProviderResponse;
import org.springframework.data.domain.PageRequest;

public interface ProviderService {
    ProviderResponse createProvider(ProviderCreateRequest request);
    ProviderResponse updateProvider(String id, ProviderUpdateRequest request);
    PageResponse<ProviderResponse> getProviders(String keyword, int page, int pageSize);
}
