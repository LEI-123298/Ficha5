package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FileUploadPage;

import java.time.Duration;

public class FileUploadTest {

    private WebDriver driver;
    private FileUploadPage page;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        page = new FileUploadPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testarUploadFicheiro() {
        page.open();

        // Caminho absoluto para um ficheiro no teu PC
        String caminho = "C:/Users/Tiago Nunes/Pictures/Screenshots/Captura de ecrã 2023-10-24 164845.png";

        page.escolherFicheiro(caminho);
        page.clicarUpload();

        Assertions.assertEquals("Captura de ecrã 2023-10-24 164845.png", page.obterNomeFicheiro());
    }
}
