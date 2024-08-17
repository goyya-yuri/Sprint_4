package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory extends ExternalResource {
    private WebDriver driver;

    public WebDriver getDriver(){
        return driver;
    }

    @Override
    protected void before() throws Throwable{
        initDriver();
    }
    @Override
    protected void after() {
        driver.quit();
    }

    public void initDriver(){
        if("firefox".equals(System.getProperty("browser"))){
            startFirefox();
        }else{
            startChrome();
        }
    }

    public void startFirefox(){
        WebDriverManager.firefoxdriver().setup();
        var options = new FirefoxOptions()
                .configureFromEnv();
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.IMPLICIT_WAIT));
    }

    public void startChrome(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.IMPLICIT_WAIT));
    }
}
