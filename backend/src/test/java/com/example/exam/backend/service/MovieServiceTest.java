// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/test/java/org/tsdes/intro/exercises/quizgame/backend/service/MatchStatsServiceTest.java

package com.example.exam.backend.service;

import com.example.exam.backend.TestApplication;
import com.example.exam.backend.entity.Movie;
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
public class MovieServiceTest extends ServiceTestBase{

    @Autowired
    private MovieService movieService;


    @Test
    public void testNoMoviesExists(){
        assertEquals(0, movieService.getMovies().size());
    }

    @Test
    public void testCreateMovie(){
        Long movieId = movieService.createMovie("test", "test", "test", 2020L);
        assertNotNull(movieId);

        Movie movie = movieService.getMovie(movieId);
        assertNotNull(movie);
    }

    @Test
    public void testDeleteMovie(){
        Long movieId = movieService.createMovie("test", "test", "test", 2020L);
        assertNotNull(movieId);

        Movie movie = movieService.getMovie(movieId);
        boolean deleted = movieService.removeMovie(movie.getId());
        assertTrue(deleted);

        // When running the test with uncommenting the code underneath that's commented out , it should throw:
        // java.lang.IllegalArgumentException: No movie with this id is found
        // It shows that the movie truly is deleted and does not exist at all

//        Movie movie1 = movieService.getMovie(movieId);
//        assertNull(movie1);
    }

    @Test
    public void getAllMovies(){
        Long firstMovie = movieService.createMovie("test", "test", "test", 2020L);
        Long secondMovie = movieService.createMovie("test2", "test2", "test2", 2020L);
        assertNotNull(firstMovie);
        assertNotNull(secondMovie);

        List<Movie> allMovies = movieService.getAllMovies();
        assertEquals(2, allMovies.size());
    }




    // All of these underneath are not functions used in the final version of the application,
    // but I will leave them here to show what could have been implemented:
//
//    @Test
//    public void testFilterReleaseYear() {
//        Long firstMovie = movieService.createMovie("test", "test", "test", 2019L);
//        Long secondMovie = movieService.createMovie("test2", "test2", "test2", 2020L);
//        Long thirdMovie = movieService.createMovie("test2", "test2", "test2", 2020L);
//
//        assertNotNull(firstMovie);
//        assertNotNull(secondMovie);
//        assertNotNull(thirdMovie);
//
//        List<Movie> pastYear = movieService.filterByReleaseYear(2019L);
//        List<Movie> presentYear = movieService.filterByReleaseYear(2020L);
//
//        // should be two movies with the present year (2020)
//        assertEquals(2,presentYear.size());
//        // and one movie with the past year (2019)
//        assertEquals(1,pastYear.size());
//    }
//
//    @Test
//    public void testFilterGenre() {
//        Long firstMovie = movieService.createMovie("test", "test", "test", 2020L);
//        Long secondMovie = movieService.createMovie("test2", "test2", "test2", 2020L);
//        Long thirdMovie = movieService.createMovie("test2", "test2", "test2", 2020L);
//
//        assertNotNull(firstMovie);
//        assertNotNull(secondMovie);
//        assertNotNull(thirdMovie);
//
//        List<Movie> testGenre1 = movieService.filterByGenre("test");
//        List<Movie> testGenre2 = movieService.filterByGenre("test2");
//
//        // should be one genre that is called test,
//        assertEquals(1,testGenre1.size());
//        // and one two that is called test2
//        assertEquals(2,testGenre2.size());
//    }
//
//
//    @Test
//    public void testFilterTitle(){
//        Long firstMovie = movieService.createMovie("test", "test", "test", 2020L);
//        Long secondMovie = movieService.createMovie("test2", "test2", "test2", 2020L);
//        Long thirdMovie = movieService.createMovie("test2", "test2", "test2", 2020L);
//
//        assertNotNull(firstMovie);
//        assertNotNull(secondMovie);
//        assertNotNull(thirdMovie);
//
//        List<Movie> title1 = movieService.filterByTitle("test2");
//        List<Movie> title2 = movieService.filterByTitle("test");
//
//        // should be two movies called test2
//        assertEquals(2, title1.size());
//        // and one movie called test
//        assertEquals(1, title2.size());
//    }

}
