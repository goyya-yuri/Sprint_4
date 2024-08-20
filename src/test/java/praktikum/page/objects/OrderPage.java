package praktikum.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.Config;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class OrderPage {
    private final WebDriver driver;

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }
    private static final By topOrderButton = By.xpath("//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");
    private static final By bottomOrderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    By firstNameInput = By.xpath("//input[@placeholder='* Имя']");
    By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    By metroInput = By.xpath("//input[@placeholder='* Станция метро']");
    By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    By nextButton = By.xpath("//div[@class='Order_NextButton__1_rCA']/button");

    By dateDelivery = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    By rentalPeriod = By.xpath("//div[@class='Dropdown-control']");
    By blackColor = By.xpath("//label[@for='black']/input");
    By grayColor = By.xpath("//label[@for='grey']/input");
    By commentEdit = By.xpath("//input[@placeholder='Комментарий для курьера']");

    By submitButton = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    By confirmButton = By.xpath("//div[@class='Order_Modal__YZ-d3']//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    By orderInfo = By.className("Order_Text__2broi");


    public OrderPage clickOrderButton(String orderButton) {
        switch (orderButton){
            case "top":
                driver.findElement(topOrderButton).click();
                break;
            case "bottom":
                driver.findElement(bottomOrderButton).click();
                break;
            default:
                driver.findElement(topOrderButton).click();
                break;
        }
        return this;
    }

    public OrderPage inputFormName(String name) {
        driver.findElement(firstNameInput).sendKeys(name);
        return this;
    }

    public OrderPage inputFormFamily(String family) {
        driver.findElement(lastNameInput).sendKeys(family);
        return this;
    }

    public OrderPage inputFormAdress(String address) {
        driver.findElement(addressInput).sendKeys(address);
        return this;
    }

    public OrderPage inputFormMetro(int metroIndex) {
        driver.findElement(metroInput).click();
        By selectableElement = By.xpath("//li[@role='menuitem'][@data-index='"+metroIndex+"']");
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT))
                .until(ExpectedConditions.presenceOfElementLocated(selectableElement));
        driver.findElement(selectableElement).click();
        return this;
    }

    public OrderPage inputFormPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
        return this;
    }

    public OrderPage clickFormNextStep() {
        driver.findElement(nextButton).click();
        return this;
    }

    public OrderPage inputFormDate(String deliveryDate) {
        driver.findElement(dateDelivery).click();
        By selectableElement = By.className("react-datepicker__day--keyboard-selected");
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT))
                .until(ExpectedConditions.presenceOfElementLocated(selectableElement));
        driver.findElement(selectableElement).click();
        return this;
    }

    public OrderPage inputFormDuration(int rentDuration) {
        driver.findElement(rentalPeriod).click();
        By selectableElement = By.xpath(".//div[@class='Dropdown-option']["+rentDuration+"]");
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT))
                .until(ExpectedConditions.presenceOfElementLocated(selectableElement));
        driver.findElement(selectableElement).click();
        return this;
    }

    public OrderPage inputFormColorBlack(boolean colorBlack) {
        if (colorBlack){ driver.findElement(blackColor).click(); }
        return this;
    }

    public OrderPage inputFormColorGrey(boolean colorGrey) {
        if (colorGrey){ driver.findElement(grayColor).click(); }
        return this;
    }

    public OrderPage inputFormComment(String comment) {
        driver.findElement(commentEdit).sendKeys(comment);
        return this;
    }

    public OrderPage clickFormSendOrder() {
        driver.findElement(submitButton).click();
        return this;
    }

    public OrderPage clickConfirmOrder() {
        driver.findElement(confirmButton).click();
        return this;
    }

    public OrderPage checkOrderAccepted() {
        assertTrue("Заказ успешно оформлен", !(" ".equals(driver.findElement(orderInfo).getText())));
        return this;
    }
}
