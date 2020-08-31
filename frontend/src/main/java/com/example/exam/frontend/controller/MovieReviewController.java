// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/main/java/org/tsdes/intro/exercises/quizgame/frontend/controller/MatchController.java
// https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/spring/jsf/src/main/java/org/tsdes/intro/spring/jsf/ex03/CommentController.java
package com.example.exam.frontend.controller;

import com.example.exam.backend.entity.Movie;
import com.example.exam.backend.entity.Review;
import com.example.exam.backend.service.MovieService;
import com.example.exam.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class MovieReviewController implements Serializable {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;


    private String formText;

    private int stars = 5;


    public Movie getMovie(Long id) {
        return movieService.getMovie(id);
    }

    public List<Movie> getMovies() {
        return new ArrayList<>(movieService.getAllMovies());
    }

    public List<Review> getReviews() {
        return new ArrayList<>(reviewService.getAllReviews());
    }

    public String getFormText() {
        return formText;
    }

    public void setFormText(String formText) {
        this.formText = formText;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }


    public String newReview(Long movieId, String userID) {
        reviewService.newReview(movieId, userID, formText, stars);
        formText = "";
        setStars(5);
        return "/moviedetails.xhtml?faces-redirect=true&successFull=true";
    }


//    public String deleteMovie(Long movieId) {
//        movieService.removeMovie(movieId);
//        return "/index.xhtml?faces-redirect=true&successFull=true";
//    }
//

    public List<Review> filterReviews(String searchOption) {
        switch (searchOption) {
            case "byStars":
                return reviewService.filterByStars();
            case "byTime":
                return reviewService.filterByTime();
            default:
                return null;
        }
    }


}
