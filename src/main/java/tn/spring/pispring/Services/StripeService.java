package tn.spring.pispring.Services;

import com.stripe.Stripe;
import com.stripe.exception.CardException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Repositories.PaymentRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class StripeService {

    @Autowired
    PaymentRepository paymentRepository;

    @Value("${stripe.key.secret}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }
    public PaymentIntent createPaymentIntent(BigDecimal amount) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount.multiply(BigDecimal.valueOf(100)).intValue());
        params.put("currency", "usd");
        params.put("payment_method_types", Collections.singletonList("card"));

        return PaymentIntent.create(params);
    }

}
