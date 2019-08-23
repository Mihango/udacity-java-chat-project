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

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class ChatTest {

    WebDriver webDriver;

    @Test
    public void joinChat() throws Exception {
        webDriver = new HtmlUnitDriver(true);
        webDriver.get("http://localhost:8082/index?username=userKay");

        webDriver.findElement(By.id("msg")).sendKeys("Hello");
        webDriver.findElement(By.id("btn_chat")).click();

        new WebDriverWait(webDriver, 10).until((ExpectedCondition<Boolean>) webDriver -> {
            assert webDriver != null;
            String currentCount = webDriver.findElement(By.xpath("//*[@class='mdui-chip']/.//span[contains(@class,'chat-num')]")).getAttribute("innerHTML");
            System.out.println("Current count >>>>>>> " +currentCount);
            return !currentCount.isEmpty();
        });
    }

    @Test
    public void sendMessage() throws Exception {
        webDriver = new HtmlUnitDriver(true);
        webDriver.get("http://localhost:8082/index?username=userKay");

        webDriver.findElement(By.id("msg")).sendKeys("Hello");
        webDriver.findElement(By.id("btn_chat")).click();

        new WebDriverWait(webDriver, 10).until((ExpectedCondition<Boolean>) webDriver -> {
            assert webDriver != null;
            List<WebElement> webElements = webDriver.findElement(By.className("message-container")).findElements(By.xpath("//*[@class='mdui-card']/.//div[contains(@class,'mdui-card-content message-content')]"));
            int elementSize = webElements.size();
            System.out.println("Chat >>>>>>>>> " +webElements.get(elementSize - 1).getText());
           return webElements.get(elementSize - 1).getText().equals("userKay：Hello");
        });
    }
}
