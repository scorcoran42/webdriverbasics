package myTestPackage;


import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by Stuart on 12/16/2014.
 */
public class InterrogationTest {
    static WebDriver driver;
    String pageBasic = "http://compendiumdev.co.uk/selenium/basic_web_page.html";
    String pageFindBy = "http://compendiumdev.co.uk/selenium/find_by_playground.php";

    @BeforeClass
    public static void createDriver () {
        driver = new FirefoxDriver();
    }

    @Test
    public void driverLevelInterrogationTest (){
        driver.get(pageBasic);
        assertThat("Title should be Basic Web Page Title", driver.getTitle(), is("Basic Web Page Title"));
        assertThat("URL should be ...", driver.getCurrentUrl(), is(pageBasic));
        assertThat("Page source should contain a paragraph...", driver.getPageSource(), containsString("A paragraph of text"));
    }
    @Test
    public void checkBasicWebPage () {

        driver.get(pageFindBy);

        assertThat(driver.findElement(By.id("p20")).getText(), containsString("This is t p"));
        assertThat(driver.findElement(By.name("pName19")).getText(), containsString("This is s p"));
        assertThat(driver.findElement(By.className("normal")).getText(), containsString("This is a p"));
        assertThat(driver.findElement(By.linkText("jump to para 12")).getAttribute("id"), is("a38"));
        assertThat(driver.findElement(By.partialLinkText("jump to para")).getAttribute("id"), is("a26"));
    }
    @Test
    public void findAllElements () {
        List<WebElement> elements;

        driver.get(pageFindBy);

        elements = driver.findElements(By.tagName("div"));
        assertThat(elements.size(), is(19));
        elements = driver.findElements(By.partialLinkText("para"));
        assertThat(elements.size(), is(25));
        elements = driver.findElements(By.className("nestedDiv"));
        assertThat(elements.size(), is(16));
        elements = driver.findElements(By.tagName("p"));
        assertThat(elements.size(), is(41));
    }

    @Test
    public void byIdTest (){
        driver.get(pageFindBy);

        WebElement element;
        element = driver.findElement(By.id("p31"));
        assertThat(element.getAttribute("id"), is("p31"));
    }
    @Test
    public void byNameTest (){
        driver.get(pageFindBy);

        WebElement element;
        element = driver.findElement(By.name("ulName1"));
        assertThat(element.getAttribute("id"), is("ul1"));
    }
    @Test
    public void byClassNameTest (){
        driver.get(pageFindBy);

        WebElement element;
        element = driver.findElement(By.className("specialDiv"));
        assertThat(element.getAttribute("id"), is("div1"));
    }

    @Test
    public void byTagNameTest (){
        driver.get(pageFindBy);

        WebElement element;
        element = driver.findElement(By.tagName("li"));
        assertThat(element.getAttribute("name"), is("liName1"));
    }

    @Test
    public void byCSSIdTest (){
        driver.get(pageFindBy);

        WebElement element;
        element = driver.findElement(By.cssSelector("#p31"));
        assertThat(element.getAttribute("id"), is("p31"));
    }
    @Test
    public void byCSSNameTest (){
        driver.get(pageFindBy);

        WebElement element;
        element = driver.findElement(By.cssSelector("[name='ulName1']"));
        assertThat(element.getAttribute("id"), is("ul1"));
    }

    @Test
    public void byCSSClassNameTest (){
        driver.get(pageFindBy);

        WebElement element;
        element = driver.findElement(By.cssSelector(".specialDiv"));
        assertThat(element.getAttribute("id"), is("div1"));
    }

    @Test
    public void byCSSTagNameTest (){
        driver.get(pageFindBy);

        WebElement element;
        element = driver.findElement(By.cssSelector("li"));
        assertThat(element.getAttribute("name"), is("liName1"));
    }

    @Test
    public void byXpathIdTest (){
        driver.get(pageFindBy);

        WebElement element;
        element = driver.findElement(By.xpath("//*[@id='p31']"));
        assertThat(element.getAttribute("id"), is("p31"));
    }
    @Test
    public void byXpathNameTest (){
        driver.get(pageFindBy);

        WebElement element;
        element = driver.findElement(By.xpath("//*[@name='ulName1']"));
        assertThat(element.getAttribute("id"), is("ul1"));
    }

    @Test
    public void byXpathClassNameTest (){
        driver.get(pageFindBy);

        WebElement element;
        element = driver.findElement(By.xpath("//*[@class='specialDiv']"));
        assertThat(element.getAttribute("id"), is("div1"));
    }

    @Test
    public void byXpathTagNameTest (){
        driver.get(pageFindBy);

        WebElement element;
        element = driver.findElement(By.xpath("//li"));
        assertThat(element.getAttribute("name"), is("liName1"));
    }
    @AfterClass
    public static void shutDown() {
        driver.quit();
    }
}
