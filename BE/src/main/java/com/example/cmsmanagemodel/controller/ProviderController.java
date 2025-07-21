package com.example.cmsmanagemodel.controller;

import com.example.cmsmanagemodel.dto.request.ProviderCreateRequest;
import com.example.cmsmanagemodel.dto.request.ProviderUpdateRequest;
import com.example.cmsmanagemodel.dto.response.ApiResponse;
import com.example.cmsmanagemodel.dto.response.PageResponse;
import com.example.cmsmanagemodel.dto.response.ProviderResponse;
import com.example.cmsmanagemodel.service.Provider.ProviderService;
import com.example.cmsmanagemodel.util.ResponseHelper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/provider")
public class ProviderController {
    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProviderResponse>> createProvider(@Valid @RequestBody ProviderCreateRequest request) {
        return ResponseHelper.created(providerService.createProvider(request), "Tao thanh cong provider");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ProviderResponse>> updateProvider(@PathVariable String id, @Valid @RequestBody ProviderUpdateRequest request) {
        return ResponseHelper.success(providerService.updateProvider(id, request), "Chinh sua thanh cong");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ProviderResponse>>> getProviders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword
    ) {
        return ResponseHelper.success(providerService.getProviders(keyword, page, pageSize), "Lay thanh cong danh sach Provider");
    }

}
