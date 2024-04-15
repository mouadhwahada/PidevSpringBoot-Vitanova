package tn.spring.pispring.Serviceworkout;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.spring.pispring.Entities.FollowedProgram;
import tn.spring.pispring.Entities.Workoutprogram;
import tn.spring.pispring.Interfaceworkout.FollowedprogrammeInterface;
import tn.spring.pispring.repositoriesworkout.FollowedprogrammeRepository;
import tn.spring.pispring.repositoriesworkout.Workoutrepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FollowedProgramService implements FollowedprogrammeInterface {
    FollowedprogrammeRepository followedprogrammeRepository;
    Workoutrepository workoutrepository;
    @Override
    public FollowedProgram creatFollowedProgram(FollowedProgram followedProgram) {
        return followedprogrammeRepository.save(followedProgram);

    }

    @Override
    public FollowedProgram UpdateFollowedProgram(FollowedProgram followedProgram) {
        return  followedprogrammeRepository.save(followedProgram);
    }

    @Override
    public void deleteFollowedProgram(Integer id_FollowedProgram) {
        followedprogrammeRepository.deleteById(id_FollowedProgram);
    }


    @Override
    public List<FollowedProgram> getAllFollowedProgram() {
    return followedprogrammeRepository.findAll();
    }


}
