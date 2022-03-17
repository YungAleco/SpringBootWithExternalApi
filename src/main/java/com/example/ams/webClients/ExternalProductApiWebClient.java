package com.example.ams.webClients;

import com.example.ams.dto.ProductInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class ExternalProductApiWebClient {

    Logger logger = LoggerFactory.getLogger(ExternalProductApiWebClient.class);

    private static ExternalProductApiWebClient externalProductApiWebClient;
    private static WebClient webClient;

    private ExternalProductApiWebClient(String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public static ExternalProductApiWebClient getInstance() {
        return new ExternalProductApiWebClient(ExternalProductApiConst.PRODUCT_EXTERNAL_API_BASE_URL);
    }

    public List<Long> requestSimilarProductsIds(Long productId){
        logger.debug("Retrieving similar product ids from external api");

        WebClient.RequestBodySpec requestBodySpec = webClient.method(HttpMethod.GET).uri(ExternalProductApiConst.SIMILAR_PRODUCT_URI,
                productId);
        Flux<Long> response = requestBodySpec
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    throw new ResponseStatusException(clientResponse.statusCode());
                }).bodyToFlux(Long.class).timeout(Duration.ofSeconds(3));

        return response.collectList().share().block();
    }


    public ProductInfoDto requestProductInfo(Long productId) {
        logger.debug("Retrieving product info from external api");
        Mono<ProductInfoDto> response = webClient.get().uri(ExternalProductApiConst.PRODUCT_INFO_URI,
                        productId).retrieve()
                .bodyToMono(ProductInfoDto.class);
        return response.share().block();
    }
}
