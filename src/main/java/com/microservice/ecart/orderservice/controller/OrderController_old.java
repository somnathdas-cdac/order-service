package com.microservice.ecart.orderservice.controller;

/*
import com.microservice.ecart.orderservice.client.ProductClient;
import com.microservice.ecart.orderservice.dto.ProductDTO;
import com.microservice.ecart.orderservice.model.Order;
import com.microservice.ecart.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*") // Allows your UI to connect directly without CORS blocks
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;
    //  @RequestHeader("X-User-Name") String username)
    // 1. PLACE ORDER
    @PostMapping
    public ResponseEntity<?> placeOrder(
            @RequestParam Long productId, 
            @RequestParam Integer quantity,
            @RequestHeader(value = "X-User-Email", required = false, defaultValue = "guest@example.com") String email) {
        
        try {
            // Service Discovery Action: Uses Feign to fetch product price from Product Service
            ProductDTO product = productClient.getProductById(productId);
            if (product == null) {
                return ResponseEntity.badRequest().body("Product not found");
            }

            Double totalPrice = product.getPrice() * quantity;
            Order newOrder = new Order(productId, quantity, totalPrice, "PLACED", email);
            Order savedOrder = orderRepository.save(newOrder);
            
            return ResponseEntity.ok(savedOrder);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error reaching Product Service via Eureka: " + e.getMessage());
        }
    }

    // 2. VIEW ORDER
    @GetMapping("/{id}")
    public ResponseEntity<?> viewOrder(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        }
        return ResponseEntity.notFound().build();
    }

    // 3. CANCEL ORDER
    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setOrderStatus("CANCELLED");
            orderRepository.save(order);
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }
}
*/