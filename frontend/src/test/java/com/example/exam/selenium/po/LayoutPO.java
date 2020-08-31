// Altered code from course (lecturer) has been used in this file
//https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/test/java/org/tsdes/intro/exercises/quizgame/selenium/po/LayoutPO.java

package com.example.exam.selenium.po;

import com.example.exam.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class LayoutPO extends PageObject {

    public LayoutPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public LayoutPO(PageObject other) {
        super(other);
    }

    public SignUpPO toSignUp() {

        clickAndWait("signupLink");

        SignUpPO po = new SignUpPO(this);
        assertTrue(po.onPage());

        return po;
    }

    public IndexPO doLogout() {
        clickAndWait("logoutBtn");

        IndexPO po = new IndexPO(this);
        assertTrue(po.onPage());

        return po;
    }

    public boolean isLoggedIn() {

        return getDriver().findElements(By.id("logoutBtn")).size() > 0 &&
                getDriver().findElements((By.id("signupLink"))).isEmpty();
    }

    public boolean isInFirstColumn(String id) {
        List<WebElement> table = getDriver().findElements(By.xpath("//*[@id=\"movieTable\"]//tbody//td[1]"));
        boolean isFound = false;
        for (WebElement column : table) {
            if (column.getText().equals(id)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

}
