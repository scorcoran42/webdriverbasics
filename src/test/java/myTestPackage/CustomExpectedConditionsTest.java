package myTestPackage;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stuart on 3/31/2015.
 */
public class CustomExpectedConditionsTest {
    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait;
    String page = "http://compendiumdev.co.uk/selenium/basic_redirect.html";

    @Test
    public void waitForNotTitle () {
        driver.get(page);
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("title")));

        // title is "Basic Redirects", click on 5 sec link wait for title to not be that
        driver.findElement(By.id("delaygotobasic")).click();
        wait = new WebDriverWait(driver, 10);
        wait.until(new titleDoesNotContain("Basic Redirects"));
        assertEquals("Basic Web Page Title", driver.getTitle());

        // go back
        driver.navigate().back();

        // title is "Basic Redirects", click on 2 sec link wait for title to not be that
        driver.findElement(By.id("delaygotobounce")).click();
        wait = new WebDriverWait(driver, 10);
        wait.until(new titleDoesNotContain("Basic Redirects"));
        assertEquals("Bounce", driver.getTitle());

    }

    private class titleDoesNotContain implements ExpectedCondition<String> {
        private String excludeString;

        public titleDoesNotContain(String notThisString) {
            this.excludeString = notThisString;
        }
        @Override
        public String apply(WebDriver webdriver){
                String title = webdriver.getTitle();
                if (title.contains(this.excludeString)) {
                    return null;
                }else{
                    return title;
                }
        }
    }
}


