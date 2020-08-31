// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/backend/src/main/java/org/tsdes/intro/exercises/quizgame/backend/service/UserService.java


package com.example.exam.backend.service;

import com.example.exam.backend.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;

@Service
@Transactional
public class UserService {

    @Autowired
    private EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(String userName, String name, String surname, String email, String password, String role) {
        String hashedPassword = passwordEncoder.encode(password);

        if ((em.find(Users.class, userName) != null) || (em.find(Users.class, email) != null)) {
            return false;
        }

        Users users = new Users();
        users.setUserID(userName);
        users.setName(name);
        users.setSurname(surname);
        users.setHashedPassword(hashedPassword);
        users.setEmail(email);
        users.setRoles(Collections.singleton(role));
        users.setEnabled(true);

        em.persist(users);

        return true;
    }

    public Users findUserByUserName(String userName) {
        Users users = em.find(Users.class, userName);
        if (users == null) {
            throw new IllegalStateException("No user with this username");
        }
        users.getReviews().size();
        return users;
    }

}