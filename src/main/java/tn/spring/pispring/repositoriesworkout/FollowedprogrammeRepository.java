package tn.spring.pispring.repositoriesworkout;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.pispring.Entities.FollowedProgram;

@Repository
public interface FollowedprogrammeRepository extends JpaRepository<FollowedProgram,Integer> {
}
