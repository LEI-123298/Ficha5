package iscteiul.ista.engsoftwareficha5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");

        acceptCookies();
        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void search() {
        mainPage.searchButton.click();

        WebElement searchField = driver.findElement(By.cssSelector("[data-test='input__inner']"));
        searchField.sendKeys("Selenium");
        searchField.sendKeys(Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        searchField = driver.findElement(By.cssSelector("[data-test='input__inner']"));
        searchField.sendKeys("Selenium");
        WebElement searchPageField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[data-test='input__inner']")
                )
        );
        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.click();

        WebElement menuPopup = driver.findElement(By.cssSelector("div[data-test='site-header-overlay']"));
        assertTrue(menuPopup.isDisplayed());
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();

        WebElement productsList = driver.findElement(By.id("products-page"));
        assertTrue(productsList.isDisplayed());
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }

    public void acceptCookies(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement acceptCookies = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.cssSelector("button[class*='ch2-allow-all-btn']")
                    )
            );
            acceptCookies.click();
        } catch (Exception e) {
            // Ignore
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Ignore
        }
    }
}
