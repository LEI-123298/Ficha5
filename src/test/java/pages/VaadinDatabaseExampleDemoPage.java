package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;

public class VaadinDatabaseExampleDemoPage {

    // Configuração do Selenide
    static {
        com.codeborne.selenide.Configuration.timeout = 15000;
        com.codeborne.selenide.Configuration.browser = "chrome";
        com.codeborne.selenide.Configuration.browserSize = "1920x1080";
        com.codeborne.selenide.Configuration.headless = false;
    }

    // Elementos da página
    private SelenideElement pageHeader = $("h3");

    // Elementos do grid
    private ElementsCollection gridCells = $$("[role='gridcell'], vaadin-grid-cell-content, td");
    private ElementsCollection allLinks = $$("a");

    public VaadinDatabaseExampleDemoPage openPage() {
        open("https://vaadin-database-example.demo.vaadin.com");
        sleep(3000);
        pageHeader.shouldBe(Condition.visible);
        return this;
    }

    public String getPageTitle() {
        return title();
    }

    public String getHeaderText() {
        return pageHeader.getText();
    }

    public boolean isTextPresent(String text) {
        return $(byText(text)).exists();
    }

    public int countTextOccurrences(String text) {
        // Busca case-insensitive
        return $$("*").filter(Condition.matchText("(?i).*" + text + ".*")).size();
    }

    // Método corrigido para verificar links IMDB
    public boolean hasImdbLinks() {
        // Verifica se há links com texto contendo "IMBD" (note o typo no site)
        for (SelenideElement link : allLinks) {
            String linkText = link.getText().toLowerCase();
            String href = link.getAttribute("href") != null ? link.getAttribute("href").toLowerCase() : "";

            if (linkText.contains("imbd") || linkText.contains("imdb") ||
                    href.contains("imdb.com") || href.contains("imbd")) {
                return true;
            }
        }
        return false;
    }

    // Método específico para verificar os links "Click to IMBD site"
    public boolean hasClickToImbdLinks() {
        return $$("a").filter(Condition.matchText("(?i)Click to IMBD")).size() > 0;
    }

    // Método para contar quantos links IMDB existem
    public int countImdbLinks() {
        int count = 0;
        for (SelenideElement link : allLinks) {
            String linkText = link.getText().toLowerCase();
            String href = link.getAttribute("href") != null ? link.getAttribute("href").toLowerCase() : "";

            if (linkText.contains("imbd") || linkText.contains("imdb") ||
                    href.contains("imdb.com") || href.contains("imbd")) {
                count++;
            }
        }
        return count;
    }

    public void printPageInfo() {
        System.out.println("=== INFORMAÇÕES DA PÁGINA ===");
        System.out.println("URL: " + webdriver().driver().url());
        System.out.println("Título: " + getPageTitle());
        System.out.println("Cabeçalho: " + getHeaderText());
        System.out.println("Células do grid: " + gridCells.size());
        System.out.println("Links totais: " + allLinks.size());
        System.out.println("Links IMDB encontrados: " + countImdbLinks());

        // Lista todos os links para debug
        System.out.println("\nTodos os links na página:");
        allLinks.forEach(link -> {
            String text = link.getText().trim();
            String href = link.getAttribute("href");
            if (!text.isEmpty()) {
                System.out.println("  Texto: '" + text + "'");
                System.out.println("  Href: " + (href != null ? href : "(sem href)"));
                System.out.println();
            }
        });

        // Verifica textos específicos
        System.out.println("\nTextos encontrados:");
        String[] textosParaVerificar = {"Law Abiding Citizen", "Knives Out", "The Last Jedi",
                "Rian Johnson", "2009", "2019", "2017", "Click to IMBD"};
        for (String texto : textosParaVerificar) {
            if (isTextPresent(texto)) {
                System.out.println("✓ " + texto);
            } else if (countTextOccurrences(texto) > 0) {
                System.out.println("✓ " + texto + " (encontrado via match text)");
            }
        }
    }

    public void clickLinkWithText(String text) {
        $(byText(text)).click();
        sleep(2000);
    }

    public int getGridCellCount() {
        return gridCells.size();
    }

    public boolean isPageLoaded() {
        return pageHeader.exists() && getGridCellCount() > 0;
    }
}