package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage {

    private WebDriver driver;

    private String url = "https://the-internet.herokuapp.com/dropdown";
    private By dropdown = By.id("dropdown");

    public DropdownPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }

    public void selecionarOpcao(String texto) {
        Select select = new Select(driver.findElement(dropdown));
        select.selectByVisibleText(texto);
    }

    public String obterOpcaoSelecionada() {
        Select select = new Select(driver.findElement(dropdown));
        return select.getFirstSelectedOption().getText();
    }
}
