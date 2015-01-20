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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SelectTest {

    static WebDriver driver;
    public static String page = "http://compendiumdev.co.uk/selenium/basic_html_form.html";

    // method to click on Submit and wait for new form by waiting until "Submitted Values" is present
    public void clickSubmitAndWait (){
        driver.findElement(By.cssSelector("[type='submit']")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("body"), "Submitted Values"));
    }

    @BeforeClass
    public static void createDriver () {
        driver = new FirefoxDriver();
    }

    @Before
    //before each test get fresh form page and wait until Title is present
    public void getFormPage(){
        driver.get(page);
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("title")));
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }

    @Test
    public void multipleSelectWithSelectClassTest () {

        List<WebElement> selects;
        // find multiple select box as a Select webelement
        Select element = new Select(driver.findElement(By.cssSelector("select[name='multipleselect[]']")));
        int i;

        // clear all selections
        element.deselectAll();

        // select first using byIndex
        element.selectByIndex(0);
        // select second using byValue
        element.selectByValue("ms2");
        // select third using byVisibleText
        element.selectByVisibleText("Selection Item 3");

        // click on Submit and wait for new form by waiting until "Submitted Values" is present
        clickSubmitAndWait();

        //find all the selections that are selected

        selects = driver.findElements(By.cssSelector("#_multipleselect li"));
        // assert that only 3 selections were selected and that they were 1, 2, and 3
        assertThat(selects.size(), is(3));
        i = 1;
        for (WebElement e : selects) {
            assertThat(e.getText(), is("ms" + i));
            i++;
        }
    }

}
