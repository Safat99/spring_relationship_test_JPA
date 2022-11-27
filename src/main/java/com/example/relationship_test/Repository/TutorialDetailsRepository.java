package com.example.relationship_test.Repository;

import com.example.relationship_test.Entity.TutorialDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TutorialDetailsRepository extends CrudRepository<TutorialDetails, Long> {
    @Transactional
    void deleteById(long id);
}
