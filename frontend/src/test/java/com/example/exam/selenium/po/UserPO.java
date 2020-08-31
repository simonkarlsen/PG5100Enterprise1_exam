// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/test/java/org/tsdes/intro/exercises/quizgame/selenium/po/ui/MatchPO.java
package com.example.exam.selenium.po;

import com.example.exam.selenium.PageObject;
import org.openqa.selenium.WebDriver;

public class UserPO extends LayoutPO {

    public UserPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public UserPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean onPage() {
        return getDriver().getTitle().contains("User page");
    }

    public String getUserName() {
        return getText("userNameID");
    }



}
