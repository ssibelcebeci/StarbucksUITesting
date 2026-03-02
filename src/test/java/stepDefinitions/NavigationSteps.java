package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.CartPage;
import pages.Homepage;
import utils.BaseDriver;
import utils.ConfigReader;
import utils.ReusableMethods;

public class NavigationSteps extends ReusableMethods {

    Homepage hp = new Homepage();
    CartPage cp = new CartPage();

    @Given("User is on the Starbucks homepage")
    public void userIsOnTheStarbucksHomepage() {
        driver.get(ConfigReader.getProperty("url"));
    }

    @When("User clicks on {string} category link")
    public void userClicksOnCategoryLink(String categoryName) {
        WebElement element = hp.getCategoryElement(categoryName);
        Assert.assertTrue(element.isDisplayed(), categoryName + " is not visible");

        myClick(element);
    }

    @Then("The page URL should contain {string}")
    public void thePageURLShouldContain(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedUrl),
                "URL wrong! Expected: " + expectedUrl + " But the URL: " + actualUrl);

        driver.navigate().back();
    }

    @When("User types {string} in the search bar")
    public void userTypesInTheSearchBar(String productName) {
        hp.searchBox.clear();
        mySendKeys(hp.searchBox, productName);
    }

    @And("User clicks the search icon")
    public void userClicksTheSearchIcon() {
        myClick(hp.searchButton);
    }

    @Then("User should see results related to {string}")
    public void userShouldSeeResultsRelatedTo(String productName) {
        String currentUrl = BaseDriver.getDriver().getCurrentUrl().toLowerCase();
        Assert.assertTrue(currentUrl.contains(productName.toLowerCase()),
                "URL doesn't exist searched text! Current URL: " + currentUrl);

        verifyContainsText(hp.foundText, "Ürün");
    }

    @Then("The shopping cart icon should display {string} items")
    public void verifyCartCount(String expectedCount) {
        verifyContainsText(hp.totalProduct, expectedCount);
    }

    @When("User clicks on Sepetim button")
    public void user_clicks_on_button() {
        myClick(hp.cartButton);
    }

    @Then("User should see {string} text")
    public void user_should_see_text(String expectedText) {
        verifyContainsText(cp.emptyText, expectedText);
    }

    @And("User clicks Alışverişe Başla button")
    public void user_clicks_Alisverise_Basla_button() {
        myClick(cp.startShoppingButton);
    }

    @Then("User should be redirected to the homepage")
    public void verifyHomepageRedirect() {
        String expectedUrl = ConfigReader.getProperty("url");
        wait.until(ExpectedConditions.urlContains(expectedUrl));

        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedUrl));
    }
}
