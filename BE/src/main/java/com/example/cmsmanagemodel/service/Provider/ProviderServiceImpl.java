package com.example.cmsmanagemodel.service.Provider;

import com.example.cmsmanagemodel.dto.request.ProviderCreateRequest;
import com.example.cmsmanagemodel.dto.response.ProviderResponse;
import com.example.cmsmanagemodel.entity.Provider;
import com.example.cmsmanagemodel.mapper.ProviderMapper;
import com.example.cmsmanagemodel.repository.ProviderRepository;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderMapper providerMapper;
    private final ProviderRepository providerRepository;

    public ProviderServiceImpl(ProviderMapper providerMapper, ProviderRepository providerRepository) {
        this.providerMapper = providerMapper;
        this.providerRepository = providerRepository;
    }

    @Override
    public ProviderResponse createProvider(ProviderCreateRequest request) {
        Provider provider = Provider.builder()
                .name(request.getName())
                .base_url(request.getBase_url())
                .build();
        return providerMapper.toProviderResponse(providerRepository.save(provider));
    }
}
