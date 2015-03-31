package myTestPackage;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Random;

import static jdk.nashorn.internal.objects.NativeMath.random;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by Stuart on 3/18/2015.
 */
public class WindowManageTest {
    static WebDriver driver = new FirefoxDriver();
    static WebDriverWait wait;
    static String page = "http://compendiumdev.co.uk/selenium/bounce.html";

    @BeforeClass
    public static void getPage() {
        driver.get(page);
        wait = new WebDriverWait(driver,10);
        wait.until(titleIs("Bounce"));
    }
    @AfterClass
    public static void shutDown(){driver.quit();}

    //@Test
    public void maximizeWindow(){
        driver.manage().window().maximize();
        System.out.println("max =");
        System.out.println(driver.manage().window().getSize());
    }
    //@Test
    public void halveWindow(){
        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(691, 372));
        System.out.println("half = ");
        System.out.println(driver.manage().window().getSize());
    }
    //@Test
    public void centerWindow(){
        driver.manage().window().setSize(new Dimension(100,300));
        driver.manage().window().setPosition(new Point(641,322));
    }
    @Test
    public void bounceWindow(){
        int xmax = 1282;
        int ymax = 344;
        driver.manage().window().setSize(new Dimension(100,300));
        driver.manage().window().setPosition(new Point(641,322));
        for (int i=0; i<10; i++) {
            driver.manage().window().setPosition(new Point(new Random().nextInt(xmax),new Random().nextInt(ymax)));
        }
    }
}
