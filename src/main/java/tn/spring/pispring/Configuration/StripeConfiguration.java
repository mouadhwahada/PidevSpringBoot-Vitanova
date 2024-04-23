package tn.spring.pispring.Configuration;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfiguration {

    @Autowired
    public StripeConfiguration(@Value("${stripe.key.secret}") String stripeSecretKey) {
        Stripe.apiKey = stripeSecretKey;
    }
}