package myTestPackage;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class UserInteractionTest {
    static WebDriver driver = new FirefoxDriver();
    static WebDriverWait wait;
    public static String page = "http://compendiumdev.co.uk/selenium/gui_user_interactions.html";

    @BeforeClass
    //before each test get fresh form page and wait until Title is present
    public static  void getFormPage() {
        driver.get(page);
        wait = new WebDriverWait(driver, 10);
        wait.until(presenceOfElementLocated(By.cssSelector("title")));

    }

    @AfterClass
    public static void closeBrowser() {
        driver.quit();
    }

    @Before
    public void refreshPage () {
        driver.navigate().refresh();
        wait.until(presenceOfElementLocated(By.cssSelector("title")));
        driver.findElement(By.tagName("html")).click();
    }

    @Test
    public void drag1OnDrop1Test() {

        new Actions(driver).dragAndDrop(driver.findElement(By.cssSelector("#draggable1")),
                driver.findElement(By.cssSelector("#droppable1"))).perform();
        assertThat(driver.findElement(By.cssSelector("#droppable1>p")).getText(), is("Dropped!"));
    }

    @Test
    public void drag2OnDrop1Test() {

        new Actions(driver).dragAndDrop(driver.findElement(By.cssSelector("#draggable2")),
                driver.findElement(By.cssSelector("#droppable1"))).perform();
        assertThat(driver.findElement(By.cssSelector("#droppable1>p")).getText(), is("Get Off Me!"));
    }
    @Test
    public void bwaHaHaTest() {

        new Actions(driver).moveToElement(driver.findElement(By.cssSelector("#draggable1>p"))).sendKeys(Keys.CONTROL+"b").perform();
        assertThat(driver.findElement(By.cssSelector("#draggable1>p")).getText(), is("Bwa! Ha! Ha!"));
    }
    @Test
    public void canvasTest() {
        WebElement canvas = driver.findElement(By.id("canvas"));
        //WebElement eventList = driver.findElement(By.id("keyeventslist"));

        //int eventCount = eventList.findElements(By.tagName("li")).size();

        new Actions(driver).
                // click doesn't do it, need to click and hold
                        //click(canvas).
                        clickAndHold(canvas).
                moveByOffset(10,10).
                release().
                perform();
        //Actions act = new Actions(driver);
        //act.clickAndHold(driver.findElement(By.cssSelector("#canvas"))).moveByOffset(100, -100).release().perform();
                //.clickAndHold().moveByOffset(100,100).release().perform();
        //assertThat(driver.findElement(By.cssSelector("#draggable1>p")).getText(), is("Bwa! Ha! Ha!"));
    }
}
