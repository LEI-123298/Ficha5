package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DropdownPage;

import java.time.Duration;

public class DropdownTest {

    private WebDriver driver;
    private DropdownPage page;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        page = new DropdownPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testarSelecaoDropdown() {
        page.open();

        page.selecionarOpcao("Option 1");
        Assertions.assertEquals("Option 1", page.obterOpcaoSelecionada());

        page.selecionarOpcao("Option 2");
        Assertions.assertEquals("Option 2", page.obterOpcaoSelecionada());
    }
}
