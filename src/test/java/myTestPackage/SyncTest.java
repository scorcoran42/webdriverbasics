package myTestPackage;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class SyncTest{
    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait;
    public static String page = "http://compendiumdev.co.uk/selenium/basic_ajax.html";

    // method to click on Submit and wait for new form by waiting until "Submitted Values" is present
    public void clickSubmitAndWait (){
        driver.findElement(By.cssSelector("[value='Code In It']")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("body"), "Submitted Values"));
    }


    @Before
    //before each test get fresh form page and wait until Title is present
    public void getFormPage(){
        driver.get(page);
        wait = new WebDriverWait(driver,10);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void basicAjaxTest () {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("title")));
        Select combo1 = new Select(driver.findElement(By.cssSelector("#combo1")));
        Select combo2 = new Select(driver.findElement(By.cssSelector("#combo2")));

        combo1.selectByVisibleText("Server");
        new WebDriverWait(driver,10).until
                (ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#combo2"), "Cobol"));
        combo2.selectByVisibleText("Java");

        clickSubmitAndWait();

        assertThat(driver.findElement(By.cssSelector("#_valuelanguage_id")).getText(), is("23"));
    }
}
