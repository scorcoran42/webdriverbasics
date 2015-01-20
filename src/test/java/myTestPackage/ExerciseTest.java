package myTestPackage;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;


public class ExerciseTest {
    static int num1 = 2;
    static int num2 = 2;

    @BeforeClass
    public static void beforeClassTest (){
        num1 = 3;

    }

    @Before
    public void beforeVarTest() {
        num2 = 3;
    }
    @Test
    public void zeroTest () {
        assertEquals ("zero test", 0, num2-num1);
    }
    @Test
    public void testingBefore (){
        assertThat(num1, is(3));
        assertFalse("num shouldn't still be 2", (num1 == 2));
    }
    @AfterClass
    public static void afterClassTest () {
        System.out.println("after");
    }
}
