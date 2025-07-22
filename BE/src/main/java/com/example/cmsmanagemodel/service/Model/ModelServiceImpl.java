package com.example.cmsmanagemodel.service.Model;

import com.example.cmsmanagemodel.dto.request.ModelCreateRequest;
import com.example.cmsmanagemodel.dto.request.ModelUpdateInfoRequest;
import com.example.cmsmanagemodel.dto.response.ModelResponse;
import com.example.cmsmanagemodel.dto.response.PageResponse;
import com.example.cmsmanagemodel.entity.Model;
import com.example.cmsmanagemodel.mapper.ModelMapper;
import com.example.cmsmanagemodel.repository.ModelRepository;
import com.example.cmsmanagemodel.repository.ProviderRepository;
import com.example.cmsmanagemodel.repository.Specification.ModelSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelMapper modelMapper;
    private final ProviderRepository providerRepository;
    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelMapper modelMapper, ProviderRepository providerRepository, ModelRepository modelRepository) {
        this.modelMapper = modelMapper;
        this.providerRepository = providerRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public ModelResponse createModel(ModelCreateRequest request) {
        var provider = providerRepository.findById(request.getProvider_id())
                .orElseThrow(() -> new EntityNotFoundException("Provider not found"));
        Model model = Model.builder()
                .modelCode(request.getModelCode())
                .displayName(request.getDisplayName())
                .enabled(true)
                .provider(provider)
                .build();
        return modelMapper.toModelResponse(modelRepository.save(model));
    }

    @Override
    public PageResponse<ModelResponse> getModels(String keyword, String providerId, int page, int pageSize) {
        Sort sort = Sort.unsorted();
        Pageable  pageable = PageRequest.of(page, pageSize, sort);
        Specification<Model> spec = ModelSpecification.build(keyword, providerId);
        Page<Model> pageResult = modelRepository.findAll(spec, pageable);
        List<ModelResponse> content = modelMapper.toModelResponseList(pageResult.getContent());
        return PageResponse.<ModelResponse>builder()
                .content(content)
                .page(page)
                .size(pageSize)
                .totalPages(pageResult.getTotalPages())
                .totalElements(pageResult.getTotalElements())
                .first(pageResult.isFirst())
                .last(pageResult.isLast())
                .hasNext(pageResult.hasNext())
                .hasPrevious(pageResult.hasPrevious())
                .build();
    }

    @Override
    public ModelResponse enableModel(String modelId) {
        var model = modelRepository.findById(modelId)
                .orElseThrow(() -> new EntityNotFoundException("Model not found"));
        model.setEnabled(true);
        return modelMapper.toModelResponse(modelRepository.save(model));
    }

    @Override
    public ModelResponse disableModel(String modelId) {
        var model = modelRepository.findById(modelId)
                .orElseThrow(() -> new EntityNotFoundException("Model not found"));
        model.setEnabled(false);
        return modelMapper.toModelResponse(modelRepository.save(model));
    }

    @Override
    public ModelResponse updateInfoModel(String model_id, ModelUpdateInfoRequest request) {
        var model = modelRepository.findById(model_id)
                .orElseThrow(() -> new EntityNotFoundException("Model not found"));
        if (request.getDisplayName() != null && !request.getDisplayName().isEmpty()) {
            model.setDisplayName(request.getDisplayName());
        }
        if (request.getModelCode() != null && !request.getModelCode().isEmpty()) {
            model.setModelCode(request.getModelCode());
        }
        return modelMapper.toModelResponse(modelRepository.save(model));
    }
}
