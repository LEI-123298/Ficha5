package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.VaadinDatabaseExampleDemoPage;

import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selenide.*;

@Epic("Test Suite: Database")
@Feature("Informação sobre filme")
@DisplayName("Testes de Aceitação - Banco de Dados de Filmes Vaadin")
public class VaadinDatabaseExampleDemoTest {

    private VaadinDatabaseExampleDemoPage page;

    @BeforeEach
    @Step("Abrir a página do banco de dados de filmes")
    public void setUp() {
        page = new VaadinDatabaseExampleDemoPage();
        page.openPage();
        page.printPageInfo();
    }

    @AfterEach
    @Step("Fechar navegador")
    public void tearDown() {
        closeWebDriver();
    }

    @Test
    @DisplayName("TC001: Verificar carregamento da página")
    @Description("Testa se a página carrega corretamente")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Carregamento da página")
    public void testPageLoads() {
        assertAll("Verificações iniciais da página",
                () -> assertTrue(page.isPageLoaded(),
                        "A página deveria estar carregada"),
                () -> assertTrue(page.getGridCellCount() > 0,
                        "Deveria haver células de dados na página")
        );
    }

    @Test
    @DisplayName("TC002: Verificar presença dos filmes específicos")
    @Description("Testa se os filmes do exemplo estão presentes")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Conteúdo da base de dados")
    public void testSpecificMoviesPresent() {
        assertAll("Verificação de filmes específicos",
                () -> assertTrue(page.isTextPresent("Law Abiding Citizen"),
                        "O filme 'Law Abiding Citizen' deveria estar presente"),
                () -> assertTrue(page.isTextPresent("Knives Out"),
                        "O filme 'Knives Out' deveria estar presente"),
                () -> assertTrue(page.isTextPresent("The Last Jedi"),
                        "O filme 'The Last Jedi' deveria estar presente")
        );
    }

    @Test
    @DisplayName("TC003: Verificar informações dos diretores")
    @Description("Testa se os diretores estão corretamente listados")
    @Severity(SeverityLevel.NORMAL)
    @Story("Informações de direção")
    public void testDirectorsInformation() {
        assertAll("Verificação de diretores",
                () -> assertTrue(page.isTextPresent("Rian Johnson"),
                        "O diretor 'Rian Johnson' deveria estar presente"),
                () -> assertTrue(page.isTextPresent("F. Gary Gray") ||
                                page.isTextPresent("F. Gardy Gray"),
                        "O diretor de 'Law Abiding Citizen' deveria estar presente")
        );
    }

    @Test
    @DisplayName("TC004: Verificar anos de lançamento")
    @Description("Testa se os anos de lançamento estão corretos")
    @Severity(SeverityLevel.NORMAL)
    @Story("Datas de lançamento")
    public void testReleaseYears() {
        assertAll("Verificação de anos de lançamento",
                () -> assertTrue(page.isTextPresent("2009"),
                        "O ano 2009 deveria estar presente (Law Abiding Citizen)"),
                () -> assertTrue(page.isTextPresent("2019"),
                        "O ano 2019 deveria estar presente (Knives Out)"),
                () -> assertTrue(page.isTextPresent("2017"),
                        "O ano 2017 deveria estar presente (The Last Jedi)")
        );
    }

    @Test
    @DisplayName("TC005: Verificar links IMDB/IMBD")
    @Description("Testa se existem links para o IMDB (ou IMBD com typo)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Links externos")
    public void testImdbLinks() {
        // Verifica se há pelo menos algum link relacionado a IMDB/IMBD
        assertTrue(page.hasImdbLinks() || page.hasClickToImbdLinks(),
                "Deveria haver links para o IMDB/IMBD na página");

        // Verifica também se há pelo menos 3 links (um para cada filme)
        int imdbLinkCount = page.countImdbLinks();
        assertTrue(imdbLinkCount >= 3,
                "Deveria haver pelo menos 3 links IMDB/IMBD (um por filme). Encontrados: " + imdbLinkCount);
    }

    @Test
    @DisplayName("TC006: Verificar estrutura da tabela")
    @Description("Testa se a tabela tem dados estruturados")
    @Severity(SeverityLevel.MINOR)
    @Story("Estrutura da interface")
    public void testTableStructure() {
        // Verifica se há um número razoável de células (pelo menos 12 para 3 filmes × 4 colunas)
        int cellCount = page.getGridCellCount();
        assertTrue(cellCount >= 12,
                "Deveria haver pelo menos 12 células (3 filmes × 4 colunas). Encontradas: " + cellCount);

        // Verifica se há textos de cabeçalho
        assertTrue(page.countTextOccurrences("(?i)title.*") > 0 ||
                        page.countTextOccurrences("(?i)name.*") > 0,
                "Deveria haver cabeçalhos de coluna");
    }

    @Test
    @DisplayName("TC007: Verificar múltiplas ocorrências do mesmo diretor")
    @Description("Testa se Rian Johnson aparece mais de uma vez")
    @Severity(SeverityLevel.MINOR)
    @Story("Análise de dados")
    public void testMultipleDirectorOccurrences() {
        int rianJohnsonCount = page.countTextOccurrences("Rian Johnson");
        assertTrue(rianJohnsonCount >= 2,
                "Rian Johnson deveria aparecer pelo menos 2 vezes. Encontrado: " + rianJohnsonCount);
    }
}