package edu.udacity.java.nano.chat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoginTest {

    WebDriver webDriver;

    @Test
    public void login() throws Exception {
        webDriver = new HtmlUnitDriver(true);
        webDriver.get("http://localhost:8082/");

        webDriver.findElement(By.id("username")).sendKeys("userKay");
        WebElement loginLink = webDriver.findElement(By.id("btn_login"));
        loginLink.click();

        new WebDriverWait(webDriver, 10).until((ExpectedCondition<Boolean>) webDriver -> {
            assert webDriver != null;
            String url = webDriver.getCurrentUrl();
            return url.equals("http://localhost:8082/index?username=userKay");
        });
    }

    @Test
    public void logout() throws Exception {
        webDriver = new HtmlUnitDriver(true);
        webDriver.get("http://localhost:8082/index?username=jose");

        WebElement logoutLink = webDriver.findElement(By.id("exit_app"));
        logoutLink.click();

        new WebDriverWait(webDriver, 10).until((ExpectedCondition<Boolean>) webDriver -> {
            assert webDriver != null;
            String url = webDriver.getCurrentUrl();
            return url.equals("http://localhost:8082/");
        });
    }
}
