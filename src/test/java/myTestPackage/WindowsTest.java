package myTestPackage;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WindowsTest {
    public static WebDriver driver = new FirefoxDriver();
    public static WebDriverWait wait = new WebDriverWait(driver, 10);
    final static String page = "http://compendiumdev.co.uk/selenium/frames";

    @BeforeClass
    public static void getPage () {
        driver.get(page);
        wait.until(presenceOfAllElementsLocatedBy(By.tagName("title")));
    }

    @AfterClass
    public static void shutDown () {driver.quit();}

    @Test
    public void switchToNewWindow(){


        String framesWindowHandle = driver.getWindowHandle();
        assertEquals("Expected only 1 current window", 1, driver.getWindowHandles().size());

        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='http://www.seleniumsimplified.com']")).click();
        assertEquals("Expected a New Window opened", 2, driver.getWindowHandles().size());

        Set<String> myWindows = driver.getWindowHandles();
        String newWindowHandle="";

        for(String aHandle : myWindows){
            if(!framesWindowHandle.contentEquals(aHandle)){
                newWindowHandle = aHandle; break;
            }
        }

        driver.switchTo().window(newWindowHandle);

        assertTrue("Expected Selenium Simplified site",
                driver.getTitle().contains("Selenium Simplified"));
        // switch back to original window
        driver.switchTo().window(framesWindowHandle);
        assertTrue(driver.getTitle().contains("Frameset Example"));

        // open eviltester and compDev
        driver.switchTo().frame("content");
        driver.findElement(By.id("goevil")).click();
        driver.findElement(By.cssSelector("a[href='http://www.compendiumdev.co.uk']")).click();

        // switch to them by name then close them
        driver.switchTo().window("evil");
        assertTrue(driver.getTitle().contains("Home | EvilTester.com"));
        driver.close();
        driver.switchTo().window("compdev");
        assertTrue(driver.getTitle().contains("Essays"));
        driver.close();


    }
}

