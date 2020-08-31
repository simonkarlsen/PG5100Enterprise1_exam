// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/test/java/org/tsdes/intro/exercises/quizgame/backend/service/CategoryServiceTest.java

package com.example.exam.backend.service;


import com.example.exam.backend.TestApplication;
import com.example.exam.backend.entity.Movie;
import com.example.exam.backend.entity.Review;
import com.example.exam.backend.entity.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ReviewServiceTest extends ServiceTestBase{

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Test
    public void testCreateReview(){
        userService.createUser("user1", "user1", "Userson","user1@user1.com", "1234", "user");
        Long movieId = movieService.createMovie("movie1", "Test of movie1", "test", 2020L);

        String review1 = "Very good";
        int rating1 = 5;

        Long reviewId = reviewService.newReview(movieId, "user1", review1, rating1);
        assertNotNull(reviewId);
    }

    @Test
    public void testUserMovieReviewAlreadyExists(){
        userService.createUser("user1", "user1", "Userson","user1@user1.com", "1234", "user");
        Long movieId = movieService.createMovie("movie1", "Test of movie1", "test", 2020L);

        String review1 = "Very good";
        int rating1 = 5;

        Long reviewId = reviewService.newReview(movieId, "user1", review1, rating1);
        assertNotNull(reviewId);
        Long reviewId2 = reviewService.newReview(movieId, "user1", review1, rating1);
        assertNull(reviewId2);
    }


    @Test
    public void testFilterReviewByUserId(){
        userService.createUser("user1", "user1", "Userson","user1@user1.com", "1234", "user");
        Long firstMovie = movieService.createMovie("movie1", "Test of movie1", "test", 2020L);
        Long secondMovie = movieService.createMovie("movie2", "Test of movie2", "test", 2020L);

        String review1 = "Very good";
        int rating1 = 5;

        Long firstReview = reviewService.newReview(firstMovie, "user1", review1, rating1);
        Long secondReview = reviewService.newReview(secondMovie, "user1", review1, rating1);
        Users user = userService.findUserByUserName("user1");
        assertNotNull(firstReview);
        assertNotNull(secondReview);

        List<Review> userReview = reviewService.filterReviewByUser(user.getUserID());
        assertEquals(2, userReview.size());

    }

    @Test
    public void testAverageStars() {
        userService.createUser("user1", "user1", "Userson","user1@user1.com", "1234", "user");
        userService.createUser("user2", "user2", "Userson","user2@user2.com", "1234", "user");

        Long movieId = movieService.createMovie("movie1", "Test of movie1", "test", 2020L);

        String review1 = "Very good";
        int rating1 = 5;
        String review2 = "Good";
        int rating2 = 4;

        Long reviewId1 = reviewService.newReview(movieId, "user1", review1, rating1);
        Long reviewId2 = reviewService.newReview(movieId, "user2", review2, rating2);
        assertNotNull(reviewId1);
        assertNotNull(reviewId2);

        Movie movie = movieService.getMovie(movieId);
        double stars = movie.getAverageStars();

        assertEquals(4.5, stars);
    }


}
