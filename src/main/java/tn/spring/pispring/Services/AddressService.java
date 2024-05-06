package tn.spring.pispring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import tn.spring.pispring.Entities.Address;
import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Repositories.AddressRepo;
import tn.spring.pispring.Repositories.RoleRepo;
import tn.spring.pispring.Repositories.UserRepo;
import tn.spring.pispring.dto.RoleName;
import tn.spring.pispring.util.FileNamingUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepository;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;

    // Enregistrer une adresse et l'associer à un utilisateur
    public Address saveAddresseee(Long userId, Address address) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            address.setUser(user);
            return addressRepository.save(address);
        }
        return null; // Retourner null si l'utilisateur n'est pas trouvé
    }

    // Enregistrer une adresse
    public Address saveAddress(Address address) {
        addressRepository.save(address);
        return address;
    }

    // Supprimer une adresse
    public void deleteAddress(int id) {
        addressRepository.deleteById(id);
    }


    public User createUserWithRole(String userName, String email, String password, String zone, int phoneNumber, RoleName roleName) {
        User user = new User();
        user.setUsername(userName);
        user.setEmail(email);

        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setZone(zone);

        // Rechercher le rôle par son nom
        Role role = roleRepo.findByName(RoleName.Delivery_man);

        if (role == null) {
            throw new RuntimeException("Le rôle '" + roleName + "' n'existe pas.");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return userRepo.save(user);
    }


    public AddressService(FileNamingUtil fileNamingUtil, @Value("${uploadProductImages}") String uploadProductImages) {
        this.fileNamingUtil = fileNamingUtil;
        this.uploadProductImages = uploadProductImages;
    }

    public final FileNamingUtil fileNamingUtil;
    public final String uploadProductImages;

    public User createUserWithRoleee(String userName, String email, String password, String zone, int phoneNumber, RoleName roleName, MultipartFile imageFile) {
        try {
            // Copier l'image vers le répertoire de destination
            String fileName = fileNamingUtil.nameFile(imageFile);
            Path destinationPath = Paths.get(uploadProductImages, fileName);
            Files.copy(imageFile.getInputStream(), destinationPath);

            User user1 = new User();
            user1.setUsername(userName);
            user1.setEmail(email);
            user1.setPassword(password);
            user1.setPhoneNumber(phoneNumber);
            user1.setZone(zone);
            user1.setImage(fileName);


            // Rechercher le rôle par son nom
            Role role = roleRepo.findByName(RoleName.Delivery_man);

            if (role == null) {
                throw new RuntimeException("Le rôle '" + roleName + "' n'existe pas.");
            }

            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user1.setRoles(roles);
            return userRepo.save(user1);
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors de la sauvegarde de l'image.", e);
        }
    }


    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }


    public Address createAddress(String rue, String ville, String codePostal, String pays) {
        Address address = new Address();
        address.setRue(rue);
        address.setVille(ville);
        address.setCodePostal(codePostal);
        address.setPays(pays);
        return addressRepository.save(address); // Sauvegarder l'adresse dans la base de données
    }

    public Address createAddress(String rue, String ville, String codePostal, String pays, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé avec l'ID : " + userId));

        Address address = new Address();
        address.setRue(rue);
        address.setVille(ville);
        address.setCodePostal(codePostal);
        address.setPays(pays);
        address.setUser(user);

        return addressRepository.save(address);
    }


    public Address updateAddress(String rue, String ville, String codePostal, String pays, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé avec l'ID : " + userId));

        Address address = addressRepository.findByUserId(userId);

        // Si l'adresse n'existe pas, créez une nouvelle adresse
        if (address == null) {
            address = new Address();
            address.setUser(user);
        }

        address.setRue(rue);
        address.setVille(ville);
        address.setCodePostal(codePostal);
        address.setPays(pays);

        return addressRepository.save(address);
    }
}
