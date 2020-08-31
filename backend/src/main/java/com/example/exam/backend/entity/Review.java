// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/entity/SubCategory.java

package com.example.exam.backend.entity;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Users reviewOwner;

    @ManyToOne
    @NotNull
    private Movie movieInformation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private String reviewText;

    @NotNull
    @Range(min = 1, max = 5)
    private int stars;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getReviewOwner() {
        return reviewOwner;
    }

    public void setReviewOwner(Users reviewOwner) {
        this.reviewOwner = reviewOwner;
    }

    public Movie getMovieInformation() {
        return movieInformation;
    }

    public void setMovieInformation(Movie movieInformation) {
        this.movieInformation = movieInformation;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

}
