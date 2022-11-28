package com.example.relationship_test.Controller;

import com.example.relationship_test.Entity.Tutorial;
import com.example.relationship_test.Entity.TutorialDetails;
import com.example.relationship_test.Repository.TutorialDetailsRepository;
import com.example.relationship_test.Repository.TutorialRepository;
import com.example.relationship_test.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/details")
@RestController
public class TutorialDetailsController {
    private final TutorialDetailsRepository detailsRepository;
    private final TutorialRepository tutorialRepository;
    public TutorialDetailsController(TutorialDetailsRepository detailsRepository, TutorialRepository tutorialRepository){
        this.detailsRepository = detailsRepository;
        this.tutorialRepository = tutorialRepository;
    }

    //////////////////////////// insert operation ///////////////////////////////////////
    @PostMapping("/{id}/add_only_details")
    public ResponseEntity<TutorialDetails> addDetails(@PathVariable("id")Long tutorialId, @RequestBody TutorialDetails detailsRequest){
        Tutorial tutorial =  tutorialRepository.findById(tutorialId)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found with id= " + tutorialId));
        detailsRequest.setCreatedOn(new java.util.Date());
        detailsRequest.setTutorial(tutorial);
        TutorialDetails details = detailsRepository.save(detailsRequest);
        return new ResponseEntity<>(details, HttpStatus.CREATED);
    }

    @PostMapping("/add_both_table")
    public ResponseEntity<TutorialDetails> addBothTable(@RequestBody TutorialDetails detailsRequest){
        TutorialDetails tutorialDetails = new TutorialDetails();
        tutorialDetails.setTutorial(detailsRequest.getTutorial());
        tutorialDetails.setCreatedBy(detailsRequest.getCreatedBy());
        tutorialDetails.setCreatedOn(new java.util.Date());
        TutorialDetails details = detailsRepository.save(tutorialDetails);
        return new ResponseEntity<>(details, HttpStatus.CREATED);
    }


    ////////////////////////////////////// read operation ////////////////////////////////////////////

    @GetMapping("/full_details/{id}") ///// not working
    public ResponseEntity<TutorialDetails> readAll(@PathVariable(value = "id")Long id){
        TutorialDetails tutorialDetails = detailsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found with tutorialId= " + id));
        return new ResponseEntity<>(tutorialDetails, HttpStatus.OK);
    }


    //////////////////////////////////////// update operation //////////////////////////////
    
    /////////////////////////////////////// delete operation /////////////////////////////////
    @DeleteMapping("/{id}/delete_only_details")
    public ResponseEntity<?> deleteOnlyDetails(@PathVariable("id") Long id){
        detailsRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/delete_from_both")
    public ResponseEntity<?> deleteFromBoth(@PathVariable("id") Long id){
        detailsRepository.deleteById(id);
        tutorialRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
