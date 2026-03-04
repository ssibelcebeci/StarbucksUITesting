package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.FavoritesPage;
import pages.Homepage;
import pages.ProductPage;
import utils.BaseDriver;
import utils.ConfigReader;
import utils.ReusableMethods;

import java.util.List;
import java.util.Random;

public class FavoriteSteps extends ReusableMethods {

    Homepage hp = new Homepage();
    FavoritesPage fp = new FavoritesPage();
    ProductPage pp = new ProductPage();
    String randomProductName;

    @Given("User is logged in and on the dashboard")
    public void userIsLoggedInAndOnTheDashboard() {
        wait.until(ExpectedConditions.visibilityOf(hp.myAccountOpt));
        Assert.assertTrue(hp.myAccountOpt.isDisplayed());
    }

    @When("User clicks on the {string} button in the navigation menu")
    public void userClicksOnTheButtonInTheNavigationMenu(String arg0) {
        myClick(hp.favoritesButton);
    }

    @Then("User should see the message {string}")
    public void userShouldSeeTheMessage(String arg0) {
        verifyContainsText(fp.emptyFavoritesText, "Favori İlk Ürününü Ekle");
    }

    @And("User clicks on the {string} button")
    public void userClicksOnTheButton(String buttonName) {
        if (buttonName.equalsIgnoreCase("Alışverişe Başla")) {
            scrollAndClick(fp.startShoppingButton);
        } else if (buttonName.equalsIgnoreCase("Favorilerim")) {
            scrollAndClick(hp.favoritesButton);
        }
    }

    @Then("User should be redirected to the home page")
    public void userShouldBeRedirectedToTheHomePage() {
        wait.until(ExpectedConditions.visibilityOf(hp.myAccountOpt));
        Assert.assertTrue(hp.myAccountOpt.isDisplayed());
    }

    @Given("User is on the home page")
    public void userIsOnTheHomePage() {
        String expectedUrl = ConfigReader.getProperty("url");
        wait.until(ExpectedConditions.urlContains(expectedUrl));

        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedUrl));
    }

    @When("User selects a random category from the navigation menu")
    public void userSelectsARandomCategoryFromTheNavigationMenu() {
        List<WebElement> categories = hp.navMenuElements;

        int randomIndex = new Random().nextInt(categories.size());
        myClick(categories.get(randomIndex));
    }

    @And("User clicks on a random product from the list")
    public void userClicksOnARandomProductFromTheList() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".productItem .productName a")));

        int size = pp.productList.size();
        int randomIndex = new Random().nextInt(size);
        WebElement selectedProduct = pp.productList.get(randomIndex);

        randomProductName = selectedProduct.getAttribute("title");

        ((JavascriptExecutor) BaseDriver.getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", selectedProduct);

        threadWait(1);

        ((JavascriptExecutor) BaseDriver.getDriver()).executeScript("arguments[0].click();", selectedProduct);
    }

    @And("User clicks on the {string} icon")
    public void userClicksOnTheIcon(String arg0) {
        jsClick(pp.addToFavoritesButton);
    }

    @And("User enters {string} into the group name field and clicks save")
    public void userEntersIntoTheGroupNameFieldAndClicksSave(String arg0) {
        mySendKeys(pp.newFavoriteGroupInput, "MyFavs");
        myClick(pp.saveFavoriteGroup);
    }

    @Then("User should see {string} notification")
    public void userShouldSeeNotification(String arg0) {
        verifyContainsText(pp.favoriteSuccessMessage, "Ürün favorilerinize eklenmiştir.");
        myClick(pp.notificationClose);
    }

    @And("User expands the {string} group using the down arrow")
    public void userExpandsTheGroupUsingTheDownArrow(String arg0) {
        wait.until(ExpectedConditions.urlContains("Favorilerim"));
        jsClick(fp.myFavsArrowDown);
    }

    @Then("The product name in the favorites list should match the added product name")
    public void theProductNameInTheFavoritesListShouldMatchTheAddedProductName() {
        wait.until(ExpectedConditions.visibilityOf(fp.lastAddedFavoriteName));

        String actualName = fp.lastAddedFavoriteName.getText().replaceAll("\\s+", " ").trim();
        String expectedName = randomProductName.replaceAll("\\s+", " ").trim();

        System.out.println("Expected: " + expectedName);
        System.out.println("Actual: " + actualName);

        Assert.assertTrue(actualName.toLowerCase().contains(expectedName.toLowerCase()));
    }

    @And("User clicks on the delete icon for the favorite group")
    public void userClicksOnTheDeleteIconForTheFavoriteGroup() {
        wait.until(ExpectedConditions.visibilityOf(fp.deleteGroupIcon));
        jsClick(fp.deleteGroupIcon);
    }

    @And("User clicks on the {string} button in the confirmation popup")
    public void userClicksOnTheButtonInTheConfirmationPopup(String arg0) {
        threadWait(1);
        myClick(fp.confirmDeleteButton);
        myClick(fp.okButton);
    }

    @Then("User should see the success message {string}")
    public void userShouldSeeTheSuccessMessage(String expectedMessage) {
        wait.until(ExpectedConditions.visibilityOf(fp.deleteSuccessMessage));
        verifyContainsText(fp.deleteSuccessMessage, expectedMessage);
    }
}
