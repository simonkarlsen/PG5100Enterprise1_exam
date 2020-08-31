// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/entity/Category.java

package com.example.exam.backend.entity;


import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String title;

    @NotBlank
    private String director;

    @NotBlank
    private String genre;

    @Range(min = 1888, max = 2020)
    private Long releaseYear;

    @OneToMany(mappedBy = "movieInformation")
    private List<Review> reviewInfo;

    private int reviewCount;

    private double averageStars;

    @NotNull
    private double totalStars;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String description) {
        this.director = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String type) {
        this.genre = type;
    }

    public Long getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Long releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Review> getReviewInfo() {
        return reviewInfo;
    }

    public void setReviewInfo(List<Review> reviewInfo) {
        this.reviewInfo = reviewInfo;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public double getAverageStars() {
        return averageStars;
    }

    public void setAverageStars(double averageStars) {
        this.averageStars = averageStars;
    }

    public double getTotalStars() {
        return totalStars;
    }

    public void setTotalStars(double totalStars) {
        this.totalStars = totalStars;
    }
}
