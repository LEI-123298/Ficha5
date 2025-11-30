package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CheckboxesPage;

import java.time.Duration;

public class CheckboxesTest {

    private WebDriver driver;
    private CheckboxesPage page;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        page = new CheckboxesPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testarCheckboxes() {
        page.open();

        page.marcarCheckbox(1);
        Assertions.assertTrue(page.isCheckboxMarcado(1));

        page.desmarcarCheckbox(1);
        Assertions.assertFalse(page.isCheckboxMarcado(1));

        page.marcarCheckbox(2);
        Assertions.assertTrue(page.isCheckboxMarcado(2));
    }
}
