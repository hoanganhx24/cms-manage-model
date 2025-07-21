package com.example.cmsmanagemodel.service.Model;

import com.example.cmsmanagemodel.dto.request.ModelCreateRequest;
import com.example.cmsmanagemodel.dto.response.ModelResponse;
import com.example.cmsmanagemodel.entity.Model;
import com.example.cmsmanagemodel.mapper.ModelMapper;
import com.example.cmsmanagemodel.repository.ModelRepository;
import com.example.cmsmanagemodel.repository.ProviderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

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
}
