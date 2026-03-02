package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.File;
import java.time.Duration;
import java.util.List;

public class ReusableMethods extends BaseDriver {

    public static void threadWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void myClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void mySendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public void verifyContainsText(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        Assert.assertTrue(element.getText().toLowerCase().contains(text.toLowerCase()));
    }

    public void jsClick(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].scrollIntoView({block:'center', inline:'center'});",
                element
        );
        js.executeScript("arguments[0].click();", element);
    }

    public void scrollAndClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );
        element.click();
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );
    }

    public void waitUntilClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementsToBeVisible(List<WebElement> elements) {
        if (elements == null || elements.isEmpty()) {
            return;
        }
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement acceptBtn = driver.findElement(
                    By.id("onetrust-accept-btn-handler")
            );
            if (acceptBtn.isDisplayed()) {
                acceptBtn.click();
            }
        } catch (Exception ignored) {
        }
    }

    public void hoverOver(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static boolean isFileDownloaded(String extension) {
        String downloadPath = System.getProperty("user.home") + "/Downloads";
        File folder = new File(downloadPath);

        File[] files = folder.listFiles((dir, name) ->
                name.toLowerCase().endsWith("." + extension.toLowerCase())
        );

        return files != null && files.length > 0;
    }

    public void waitUntilAttributeToBe(WebElement element, String attribute, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }
}
