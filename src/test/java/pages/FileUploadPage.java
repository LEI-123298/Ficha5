package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileUploadPage {

    private WebDriver driver;

    private String url = "https://the-internet.herokuapp.com/upload";
    private By uploadInput = By.id("file-upload");
    private By uploadButton = By.id("file-submit");
    private By uploadedFiles = By.id("uploaded-files");

    public FileUploadPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }

    public void escolherFicheiro(String caminho) {
        driver.findElement(uploadInput).sendKeys(caminho);
    }

    public void clicarUpload() {
        driver.findElement(uploadButton).click();
    }

    public String obterNomeFicheiro() {
        return driver.findElement(uploadedFiles).getText();
    }
}
