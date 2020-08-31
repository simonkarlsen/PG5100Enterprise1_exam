// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/main/java/org/tsdes/intro/exercises/quizgame/frontend/RedirectForwardHandler.java


package com.example.exam.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RedirectForwardHandler {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String forward() {
        return "forward:index.jsf";
    }
}
