package com.example.ams.controller;

import com.example.ams.dto.ProductInfoDto;
import com.example.ams.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @GetMapping("/{productId}/similar")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductInfoDto> getSimilarProductInfo(@PathVariable Long productId){
        return productService.getSimilarProductsInfo(productId);
    }
}
