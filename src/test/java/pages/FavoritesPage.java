package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseDriver;

public class FavoritesPage {
    public FavoritesPage() {
        PageFactory.initElements(BaseDriver.driver, this);
    }

    @FindBy(xpath = "//div[@class='favoriBosP-1 ng-binding']")
    public WebElement emptyFavoritesText;

    @FindBy(xpath = "//a[contains(text(),'Alışverişe Başla')]")
    public WebElement startShoppingButton;

    @FindBy(xpath = "//div[contains(@class,'trTutucu')]//i[contains(@class,'fa-arrow-down')]")
    public WebElement myFavsArrowDown;

    @FindBy(xpath = "//a[contains(@class,'urunBaslik')]")
    public WebElement lastAddedFavoriteName;

    @FindBy(xpath = "//a[contains(@ng-click,'DeletFavoriGroup')]")
    public WebElement deleteGroupIcon;

    @FindBy(xpath = "//button[contains(text(),'Evet')] | //a[contains(text(),'Evet')]")
    public WebElement confirmDeleteButton;

    @FindBy(xpath = "//p[text()='İşleminiz başarıyla gerçekleşmiştir.']")
    public WebElement deleteSuccessMessage;

    @FindBy(css = "button.confirm")
    public WebElement okButton;
}
