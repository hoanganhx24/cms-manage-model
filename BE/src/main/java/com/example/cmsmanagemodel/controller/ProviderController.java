package com.example.cmsmanagemodel.controller;

import com.example.cmsmanagemodel.dto.request.ProviderCreateRequest;
import com.example.cmsmanagemodel.dto.response.ApiResponse;
import com.example.cmsmanagemodel.dto.response.ProviderResponse;
import com.example.cmsmanagemodel.service.Provider.ProviderService;
import com.example.cmsmanagemodel.util.ResponseHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/provider")
public class ProviderController {
    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProviderResponse>> createProvider(@RequestBody ProviderCreateRequest request) {
        return ResponseHelper.created(providerService.createProvider(request), "Tao thanh cong provider");
    }

}
