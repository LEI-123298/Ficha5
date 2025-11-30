package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CheckboxesPage {

    private WebDriver driver;

    private String url = "https://the-internet.herokuapp.com/checkboxes";
    private By checkboxes = By.cssSelector("#checkboxes input[type='checkbox']");

    public CheckboxesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }

    private WebElement getCheckbox(int index) {
        return driver.findElements(checkboxes).get(index - 1);
    }

    public void marcarCheckbox(int index) {
        WebElement cb = getCheckbox(index);
        if (!cb.isSelected()) cb.click();
    }

    public void desmarcarCheckbox(int index) {
        WebElement cb = getCheckbox(index);
        if (cb.isSelected()) cb.click();
    }

    public boolean isCheckboxMarcado(int index) {
        return getCheckbox(index).isSelected();
    }
}