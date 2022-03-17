package com.example.ams.service;

import com.example.ams.dto.ProductInfoDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IProductService {

    List<ProductInfoDto> getSimilarProductsInfo(Long id);
}
