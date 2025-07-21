package com.example.cmsmanagemodel.controller;

import com.example.cmsmanagemodel.dto.request.ModelCreateRequest;
import com.example.cmsmanagemodel.dto.response.ApiResponse;
import com.example.cmsmanagemodel.dto.response.ModelResponse;
import com.example.cmsmanagemodel.service.Model.ModelService;
import com.example.cmsmanagemodel.util.ResponseHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/model")
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ModelResponse>> createModel(@RequestBody ModelCreateRequest request){
        return ResponseHelper.created(modelService.createModel(request), "Tao thanh cong model");
    }
}
