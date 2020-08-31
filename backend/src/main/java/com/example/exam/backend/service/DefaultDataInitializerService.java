// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/service/DefaultDataInitializerService.java
package com.example.exam.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.function.Supplier;

@Service
public class DefaultDataInitializerService {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ReviewService reviewService;


    @PostConstruct
    public void init() {

        String firstUser = "admin";
        String secondUser = "user";
        String thirdUser = "mario";
        String fourthUser = "luigi";
        String fifthUser = "testUser";


        //Create som default users
        attempt(() -> {
            return userService.createUser(
                    firstUser, "Admin", "Adminson","admin@email.com", "1234", "admin");

        });

        attempt(() -> {
            return userService.createUser(
                    secondUser, "Example", "Exampleman","exampleUser@email.com", "1234", "user");

        });

        attempt(() -> {
            return userService.createUser(
                    thirdUser, "Mario", "Surname","mario@email.com", "1234", "user");

        });

        attempt(() -> {
            return userService.createUser(
                    fourthUser, "Luigi", "Surname","luigi@email.com", "1234", "user");

        });

        attempt(() -> {
            return userService.createUser(
                    fifthUser, "Test User", "Testson","testUser@email.com", "1234", "user");

        });


        //Create som default movies
        Long firstMovie = attempt(() ->
                movieService.createMovie("The Shawshank Redemption", "Frank Darabont", "drama", 1994L)
        );

        Long secondMovie = attempt(() ->
                movieService.createMovie("The Godfather", "Francis Ford Coppola", "crime", 1972L)
        );

        Long thirdMovie = attempt(() ->
                movieService.createMovie("The Dark Knight", "Christopher Nolan", "action", 2008L)
        );

        Long fourthMovie = attempt(() ->
                movieService.createMovie("The Lord of the Rings: The Return of the King", "Peter Jackson", "adventure", 2003L)
        );

        Long fifthMovie = attempt(() ->
                movieService.createMovie("Disaster Movie", "Jason Friedberg, Aaron Seltzer", "comedy", 2008L)
        );

        Long sixthMovie = attempt(() ->
                movieService.createMovie("Jaws: The Revenge", "Joseph Sargent", "horror", 1987L)
        );

        Long seventhMovie = attempt(() ->
                movieService.createMovie("Exorcist II: The Heretic", "John Boorman", "horror", 1977L)
        );

        Long eigthMovie = attempt(() ->
                movieService.createMovie("The Terminator", "James Cameron", "action", 1984L)
        );

        Long ninthMovie = attempt(() ->
                movieService.createMovie("Monsters, Inc.", "Pete Docter", "comedy", 2001L)
        );

        Long tenthMovie = attempt(() ->
                movieService.createMovie("Rocky", "John G. Avildsen", "drama", 1976L)
        );

        String review1 = "Very good";
        int rating1 = 5;
        String review2 = "Bad";
        int rating2 = 2;
        String review3 = "Ok";
        int rating3 = 3;
        String review4 = "Great";
        int rating4 = 4;
        String review5 = "Very bad";
        int rating5 = 1;


        reviewService.newReview(firstMovie, firstUser, review1, rating1);
        reviewService.newReview(firstMovie, secondUser, review2, rating2);
        reviewService.newReview(firstMovie, thirdUser, review3, rating3);

        reviewService.newReview(secondMovie, fourthUser, review1, rating1);
        reviewService.newReview(secondMovie, fifthUser, review1, rating1);
        reviewService.newReview(secondMovie, thirdUser, review3, rating3);

        reviewService.newReview(thirdMovie, firstUser, review1, rating1);
        reviewService.newReview(thirdMovie, secondUser, review1, rating1);
        reviewService.newReview(thirdMovie, thirdUser, review1, rating1);

        reviewService.newReview(fourthMovie, fifthUser, review4, rating4);
        reviewService.newReview(fourthMovie, fourthUser, review4, rating4);
        reviewService.newReview(fourthMovie, firstUser, review1, rating1);

        reviewService.newReview(fifthMovie, fifthUser, review5, rating5);
        reviewService.newReview(fifthMovie, thirdUser, review5, rating5);
        reviewService.newReview(fifthMovie, secondUser, review2, rating2);

        reviewService.newReview(sixthMovie, firstUser, review5, rating5);
        reviewService.newReview(sixthMovie, secondUser, review2, rating2);
        reviewService.newReview(sixthMovie, thirdUser, review3, rating3);

        reviewService.newReview(seventhMovie, thirdUser, review2, rating2);
        reviewService.newReview(seventhMovie, fourthUser, review2, rating2);
        reviewService.newReview(seventhMovie, fifthUser, review3, rating3);

        reviewService.newReview(eigthMovie, fifthUser, review1, rating1);
        reviewService.newReview(eigthMovie, firstUser, review4, rating4);
        reviewService.newReview(eigthMovie, secondUser, review4, rating4);

        reviewService.newReview(ninthMovie, secondUser, review3, rating3);
        reviewService.newReview(ninthMovie, thirdUser, review4, rating4);
        reviewService.newReview(ninthMovie, fourthUser, review4, rating4);

        reviewService.newReview(tenthMovie, fourthUser, review1, rating1);
        reviewService.newReview(tenthMovie, fifthUser, review1, rating1);
        reviewService.newReview(tenthMovie, secondUser, review4, rating4);
    }


    private <T> T attempt(Supplier<T> lambda) {
        try {
            return lambda.get();
        } catch (Exception e) {
            return null;
        }
    }


}
