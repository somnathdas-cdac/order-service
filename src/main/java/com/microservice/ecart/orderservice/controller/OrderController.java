package com.microservice.ecart.orderservice.controller;

import com.microservice.ecart.orderservice.client.ProductClient;
import com.microservice.ecart.orderservice.dto.OrderRequest;
import com.microservice.ecart.orderservice.dto.ProductDTO;
import com.microservice.ecart.orderservice.model.Order;
import com.microservice.ecart.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

//--------------------------------------------

import com.microservice.ecart.orderservice.util.OrderJwtUtil;

@RestController
@RequestMapping("/orders") // Add the prefix here
@CrossOrigin(origins = "*")



public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductClient productClient;
    
    private final OrderJwtUtil orderJwtUtil;
    

    /**
     * Creates an order securely.
     * REMOVED: @RequestParam String productName to prevent MissingServletRequestParameterException.
     */
   
    public OrderController(OrderJwtUtil orderJwtUtil)
    {
    	this.orderJwtUtil=orderJwtUtil;
    }
    
    
    
    
    
    // View implementation handling targeted object checks
    @GetMapping("/{id}") 
    public ResponseEntity<?> viewOrder(@PathVariable Long id) { 
        Optional<Order> order = orderRepository.findById(id); 
        if (order.isPresent()) { 
            return ResponseEntity.ok(order.get()); 
        } 
        return ResponseEntity.notFound().build(); 
    } 
    
    
    @PostMapping
    /*
    public ResponseEntity<?> placeOrder(
            @RequestBody OrderRequest orderRequest, 
            @RequestHeader(value = "X-User-Name", required = false, defaultValue = "user@example.com") String email) { 
            
            */
    public ResponseEntity<?> placeOrder(
            @RequestBody OrderRequest orderRequest, 
            @RequestHeader("Authorization") String authHeader) { 
    
        try { 
        	
        	// 1. Verify header existence and format
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or malformed token");
            }
            String token = authHeader.substring(7); // Remove "Bearer " prefix
            
            // 2. Perform cryptographic validation and get user identity
            String validatedUserEmail = orderJwtUtil.validateAndExtractEmail(token);
        	
        	
            // 1. Read values straight from your updated frontend DTO payload structure
            Long productId = orderRequest.getProductId();
            String frontEndName = orderRequest.getProductName();
            Double frontEndPrice = orderRequest.getProductAmount();
            
            // Defaulting quantity to 1 since it's a direct item checkout click
            Integer quantity = 1; 

            // 2. Fetch live product data from the Product Microservice using OpenFeign
            ProductDTO product = productClient.getProductById(productId); 
            if (product == null) { 
                return ResponseEntity.badRequest().body("Product target identity context not found."); 
            } 
            
            // 3. Calculate final billing records (Using your verified database information)
            Double totalPrice = product.getPrice() * quantity; 
            String liveProductName = product.getName();
            
            // 4. Save order records directly to MySQL
           // Order newOrder = new Order(productId, liveProductName, quantity, totalPrice, "PLACED", email); 
            Order newOrder = new Order(productId, liveProductName, quantity, totalPrice, "PLACED", validatedUserEmail); 
            Order savedOrder = orderRepository.save(newOrder); 
            
            
            return ResponseEntity.ok(savedOrder); 
        } catch (Exception e) { 
            return ResponseEntity.internalServerError().body("Error reaching Product Service: " + e.getMessage()); 
        } 
    }


    
    
    
}
