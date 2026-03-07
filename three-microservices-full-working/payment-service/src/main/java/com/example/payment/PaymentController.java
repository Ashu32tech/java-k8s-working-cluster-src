
package com.example.payment;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @PostMapping
    public String process(@RequestBody Object order) {
        return "SUCCESS";
    }
}
