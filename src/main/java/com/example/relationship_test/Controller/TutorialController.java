package com.example.relationship_test.Controller;

import com.example.relationship_test.Entity.Tutorial;
import com.example.relationship_test.Repository.TutorialDetailsRepository;
import com.example.relationship_test.Repository.TutorialRepository;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class TutorialController {
    private final TutorialRepository tutorialRepository;
    private final TutorialDetailsRepository detailsRepository;
    // constructor
    public TutorialController(TutorialRepository tutorialRepository, TutorialDetailsRepository detailsRepository){
        this.tutorialRepository = tutorialRepository;
        this.detailsRepository = detailsRepository;
    }

    //////////////////// read operation ////////////////////////////
    @GetMapping("/all_published_tutorials")
    public ResponseEntity<List<Tutorial>> getAllPublishedTutorials(){
        return new ResponseEntity<List<Tutorial>>(tutorialRepository.findByPublished(true), HttpStatus.OK);
    }

    @GetMapping("tutorials/{id}")
    public ResponseEntity<List<Tutorial>> getTutorialById(@PathVariable("id")long id){
        Tutorial tutorial = tutorialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found with Tutorial with id= " + id));
    }

}
