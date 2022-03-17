package com.example.ams.service;

import com.example.ams.dto.ProductInfoDto;
import com.example.ams.webClients.ExternalProductApiWebClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductService implements IProductService{

    /**
     * Find ids from similar product given a product id
     * Get product info from the similar taken ids
     * @param id
     * @return product info from similar products
     */
    @Override
    public List<ProductInfoDto> getSimilarProductsInfo(Long id) {
        ExternalProductApiWebClient externalProductApiWebClient = ExternalProductApiWebClient.getInstance();

        List<Long> similarProductsIdList= externalProductApiWebClient.requestSimilarProductsIds(id);
        List<ProductInfoDto> productInfoDtos = new ArrayList<>(6);
        for(Long productId : similarProductsIdList){
            productInfoDtos.add(externalProductApiWebClient.requestProductInfo(productId));
        }
        return productInfoDtos;
    }
}
