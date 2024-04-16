package tn.spring.pispring.Controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Services.StripeService;
import tn.spring.pispring.dtos.PaymentDto;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")

public class StripeControler {

@Autowired
StripeService stripeService;

    @Value("${stripe.key.secret}")
    private String stripeSecretKey;

    @GetMapping("/stripe-secret-key")
    public String getStripeSecretKey() {
        return stripeSecretKey;
    }


}
