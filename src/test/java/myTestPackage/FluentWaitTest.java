package myTestPackage;

import com.gargoylesoftware.htmlunit.WebAssert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import static org.junit.Assert.assertEquals;

/**
 * Created by Stuart on 4/1/2015.
 */
public class FluentWaitTest {
    WebDriver driver = new FirefoxDriver();
    WebElement timeline;

    @Test
    public void testFluent(){
        // goto javascript_countdown page
        driver.get("http://compendiumdev.co.uk/selenium/javascript_countdown.html");
        timeline = driver.findElement(By.id("javascript_countdown_time"));
        // find clock element and extract last two chars
        String timeChars = new FluentWait<WebElement>(timeline).
                withTimeout(10, TimeUnit.SECONDS).
                pollingEvery(20, TimeUnit.MILLISECONDS).
                until(new com.google.common.base.Function<WebElement, String>() {
                          @Override
                          public String apply(WebElement element) {
                              String timeText = element.getText();
                              return timeText.endsWith("04") ? timeText : null;
                              }
                      }
                    );

        assertEquals("01:01:04",timeChars);
        //
        // Fluent wait until they are 04

        // assert they are 04
        driver.quit();
    }
}
