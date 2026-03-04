package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.Homepage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ReusableMethods;

public class LoginSteps extends ReusableMethods {

    Homepage hp = new Homepage();
    LoginPage lp = new LoginPage();

    @When("User clicks on {string} button")
    public void user_clicks_on_button(String buttonName) {
        myClick(hp.loginOpt);
    }

    @When("User enters valid email and password from config")
    public void userEntersValidEmailAndPasswordFromConfig() {
        mySendKeys(lp.emailInput, ConfigReader.getProperty("validEmail"));
        mySendKeys(lp.passwordInput, ConfigReader.getProperty("validPassword"));
        myClick(lp.loginButton);
    }

    @And("User clicks the {string} Option")
    public void userClicksTheOption(String arg0) {
        myClick(hp.myAccountOpt);
    }

    @Then("User should see {string} text or their account name")
    public void userShouldSeeTextOrTheirAccountName(String welcomeText) {
        verifyContainsText(lp.welcomeText, welcomeText);
    }

    @When("User enters invalid email {string} and password {string}")
    public void userEntersInvalidEmailAndPassword(String email, String password) {
        mySendKeys(lp.emailInput, email);
        mySendKeys(lp.passwordInput, password);
    }

    @And("User clicks the {string} submit button")
    public void userClicksTheSubmitButton(String arg0) {
        jsClick(lp.loginButton);
    }

    @Then("User should see error message {string}")
    public void userShouldSeeErrorMessage(String errorMessage) {
        String xpath = "//*[contains(normalize-space(), '" + errorMessage + "')]";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    @When("User clicks on {string} link")
    public void userClicksOnLink(String arg0) {
        scrollAndClick(lp.forgotPassword);
    }

    @And("The page URL should contain {string} ")
    public void thePageURLShouldContain(String expectedUrl) {
        wait.until(ExpectedConditions.urlContains(expectedUrl));

        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedUrl));
    }
}
