package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NotificationMessagesPage {

    private WebDriver driver;

    private String url = "https://the-internet.herokuapp.com/notification_message_rendered";
    private By triggerButton = By.linkText("Click here");
    private By message = By.id("flash");

    public NotificationMessagesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }

    public void clicarTrigger() {
        driver.findElement(triggerButton).click();
    }

    public String obterMensagem() {
        return driver.findElement(message).getText().trim();
    }
}
