package tn.spring.pispring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.pispring.Entities.Address;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Services.AddressService;
import tn.spring.pispring.Services.StripeService;
import tn.spring.pispring.Services.UserService;
import tn.spring.pispring.dto.RoleName;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")

public class StripeControler {

    @Autowired
    StripeService stripeService;
    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;


    @Value("${stripe.key.secret}")
    private String stripeSecretKey;

    @GetMapping("/stripe-secret-key")
    public String getStripeSecretKey() {
        return stripeSecretKey;
    }


    // Enregistrer une adresse
    @PostMapping
    public Address saveAddress(@RequestBody Address address) {
        return addressService.saveAddress(address);
    }

    @PostMapping("/{userId}")
    public Address saveAddressee(@PathVariable Long userId, @RequestBody Address address) {
        return addressService.saveAddresseee(userId, address);
    }

    // Supprimer une adresse
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable int id) {
        addressService.deleteAddress(id);
    }


    @PostMapping("/user/create")
    public User createUserWithRole(
            @RequestParam String userName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String zone,
            @RequestParam int phoneNumber,
            @RequestParam String roleName

    ) {
        return addressService.createUserWithRole(
                userName,
                email,
                password,
                zone,
                phoneNumber,

                RoleName.valueOf(roleName)
        );
    }


    @GetMapping("/byRole/delivery_man")
    public List<User> getUsersWithDeliveryManRole() {
        return stripeService.getUsersByRole();
    }



    @GetMapping("/delivery-men/addresses")
    public List<Address> getAddressesOfDeliveryMen() {
        return stripeService.getAddressesOfDeliveryMen();
    }


    @PostMapping("/createUserWithRole")
    public ResponseEntity<User> createUserWithRole(@RequestParam("userName") String userName,
                                                   @RequestParam("email") String email,
                                                   @RequestParam("password") String password,
                                                   @RequestParam("zone") String zone,
                                                   @RequestParam("phoneNumber") int phoneNumber,
                                                   @RequestParam("roleName") RoleName roleName,
                                                   @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            User user = addressService.createUserWithRoleee(userName, email, password, zone, phoneNumber, roleName, imageFile);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/createAddress")
    public Address createAddress(String rue, String ville, String codePostal, String pays) {
        return addressService.createAddress(rue, ville, codePostal, pays);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Address> createAddressForUser(@RequestParam String rue, @RequestParam String ville,
                                                        @RequestParam String codePostal, @RequestParam String pays,
                                                        @PathVariable Long userId) {
        Address createdAddress = addressService.createAddress(rue, ville, codePostal, pays, userId);
        return new ResponseEntity<>(createdAddress, HttpStatus.CREATED);
    }

    @PutMapping("/userrr/{userId}")
    public ResponseEntity<Address> updateAddressForUser(@RequestParam String rue, @RequestParam String ville,
                                                        @RequestParam String codePostal, @RequestParam String pays,
                                                        @PathVariable Long userId) {
        Address updatedAddress = addressService.updateAddress(rue, ville, codePostal, pays, userId);
        return ResponseEntity.ok(updatedAddress);
    }
}


