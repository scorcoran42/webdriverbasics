package myTestPackage;

import com.google.common.base.Predicate;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * Created by Stuart on 12/18/2014.
 */
public class ManipTest {
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

    @Test
    public void checkHtmlPageTitleTest () {

        //capture current page title
        String oldTitle = driver.getTitle();

        // click on Submit and wait for new form by waiting until "Submitted Values" is present
        clickSubmitAndWait();

        //assert that new title is not the old title
        assertThat(driver.getTitle(), is(not(oldTitle)));
    }

    @Test
    public void checkCommentsTest () {

        //find Comments box, clear contents, then enter text "Lorem ipsum"
        driver.findElement(By.cssSelector("textarea")).clear();
        driver.findElement(By.cssSelector("textarea")).sendKeys("Lorem ipsum");

        // click on Submit and wait for new form by waiting until "Submitted Values" is present
        clickSubmitAndWait();

        // assert that the entered text on this page is the text entered
        assertThat(driver.findElement(By.cssSelector("#_valuecomments")).getText(), is("Lorem ipsum"));
    }

    @Test
    public void radio2Test () {

        //find radio button 2 and select it
        driver.findElement(By.cssSelector("[type='radio'][value='rd2']")).click();

        // click on Submit and wait for new form by waiting until "Submitted Values" is present
        clickSubmitAndWait();

        // assert that radio button 2 was selected
        assertThat(driver.findElement(By.cssSelector("#_valueradioval")).getText(), is("rd2"));
    }

    @Test
    public void checkbox1Test () {

        List<WebElement> chkbxes;
        int i = 0;

        // get all checkboxes
        chkbxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        //loop through all checkboxes, select cb1 if not already selected, clear all others
        for(WebElement e : chkbxes){
            if (i == 0){
                if (!(e.isSelected())){
                    e.click();
                }
                i++;
            }
            else {
                if (e.isSelected()){
                    e.click();
                }
                i++;
            }
           }

        // click on Submit and wait for new form by waiting until "Submitted Values" is present
        clickSubmitAndWait();

        //find all the checkboxes that are checked

        chkbxes = driver.findElements(By.cssSelector("#_checkboxes>ul>li"));
        // assert that only 1 checkbox is checked and that it is checkbox 1
        assertThat(chkbxes.size(), is(1));
        assertThat(driver.findElement(By.cssSelector("#_valuecheckboxes0")).getText(), is("cb1"));
    }

    @Test
    public void dropdownTest () {

        //find dropdown and select item 5
        driver.findElement(By.cssSelector("select[name='dropdown']>option[value='dd5']")).click();

        // click on Submit and wait for new form by waiting until "Submitted Values" is present
        clickSubmitAndWait();

        // assert that dropwdown item 5 was selected
        assertThat(driver.findElement(By.cssSelector("#_valuedropdown")).getText(), is("dd5"));
    }

    @Test
    public void multipleSelectTest () {

        List<WebElement> selects;
        int i = 0;

        // get all multiple select elements
        selects = driver.findElements(By.cssSelector("[multiple='multiple']>option"));

        //loop through all selects, select select 1-3, but no others
        for(WebElement e : selects){
            if (i < 3){
                if (!(e.isSelected())){
                    e.click();
                }
                i++;
            }
            else {
                if (e.isSelected()){
                    e.click();
                }
                i++;
            }
        }

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

    @Test
    public void fileTest () {

        String filename = "desktop.ini";
        String fullFilename = "C:\\Users\\Stuart\\Desktop\\desktop.ini";

        //find and click file browse button, then send full filename and ENTER
        driver.findElement(By.cssSelector("[type='file']")).sendKeys(fullFilename);

        // click on Submit and wait for new form by waiting until "Submitted Values" is present
        clickSubmitAndWait();

        // assert that filename is the one submitted
        assertThat(driver.findElement(By.cssSelector("#_valuefilename")).getText(), is(filename));
    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }
}



