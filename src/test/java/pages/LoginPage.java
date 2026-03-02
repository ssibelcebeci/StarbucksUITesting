package pages;

import io.cucumber.java.en.Given;
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

    @FindBy(className = "headerHesabim")
    public WebElement myAccountOpt;

    @FindBy(className = "ng-binding")
    public WebElement welcomeText;

    @FindBy(xpath = "//div[contains(@class,'ticimaxDialogTxt')]//p")
    public WebElement generalError;

    @FindBy(xpath = "//label[@class='mailRequired alert alert-danger displayNone']")
    public WebElement emailError;

    @FindBy(xpath = "//label[@class='isRequired alert alert-danger displayNone']")
    public WebElement passwordError;

    @FindBy(className = "userPassBtn")
    public WebElement forgotPassword;
}
