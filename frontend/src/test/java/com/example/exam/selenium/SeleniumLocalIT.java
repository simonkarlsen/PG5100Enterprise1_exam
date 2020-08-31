// Altered code from course (lecturer) has been used in this file
// https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/test/java/org/tsdes/intro/exercises/quizgame/selenium/SeleniumLocalIT.java
// https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/test/java/org/tsdes/intro/exercises/quizgame/selenium/SeleniumTestBase.java

package com.example.exam.selenium;

import com.example.exam.Application;
import com.example.exam.backend.service.MovieService;
import com.example.exam.backend.service.ReviewService;
import com.example.exam.selenium.po.IndexPO;
import com.example.exam.selenium.po.MovieDetailsPO;
import com.example.exam.selenium.po.SignUpPO;
import com.example.exam.selenium.po.UserPO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
public class SeleniumLocalIT {

    private static final AtomicInteger counter = new AtomicInteger(0);
    private static WebDriver driver;
    @Autowired
    MovieService movieService;
    ReviewService reviewService;
    @LocalServerPort
    private int port;
    private IndexPO home;
    private UserPO userPO;
    private MovieDetailsPO movieDetailsPO;

    @BeforeAll
    public static void initClass() {

        driver = SeleniumDriverHandler.getChromeDriver();

        assumeTrue(driver != null, "Cannot find/initialize Chrome driver");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected String getServerHost() {
        return "localhost";
    }

    protected int getServerPort() {
        return port;
    }

    private String getUniqueId() {
        return "foo_SeleniumLocalIT_" + counter.getAndIncrement();
    }

    private IndexPO createNewUser(String username, String password) {
        home.toStartingPage();
        SignUpPO signUpPO = home.toSignUp();
        IndexPO indexPO = signUpPO.signUP(username, password);
        assertNotNull(indexPO);

        return indexPO;
    }

    @BeforeEach
    public void initTest() {
        getDriver().manage().deleteAllCookies();
        home = new IndexPO(getDriver(), getServerHost(), getServerPort());
        home.toStartingPage();
        assertTrue(home.onPage(), "Failed to start from home page");
    }


    // Should pass if you are logged in.
    @Test
    public void testUserInfoIsDisplayed() {
        UserPO userPO = home.getUsersInfo();
        assertNull(userPO);
        String userID = getUniqueId();
        home = createNewUser(userID, "1234");
        userPO = home.getUsersInfo();
        assertNotNull(userPO);
        assertTrue(userPO.getUserName().contains(userID));
        userPO.doLogout();
        userPO = home.getUsersInfo();
        assertNull(userPO);
    }

    // Should display 10 sample movies on home page, but also counts the top column
    // Therefore, the expected result should be 11
    @Test
    public void testDefaultMovies() {
        assertEquals(11, home.getAmountOfDisplayedMovies());
    }


    // Did not manage to get these to work
//    @Test
//    public void testWriteReview() {
//        //Not logged in
//        UserPO userPO = home.getUsersInfo();
//        assertNull(userPO);
//
//        MovieDetailsPO movieDetailsPO = home.getMovieDetailsinfo();
//        home.clickAndWait("movieDetailsLink");
//        assertTrue(movieDetailsPO.getDriver().getPageSource().contains("Movie details"));
//        assertFalse(movieDetailsPO.getDriver().getPageSource().contains("Welcome"));
//        assertFalse(movieDetailsPO.getDriver().getPageSource().contains("Write a review:"));
//
//        //logged in
//        String userID = getUniqueId();
//        home = createNewUser(userID, "1234");
//        userPO = home.getUsersInfo();
//        assertNotNull(userPO);
//        assertTrue(userPO.getUserName().contains(userID));
//
//        home.clickAndWait("movieDetailsLink");
//
//        movieDetailsPO.writeReview("This is a review done by selenium", "5");
//        assertTrue(movieDetailsPO.getDriver().getPageSource().contains("This is a review done by selenium"));
//
//        // log out
//        userPO.doLogout();
//        userPO = home.getUsersInfo();
//        assertNull(userPO);
//
//        home.clickAndWait("movieDetailsLink");
//
//        assertTrue(movieDetailsPO.getDriver().getPageSource().contains("This is a review done by selenium"));
//    }


//    @Test
//    public void testStars() {
//
//    }

//    @Test
//    public void testSorting() {
//
//    }

    // extra test
    @Test
    public void testNoUserReviewsOnUserPage() {
        UserPO userPO = home.getUsersInfo();
        assertNull(userPO);
        String userID = getUniqueId();
        home = createNewUser(userID, "1234");
        userPO = home.getUsersInfo();
        assertNotNull(userPO);
        assertTrue(userPO.getUserName().contains(userID));
        assertTrue(userPO.getDriver().getPageSource().contains("You have no reviews"));
        assertEquals(0, home.getAmountOfDisplayedMoviesOnUserPage());
    }


}
