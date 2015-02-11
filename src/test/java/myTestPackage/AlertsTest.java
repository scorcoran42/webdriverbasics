package myTestPackage;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class AlertsTest {
    static WebDriver driver = new FirefoxDriver();
    static WebDriverWait wait;
    static final String page = "http://compendiumdev.co.uk/selenium/alerts.html";
    Alert myAlert;

    @BeforeClass
    static public void getPage() {
        driver.get(page);
        wait = new WebDriverWait(driver,10);
        wait.until(presenceOfElementLocated(By.tagName("body")));
    }
    @AfterClass
    static public void closeDown() {driver.quit();}

    @Test
    public void alertBoxTest () {
        driver.findElement(By.id("alertexamples")).click();
        myAlert = driver.switchTo().alert();
        assertEquals("I am an alert box!", myAlert.getText());
        myAlert.dismiss();
    }
    @Test
    public void confirmBoxTest () {
        driver.findElement(By.id("confirmexample")).click();
        myAlert = driver.switchTo().alert();
        myAlert.accept();
        assertEquals("true", driver.findElement(By.id("confirmreturn")).getText());
    }
}
