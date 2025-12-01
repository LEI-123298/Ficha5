package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InputsPage {

    private WebDriver driver;
    private String url = "https://the-internet.herokuapp.com/inputs";
    private By inputField = By.tagName("input");

    public InputsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }

    public void escrever(String texto) {
        driver.findElement(inputField).clear();
        driver.findElement(inputField).sendKeys(texto);
    }

    public String obterValor() {
        return driver.findElement(inputField).getAttribute("value");
    }
}
