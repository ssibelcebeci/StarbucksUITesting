package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseDriver;

public class CartPage {
    public CartPage() {
        PageFactory.initElements(BaseDriver.driver, this);
    }

    @FindBy(className = "empty-cart--title")
    public WebElement emptyText;

    @FindBy(className = "v-btn__content")
    public WebElement startShoppingButton;
}
