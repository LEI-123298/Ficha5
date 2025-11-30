package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DynamicLoadingPage;

import java.time.Duration;

public class DynamicLoadingTest {

    private WebDriver driver;
    private DynamicLoadingPage page;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        page = new DynamicLoadingPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testarDynamicLoading() {
        page.open();

        page.clicarStart();
        String texto = page.esperarElementoFinal();

        Assertions.assertEquals("Hello World!", texto);
    }
}
