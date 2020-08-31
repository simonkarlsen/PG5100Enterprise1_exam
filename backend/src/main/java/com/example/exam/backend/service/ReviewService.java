// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/service/CategoryService.java

//Used altered code (ListIterator) from this link in newReview():
//https://stackoverflow.com/questions/18410035/ways-to-iterate-over-a-list-in-java

// Used altered code from this link in 'computeAverageStars()'
//https://mkyong.com/java/java-display-double-in-2-decimal-points/

package com.example.exam.backend.service;

import com.example.exam.backend.entity.Movie;
import com.example.exam.backend.entity.Review;
import com.example.exam.backend.entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@Service
@Transactional
public class ReviewService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EntityManager em;

    public List<Review> getAllReviews() {
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r ORDER BY r.createdDate DESC", Review.class);
        List<Review> allMovies = query.getResultList();
        allMovies.forEach(u -> u.getMovieInformation().getTitle());
        return allMovies;
    }


    public Long newReview(Long movieId, String userId, String reviewText, int stars) {
        Movie movie = em.find(Movie.class, movieId);
        Users users = em.find(Users.class, userId);

        if (movie == null) {
            throw new IllegalArgumentException("Movie not found");
        }

        if (users == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Use of ListIterator from stackoverflow link
        List<Review> reviewList = users.getReviews();
        boolean exists = false;
        for (ListIterator<Review> iterator = reviewList.listIterator(); iterator.hasNext(); ) {
            Review review = iterator.next();
            if (review.getMovieInformation().getId().equals(movieId)) {
                exists = true;
            }
        }

        if (exists) {
            logger.info("User {} has already made a review for this movie", users.getName());
            return null;
        } else {
            movie.setReviewCount(movie.getReviewCount() + 1);

            int newReviewCount = movie.getReviewCount();

            Review review = new Review();
            review.setReviewOwner(users);
            review.setMovieInformation(movie);
            review.setReviewText(reviewText);

            double averageStars = computeAverageStars(movie.getTotalStars(), stars, newReviewCount);

            review.setStars(stars);
            movie.setTotalStars(movie.getTotalStars() + stars);
            movie.setAverageStars(averageStars);
            review.setCreatedDate(new Date());

            em.persist(review);

            return review.getId();
        }
    }

    public double computeAverageStars(double currentStars, int newStarsRating, int reviewCount) {
        double averageStars = (currentStars + newStarsRating) / reviewCount;

        BigDecimal bd = new BigDecimal(averageStars).setScale(1, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }


    public List<Review> filterReviewByUser(String userId) {
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r WHERE r.reviewOwner.userID =?1", Review.class);
        query.setParameter(1, userId);

        return query.getResultList();
    }

    public List<Review> filterByStars() {
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r ORDER BY r.stars DESC", Review.class);

        return query.getResultList();
    }


    public List<Review> filterByTime() {
        TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r ORDER BY r.createdDate DESC", Review.class);

        return query.getResultList();
    }


}
