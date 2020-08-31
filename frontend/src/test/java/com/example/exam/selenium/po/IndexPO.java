// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/test/java/org/tsdes/intro/exercises/quizgame/selenium/po/IndexPO.java

package com.example.exam.selenium.po;

import com.example.exam.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IndexPO extends LayoutPO {

    public IndexPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public IndexPO(PageObject other) {
        super(other);
    }

    public void toStartingPage() {
        getDriver().get(host + ":" + port);
    }


    public int getAmountOfDisplayedMovies() {
        return getDriver().findElements(By.xpath("//table//tr")).size();
    }

    public int getAmountOfDisplayedMoviesOnUserPage() {
        return getDriver().findElements(By.xpath("/html/body/form[3]/table/tbody/tr/td[0]")).size();
    }


    @Override
    public boolean onPage() {
        return getDriver().getTitle().contains("Home");
    }

    public UserPO getUserDetails(String id) {
        clickAndWait(id);
        UserPO userPO = new UserPO(this);
        assertTrue(userPO.onPage());

        return userPO;
    }

    public UserPO getUsersInfo() {
        if (getDriver().findElements(By.id("userID")).size() == 0)
            return null;
        clickAndWait("userID");
        UserPO userPO = new UserPO(this);
        assertTrue(userPO.onPage());
        return userPO;
    }

    public MovieDetailsPO getMovieDetailsinfo() {
        clickAndWait("movieDetailsLink");
        MovieDetailsPO movieDetailsPO = new MovieDetailsPO(this);
        assertTrue(movieDetailsPO.onPage());
        return movieDetailsPO;
    }


}
