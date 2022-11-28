package com.example.relationship_test.Repository;

import com.example.relationship_test.Entity.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
//    @Query("select t from Tutorial t where t.published=?1")
    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);
}
