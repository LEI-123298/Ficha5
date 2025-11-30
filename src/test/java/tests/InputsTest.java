package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.InputsPage;

import java.time.Duration;

public class InputsTest {

    private WebDriver driver;
    private InputsPage page;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        page = new InputsPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testarInputNumerico() {
        page.open();

        page.escrever("12345");
        Assertions.assertEquals("12345", page.obterValor());

        page.escrever("999");
        Assertions.assertEquals("999", page.obterValor());
    }
}
