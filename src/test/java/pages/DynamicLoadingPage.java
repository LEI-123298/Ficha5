package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicLoadingPage {

    private WebDriver driver;

    private String url = "https://the-internet.herokuapp.com/dynamic_loading/1";
    private By startButton = By.cssSelector("#start button");
    private By loadingText = By.id("loading");
    private By finishText = By.id("finish");

    public DynamicLoadingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }

    public void clicarStart() {
        driver.findElement(startButton).click();
    }

    public String esperarElementoFinal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingText));
        return driver.findElement(finishText).getText();
    }
}
