package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.NotificationMessagesPage;

import java.time.Duration;

public class NotificationMessagesTest {

    private WebDriver driver;
    private NotificationMessagesPage page;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        page = new NotificationMessagesPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testarNotificacoes() {
        page.open();

        page.clicarTrigger();
        String msg = page.obterMensagem();

        Assertions.assertTrue(
                msg.contains("Action successful") ||
                        msg.contains("Action unsuccesful") ||
                        msg.contains("Action unsuccessful")
        );
    }
}
