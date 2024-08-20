package praktikum;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import praktikum.page.objects.MainPage;
import praktikum.page.objects.OrderPage;

@RunWith(Parameterized.class)
public class OrderTest {
    private final By orderButton;
    private final String name;
    private final String family;
    private final String address;
    private final int metroIndex;
    private final String phone;
    private final String deliveryDate;
    private final int rentDuration;
    private final boolean colorBlack;
    private final boolean colorGrey;
    private final String comment;

    public OrderTest(By orderButton, String name, String family, String address, int metroIndex, String phone, String deliveryDate, int rentDuration, boolean colorBlack, boolean colorGrey, String comment){
        this.orderButton = orderButton;
        this.name = name;
        this.family = family;
        this.address = address;
        this.metroIndex = metroIndex;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.rentDuration = rentDuration;
        this.colorBlack = colorBlack;
        this.colorGrey = colorGrey;
        this.comment = comment;
    }
    @Rule
    public DriverFactory factory = new DriverFactory();
    @Before
    public void initDriver(){
        new MainPage(factory.getDriver())
                .open()
                .acceptCookies();
    }
    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getOrderParameters() {
        return new Object[][] {
                { OrderPage.topOrderButton,"Иван", "Иванов", "Москва", 1, "+79998765432", "toDay", 2, true, false, "random"},
                { OrderPage.bottomOrderButton,"Анастасия", "Воробей", "Питер, Смоленская ул. дом 10", 5, "89988765432", "toDay", 1, true, false, ""},
        };
    }
    @Test
    public void openMainPage() throws Exception{
        new OrderPage(factory.getDriver())
                .clickOrderButton(orderButton)
                .inputFormName(name)
                .inputFormFamily(family)
                .inputFormAdress(address)
                .inputFormMetro(metroIndex)
                .inputFormPhone(phone)
                .clickFormNextStep()
                .inputFormDate(deliveryDate)
                .inputFormDuration(rentDuration)
                .inputFormColorBlack(colorBlack)
                .inputFormColorGrey(colorGrey)
                .inputFormComment(comment)
                .clickFormSendOrder()
                .clickConfirmOrder()
                .checkOrderAccepted();
    }
}
