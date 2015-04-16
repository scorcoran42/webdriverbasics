package myTestPackage;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stuart on 4/6/2015.
 */
public class CookieTest {
    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @Before
    public void setup(){
        driver.get("http://www.compendiumdev.co.uk/selenium/search.php");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("title")));
    }

    @Test
    public void testFirstVistit(){
        //delete number of visits cookie
        driver.manage().deleteCookieNamed("seleniumSimplifiedSearchNumVisits");
        //find and click search button, then wait for results to be present
        driver.findElement(By.cssSelector("input[name='btnG']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resultList")));
        //get number of times visited in cookie and assert = 1
        Cookie aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        assertEquals("1", aCookie.getValue());
        driver.quit();
    }
    @Test
    public void changeVisitValue (){
        String cookieName = "seleniumSimplifiedSearchNumVisits";
        Cookie newCookie;
        //delete original cookie
        driver.manage().deleteCookieNamed(cookieName);
        //set number of visits cookie in new cookie
        //build new cookie from values and change visits to 42
        newCookie = new Cookie(cookieName, "42");
        //check value
        assertEquals("42", newCookie.getValue());
        driver.quit();
    }
}
