package praktikum.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import praktikum.Config;

import static org.junit.Assert.assertEquals;

public class MainPage {
    private final WebDriver driver;

    By answerPanel = By.className("accordion__panel");
    By questionButton = By.className("accordion__button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage open() {
        driver.get(Config.BASE_URL);
        return this;
    }

    public MainPage acceptCookies() {
        driver.findElement(By.className("App_CookieButton__3cvqF")).click();
        return this;
    }

    public WebElement getFaqElementById(int itemId) {
        return driver.findElement(By.xpath("//*[@id='accordion__panel-" + itemId + "']/.."));
    }

    public String getFaqQuestionText(int itemId) {
        return getFaqElementById(itemId).findElement(questionButton).getText();
    }

    public void scrollDown() {
        new Actions(driver)
                .scrollByAmount(0, 1500)
                .perform();
    }

    public MainPage checkAnswerIsInvisible(int itemId) {
        assertEquals("Ответ на вопрос " + getFaqQuestionText(itemId) + ", скрыт.", false, getFaqElementById(itemId).findElement(answerPanel).isDisplayed());
        return this;
    }

    public MainPage clickOnQuestion(int itemId) {
        getFaqElementById(itemId).click();
        return this;
    }

    public MainPage waitForAnswer(int itemId) {
        assertEquals("При нажатии на вопрос " + getFaqQuestionText(itemId) + ", раскрывается ответ", true, getFaqElementById(itemId).findElement(answerPanel).isDisplayed());
        return this;
    }
}