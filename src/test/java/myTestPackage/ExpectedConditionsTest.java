package myTestPackage;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExpectedConditionsTest {
    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait;
    public static String page = "http://compendiumdev.co.uk/selenium/basic_ajax.html";

    @Before
    //before each test get fresh form page and wait until Title is present
    public void getFormPage(){
        driver.get(page);
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("title")));
    }
    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void inlineExpCondTest () {

        Select combo1 = new Select(driver.findElement(By.cssSelector("#combo1")));

        combo1.selectByVisibleText("Server");
        WebElement webEl = new WebDriverWait(driver,10).until(
                new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver webdriver) {
                        WebElement eli = webdriver.findElement(By.cssSelector("option[value='23']"));
                        if (eli.isDisplayed()) {
                            return eli;
                        } else {
                            return null;
                        }
                    }
                }
        );

        webEl.click();
        assertThat(webEl.getText(), is("Java"));
    }

}
