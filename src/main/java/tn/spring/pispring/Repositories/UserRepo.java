package tn.spring.pispring.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
