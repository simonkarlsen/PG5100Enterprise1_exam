// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/test/java/org/tsdes/intro/exercises/quizgame/backend/service/ResetService.java

package com.example.exam.backend.service;

import com.example.exam.backend.entity.Review;
import com.example.exam.backend.entity.Movie;
import com.example.exam.backend.entity.Users;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Service
@Transactional
public class ResetService {
    @PersistenceContext
    private EntityManager entityManager;

    public void resetDatabase() {
        Query query = entityManager.createNativeQuery("DELETE FROM users_roles");
        query.executeUpdate();

        deleteEntities(Review.class);
        deleteEntities(Movie.class);
        deleteEntities(Users.class);
    }

    private void deleteEntities(Class<?> entity) {
        if (entity == null || entity.getAnnotation(Entity.class) == null) {
            throw new IllegalArgumentException("Invalid non-entity class");
        }

        String name = entity.getSimpleName();

        Query query = entityManager.createQuery("DELETE FROM " + name);
        query.executeUpdate();
    }

}