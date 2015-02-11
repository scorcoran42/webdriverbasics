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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class FramesTest {
    public static WebDriver driver = new FirefoxDriver();
    public static WebDriverWait wait = new WebDriverWait(driver, 10);
    final static String page = "http://compendiumdev.co.uk/selenium/frames";

    @BeforeClass
    public static void getPage () {
        driver.get(page);
        wait.until(presenceOfAllElementsLocatedBy(By.tagName("title")));
    }
    @Before
    public void refresh(){driver.navigate().refresh();}
    @AfterClass
    public static void shutDown () {driver.quit();}
    @Test
    public void greenPageTest () {
        assertEquals("Frameset Example Title (Example 6)", driver.getTitle());
        driver.switchTo().frame("content");
        driver.findElement(By.linkText("Load green page")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("#green")));
        WebElement originalLink = driver.findElement(By.linkText("Back to original page"));
        assertEquals("Back to original page", originalLink.getText());
        originalLink.click();
        assertEquals("Content", driver.findElement(By.tagName("h1")).getText());
    }

     @Test
     public void iFramesTest() {
         assertEquals("Frameset Example Title (Example 6)", driver.getTitle());
         driver.switchTo().frame("menu");
         driver.findElement(By.cssSelector("a[href='iframe.html']")).click();
         wait.until(textToBePresentInElementLocated(By.tagName("h4"), "Iframe Below"));
         driver.switchTo().frame(0);
         driver.findElement(By.linkText("Example 5")).click();
         wait.until(titleIs("Frameset Example Title (Example 5)"));
         driver.switchTo().frame("content");
         driver.findElement(By.cssSelector("a[href='index.html']")).click();
         wait.until(titleIs("Frameset Example Title (Example 6)"));
    }
}
