package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseDriver;

public class Homepage {
    public Homepage() {
        PageFactory.initElements(BaseDriver.driver, this);
    }

    public WebElement getCategoryElement(String categoryName) {
        String xpath = "//a[contains(text(),'" + categoryName + "')]";
        return BaseDriver.getDriver().findElement(By.xpath(xpath));
    }

    @FindBy(id = "txtbxArama")
    public WebElement searchBox;

    @FindBy(id = "btnKelimeAra")
    public WebElement searchButton;

    @FindBy(xpath = "//li[@class='appliedFilter FiltrelemeUrunAdet']")
    public WebElement foundText;

    @FindBy(id = "spnTopSepetToplamUrun")
    public WebElement totalProduct;

    @FindBy(id = "divHeaderCart")
    public WebElement cartButton;

    @FindBy(id = "divMemberPanel")
    public WebElement loginOpt;
}
