// Code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/test/java/org/tsdes/intro/exercises/quizgame/LocalApplicationRunner.java


package com.example.exam;

import org.springframework.boot.SpringApplication;

public class LocalApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, "--spring.profiles.active=test");
    }


}
