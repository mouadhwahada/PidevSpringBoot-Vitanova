package tn.spring.pispring.Interfaceworkout;

import tn.spring.pispring.Entities.Exercise;
import tn.spring.pispring.Entities.FollowedProgram;
import tn.spring.pispring.Entities.Workoutprogram;

import java.util.List;

public interface FollowedprogrammeInterface {
    public FollowedProgram creatFollowedProgram(FollowedProgram followedProgram);
    public  FollowedProgram UpdateFollowedProgram(FollowedProgram followedProgram);
    public void   deleteFollowedProgram(Integer id_FollowedProgram);
    public List<FollowedProgram> getAllFollowedProgram();
}
