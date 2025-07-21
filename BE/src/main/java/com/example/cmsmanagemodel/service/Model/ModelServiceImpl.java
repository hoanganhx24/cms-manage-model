package com.example.cmsmanagemodel.service.Model;

import com.example.cmsmanagemodel.dto.request.ModelCreateRequest;
import com.example.cmsmanagemodel.dto.request.ModelUpdateInfoRequest;
import com.example.cmsmanagemodel.dto.response.ModelDetailResponse;
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
                .original_name(request.getOriginal_name())
                .display_name(request.getDisplay_name())
                .api_key(request.getApi_key())
                .is_enabled(true)
                .provider(provider)
                .build();
        return modelMapper.toModelResponse(modelRepository.save(model));
    }

    @Override
    public PageResponse<ModelDetailResponse> getModels(String display_name, String provider_id, int page, int pageSize) {
        Sort sort = Sort.unsorted();
        Pageable  pageable = PageRequest.of(page, pageSize, sort);
        Specification<Model> spec = ModelSpecification.build(display_name, provider_id);
        Page<Model> pageResult = modelRepository.findAll(spec, pageable);
        List<ModelDetailResponse> content = modelMapper.toModelDetailResponseList(pageResult.getContent());
        return PageResponse.<ModelDetailResponse>builder()
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
    public ModelResponse enableModel(String model_id) {
        var model = modelRepository.findById(model_id)
                .orElseThrow(() -> new EntityNotFoundException("Model not found"));
        model.setIs_enabled(true);
        return modelMapper.toModelResponse(modelRepository.save(model));
    }

    @Override
    public ModelResponse disableModel(String model_id) {
        var model = modelRepository.findById(model_id)
                .orElseThrow(() -> new EntityNotFoundException("Model not found"));
        model.setIs_enabled(false);
        return modelMapper.toModelResponse(modelRepository.save(model));
    }

    @Override
    public ModelResponse updateInfoModel(String model_id, ModelUpdateInfoRequest request) {
        var model = modelRepository.findById(model_id)
                .orElseThrow(() -> new EntityNotFoundException("Model not found"));
        if (request.getDisplay_name() != null && !request.getDisplay_name().isEmpty()) {
            model.setDisplay_name(request.getDisplay_name());
        }
        if (request.getOriginal_name() != null && !request.getOriginal_name().isEmpty()) {
            model.setOriginal_name(request.getOriginal_name());
        }
        if (request.getApi_key() != null && !request.getApi_key().isEmpty()) {
            model.setApi_key(request.getApi_key());
        }
        return modelMapper.toModelResponse(modelRepository.save(model));
    }
}
