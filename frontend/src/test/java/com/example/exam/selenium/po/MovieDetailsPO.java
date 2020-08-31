// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/test/java/org/tsdes/intro/exercises/quizgame/selenium/po/SignUpPO.java
package com.example.exam.selenium.po;

import com.example.exam.selenium.PageObject;
import com.sun.faces.taglib.html_basic.DataTableTag;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.InputSource;
import org.openqa.selenium.support.ui.Select;

public class MovieDetailsPO extends LayoutPO {

    public MovieDetailsPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public MovieDetailsPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean onPage() {
        return getDriver().getTitle().contains("Movie details");
    }

    public MovieDetailsPO selectOrderBy(String selection) {
        WebElement dropDown = getDriver().findElement(By.id("selectOneID"));
        Select searchType = new Select(dropDown);
        searchType.selectByValue(selection);
        MovieDetailsPO movieDetailsPO = new MovieDetailsPO(this);
        clickAndWait("searchBtn");
        Assertions.assertTrue(movieDetailsPO.onPage());
        return movieDetailsPO;
    }


    public MovieDetailsPO writeReview(String review, String rating) {
        // cant find the input ids...
        setText("inputReview1", review);
        setText("inputRating1", rating);
        clickAndWait("submitBtn");

        MovieDetailsPO movieDetailsPO = new MovieDetailsPO(this);
        Assertions.assertTrue(movieDetailsPO.onPage());

        return movieDetailsPO;
    }


}
