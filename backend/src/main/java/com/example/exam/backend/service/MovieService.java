// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/service/MatchStatsService.java

package com.example.exam.backend.service;

import com.example.exam.backend.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
@Transactional
public class MovieService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EntityManager em;
    @Autowired
    private ReviewService reviewService;

    public List<Movie> getAllMovies() {
        TypedQuery query = em.createQuery("SELECT m FROM Movie m ORDER BY m.averageStars DESC", Movie.class);
        List<Movie> allItems = query.getResultList();
        allItems.forEach(u -> u.getReviewInfo().size());
        return allItems;
    }


    public Long createMovie(String title, String director, String genre, Long releaseYear) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDirector(director);
        movie.setGenre(genre);
        movie.setReleaseYear(releaseYear);

        em.persist(movie);

        return movie.getId();
    }


    public List<Movie> getMovies() {
        TypedQuery query = em.createQuery("SELECT m FROM Movie m", Movie.class);
        return query.getResultList();
    }


    public Movie getMovie(Long movieId) {
        Movie movie = em.find(Movie.class, movieId);

        if (movie == null) {
            throw new IllegalArgumentException("No movie with this id is found");
        }

        movie.getReviewInfo().size();

        return movie;
    }


    public boolean removeMovie(Long movieID) {
        Movie movie = em.find(Movie.class, movieID);

        if (movie == null) {
            throw new IllegalArgumentException("Movie does not exist");
        }

        em.remove(movie);

        return true;
    }


    // All of these underneath are not functions used in the final version of the application,
    // but I will leave them here to show what could have been implemented:

//    public List<Movie> filterByReleaseYear(Long releaseYear) {
//        TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m WHERE m.releaseYear =?1", Movie.class);
//
//        query.setParameter(1, releaseYear);
//
//        return query.getResultList();
//    }
//
//
//    public List<Movie> filterByTitle(String title) {
//        TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m WHERE m.title =?1 ORDER BY m.title ASC", Movie.class);
//        query.setParameter(1, title);
//
//        return query.getResultList();
//    }
//
//    public List<Movie> filterByGenre(String type) {
//        TypedQuery<Movie> query = em.createQuery("SELECT m FROM Movie m WHERE m.genre =?1", Movie.class);
//
//        query.setParameter(1, type);
//
//        return query.getResultList();
//    }


}
