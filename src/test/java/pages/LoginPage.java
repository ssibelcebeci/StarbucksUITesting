package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseDriver;

public class LoginPage {
    public LoginPage() {
        PageFactory.initElements(BaseDriver.driver,this);
    }

    @FindBy(id = "txtUyeGirisEmail")
    public WebElement emailInput;

    @FindBy(id = "txtUyeGirisPassword")
    public WebElement passwordInput;

    @FindBy(className = "newUserLoginBtn")
    public WebElement loginButton;

    @FindBy(className = "ng-binding")
    public WebElement welcomeText;

    @FindBy(className = "userPassBtn")
    public WebElement forgotPassword;
}
