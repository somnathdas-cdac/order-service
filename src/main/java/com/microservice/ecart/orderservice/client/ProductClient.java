package com.microservice.ecart.orderservice.client;




import com.microservice.ecart.orderservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// PRODUCT-SERVICE is the name registered in your Eureka Server dashboard
@FeignClient(name = "PRODUCT-SERVICE") 
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);
}
