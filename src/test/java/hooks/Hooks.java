package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import utils.BaseDriver;

public class Hooks {
    private WebDriver driver;

    @Before
    public void setup() {
        BaseDriver.setUp();
    }

    @After
    public void teardown() {
        BaseDriver.tearDown();
    }
}