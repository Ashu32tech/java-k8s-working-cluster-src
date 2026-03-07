
package com.example.order;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public Order create(@RequestBody Order order) {
        order.setStatus("PENDING");
        String paymentUrl = "http://payment-service:7082/payments";
        String result = restTemplate.postForObject(paymentUrl, order, String.class);

        if ("SUCCESS".equals(result)) {
            order.setStatus("CONFIRMED");
        } else {
            order.setStatus("FAILED");
        }
        return order;
    }
}
