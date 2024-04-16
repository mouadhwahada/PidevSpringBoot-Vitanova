package tn.spring.pispring.WorkoutController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.spring.pispring.Entities.Exercise;
import tn.spring.pispring.Entities.FollowedProgram;
import tn.spring.pispring.Serviceworkout.ExerciceService;
import tn.spring.pispring.Serviceworkout.FollowedProgramService;

import java.util.List;
@RestController
@AllArgsConstructor
public class FollowedWorkout {
    FollowedProgramService followedProgramService;

    @PostMapping("/creatFollowedProgram")
    public FollowedProgram creatFollowedProgram(@RequestBody FollowedProgram followedProgram) {
        return followedProgramService.creatFollowedProgram(followedProgram);
    }

    @DeleteMapping("/deleteFollowedProgram/{id_FollowedProgram}")
    public void deleteFollowedProgram(@PathVariable("id_FollowedProgram") Integer id_FollowedProgram) {
        followedProgramService.deleteFollowedProgram(id_FollowedProgram);

    }

    @PutMapping("/UpdateFollowedProgram")
    public FollowedProgram UpdateFollowedProgram(@RequestBody FollowedProgram followedProgram) {
        return followedProgramService.UpdateFollowedProgram(followedProgram);
    }

    @GetMapping("/getAllFollowedProgram")
    public List<FollowedProgram> getAllFollowedProgram() {
        return followedProgramService.getAllFollowedProgram();
    }



}
