package com.example.relationship_test.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tutorial_details")
public class TutorialDetails {

    @Id
    private Long id;
    @Column
    private Date createdOn;
    @Column
    private String createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId //field serve as both Primary Key and Foreign Key (shared primary key).
    @JoinColumn(name = "tutorial_id") // We set the shared primary key column
    private Tutorial tutorial;

    public TutorialDetails(){}
    public TutorialDetails(String createdBy){
        this.createdBy = createdBy;
        this.createdOn = new Date();
    }

}
