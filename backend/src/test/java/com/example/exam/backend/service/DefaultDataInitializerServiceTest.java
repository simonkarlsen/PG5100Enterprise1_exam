// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/test/java/org/tsdes/intro/exercises/quizgame/backend/service/DefaultDataInitializerServiceTest.java

package com.example.exam.backend.service;

import com.example.exam.backend.TestApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = BEFORE_CLASS)
class DefaultDataInitializerServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MovieService movieService;

    @Test
    public void testInit() {
        assertTrue(reviewService.getAllReviews().size() > 0);
        assertTrue(movieService.getAllMovies().stream().mapToLong(i -> i.getReviewInfo().size()).sum() > 0);
        assertTrue(movieService.getAllMovies().size() > 0);
    }

}
