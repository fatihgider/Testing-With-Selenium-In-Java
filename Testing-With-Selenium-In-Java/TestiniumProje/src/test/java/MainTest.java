import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainTest {
    protected WebDriver driver;
    AnaSayfaElements anaSayfaElements;


    @Before
    public void setup(){
        driver = new ChromeDriver();
        anaSayfaElements = new AnaSayfaElements(driver);
        driver.manage().window().maximize();
        driver.get("https://www.network.com.tr/");
        String URL = driver.getCurrentUrl();
        Assert.assertEquals("URL Farklı",URL,"https://www.network.com.tr/");
    }


    @After
    public void tearDown(){
        System.out.println("Test Finished");
    }

}
