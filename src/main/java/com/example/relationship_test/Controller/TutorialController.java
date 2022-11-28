package com.example.relationship_test.Controller;

import com.example.relationship_test.Entity.Tutorial;
import com.example.relationship_test.Repository.TutorialDetailsRepository;
import com.example.relationship_test.Repository.TutorialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.relationship_test.exception.ResourceNotFoundException;


import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class TutorialController {
    private final TutorialRepository tutorialRepository;
    // constructor
    public TutorialController(TutorialRepository tutorialRepository){
        this.tutorialRepository = tutorialRepository;
    }

    //////////////////// read operation ////////////////////////////
    @GetMapping("/all_published_tutorials") // not all tutorials
    public ResponseEntity<List<Tutorial>> getAllPublishedTutorials(){
        return new ResponseEntity<List<Tutorial>>(tutorialRepository.findByPublished(true), HttpStatus.OK);
    }

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required= false)String title){
        List<Tutorial> tutorials = new ArrayList<Tutorial>();
        if (title == null)
            tutorialRepository.findAll().forEach(tutorials::add);
        else
            tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
        if (tutorials.isEmpty()){
            return new ResponseEntity<>(tutorials,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tutorials,HttpStatus.OK);
    }

    ///////////////////////////// insert operation ///////////////////////////////////////////



}
