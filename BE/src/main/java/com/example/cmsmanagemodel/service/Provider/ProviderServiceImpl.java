package com.example.cmsmanagemodel.service.Provider;

import com.example.cmsmanagemodel.dto.request.ProviderCreateRequest;
import com.example.cmsmanagemodel.dto.request.ProviderUpdateRequest;
import com.example.cmsmanagemodel.dto.response.PageResponse;
import com.example.cmsmanagemodel.dto.response.ProviderResponse;
import com.example.cmsmanagemodel.entity.Provider;
import com.example.cmsmanagemodel.mapper.ProviderMapper;
import com.example.cmsmanagemodel.repository.ProviderRepository;
import com.example.cmsmanagemodel.repository.Specification.ProviderSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;

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

    @Override
    public ProviderResponse updateProvider(String id, ProviderUpdateRequest request) {
        var provider = providerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found"));
        if (request.getName() != null && !request.getName().isEmpty()) {
            provider.setName(request.getName());
        }
        if (request.getBase_url() != null && !request.getBase_url().isEmpty()) {
            provider.setBase_url(request.getBase_url());
        }
        return providerMapper.toProviderResponse(providerRepository.save(provider));
    }

    @Override
    public PageResponse<ProviderResponse> getProviders(String keyword, int page, int pageSize) {
        Sort sort = Sort.unsorted();
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Specification<Provider> spec = ProviderSpecification.build(keyword);
        Page<Provider> pageResult = providerRepository.findAll(spec, pageable);
        List<ProviderResponse> content = providerMapper.toProviderResponseList(pageResult.getContent());
        return PageResponse.<ProviderResponse>builder()
                .content(content)
                .page(page)
                .size(pageSize)
                .totalElements(pageResult.getTotalElements())
                .totalPages(pageResult.getTotalPages())
                .first(pageResult.isFirst())
                .last(pageResult.isLast())
                .hasNext(pageResult.hasNext())
                .hasPrevious(pageResult.hasPrevious())
                .build();
    }
}
