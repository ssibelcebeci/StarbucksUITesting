package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseDriver;

import java.util.List;

public class ProductPage {
    public ProductPage() {
        PageFactory.initElements(BaseDriver.driver, this);
    }

    @FindBy(css = ".productItem .productName a")
    public List<WebElement> productList;

    @FindBy(id = "aFavoriEkleBtn")
    public WebElement addToFavoritesButton;

    @FindBy(id = "txtFavoriGrup")
    public WebElement newFavoriteGroupInput;

    @FindBy(id = "btnFavoriGrupKaydet")
    public WebElement saveFavoriteGroup;

    @FindBy(xpath = "//div[contains(@class,'ticimaxDialog-info')]//p")
    public WebElement favoriteSuccessMessage;

    @FindBy(xpath = "//a[@class='tici-noty-notification-close-btn']")
    public WebElement notificationClose;
}
