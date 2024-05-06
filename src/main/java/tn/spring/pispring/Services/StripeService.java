package tn.spring.pispring.Services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.Address;
import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Repositories.AddressRepo;
import tn.spring.pispring.Repositories.PaymentRepository;
import tn.spring.pispring.Repositories.RoleRepo;
import tn.spring.pispring.Repositories.UserRepo;
import tn.spring.pispring.dto.RoleName;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StripeService {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    AddressRepo addressRepo;

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
    public List<User> getUsersByRole() {
        return userRepo.findByRoleName(RoleName.Delivery_man);
    }

    public List<Address> getAddressesOfDeliveryMen() {
        Role deliveryManRole = roleRepo.findByName(RoleName.valueOf("Delivery_man"));
        if (deliveryManRole != null) {
            return addressRepo.findByUserRole(RoleName.Delivery_man);
        }
        return null;
    }

}
