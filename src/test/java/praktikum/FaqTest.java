package praktikum;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.page.objects.MainPage;

@RunWith(Parameterized.class)
public class FaqTest {
    private final int itemId;

    public FaqTest(int itemId){
        this.itemId = itemId;
    }

    @ClassRule
    public static DriverFactory factory = new DriverFactory();

    @BeforeClass
    public static void initDriver(){
        new MainPage(factory.getDriver())
                .open()
                .acceptCookies()
                .scrollDown();
    }

    @Parameterized.Parameters // добавили аннотацию
    public static Object[][] getTestId() {
        return new Object[][] {
                { 0},
                { 1},
                { 2},
                { 3},
                { 4},
                { 5},
                { 6},
                { 7},
        };
    }

    @Test
    public void openMainPage() throws Exception{
        new MainPage(factory.getDriver())
                .checkAnswerIsInvisible(itemId)
                .clickOnQuestion(itemId)
                .waitForAnswer(itemId);
    }
}
