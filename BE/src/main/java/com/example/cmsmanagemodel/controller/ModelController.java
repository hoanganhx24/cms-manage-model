package com.example.cmsmanagemodel.controller;

import com.example.cmsmanagemodel.dto.request.ModelCreateRequest;
import com.example.cmsmanagemodel.dto.request.ModelUpdateInfoRequest;
import com.example.cmsmanagemodel.dto.response.ApiResponse;
import com.example.cmsmanagemodel.dto.response.ModelDetailResponse;
import com.example.cmsmanagemodel.dto.response.ModelResponse;
import com.example.cmsmanagemodel.dto.response.PageResponse;
import com.example.cmsmanagemodel.service.Model.ModelService;
import com.example.cmsmanagemodel.util.ResponseHelper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/model")
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ModelResponse>> createModel(@Valid @RequestBody ModelCreateRequest request){
        return ResponseHelper.created(modelService.createModel(request), "Tao thanh cong model");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ModelDetailResponse>>> getModels(
            @RequestParam(required = false) String display_name,
            @RequestParam(required = false) String provider_id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        return ResponseHelper.success(modelService.getModels(display_name, provider_id, page, pageSize), "Lay thanh cong danh sach Model");
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<ApiResponse<ModelResponse>> enableModel(@PathVariable String id){
        return ResponseHelper.success(modelService.enableModel(id), "Cho phep model duoc tiep tuc su dung");
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<ApiResponse<ModelResponse>> disableModel(@PathVariable String id){
        return ResponseHelper.success(modelService.disableModel(id), "Cho phep model duoc tiep tuc su dung");
    }

    @PatchMapping("/{id}/updateInfo")
    public ResponseEntity<ApiResponse<ModelResponse>> updateInfo(@PathVariable String id, @RequestBody ModelUpdateInfoRequest  request){
        return ResponseHelper.success(modelService.updateInfoModel(id, request), "Cap nhat thong tin model thanh cong");
    }
}
