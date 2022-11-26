import com.opencsv.CSVReader;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class AnaSayfaElements {

    private final WebDriver driver;
    private final By inpElement = By.id("search");
    String CSV_PATH = "src/main/resources/user.csv";
    private CSVReader csvReader;
    String[] csvCell;
    public AnaSayfaElements(WebDriver driver){
        this.driver = driver;
    }
    public void setUrun(String name){

        WebElement input = driver.findElement(inpElement);
        input.click();
        input.sendKeys(name);

    }

    public void cerezKabulEt(){

        WebElement cerezBtn = driver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]"));
        cerezBtn.click();

    }

    public void aramaYap(){

        WebElement textbox = driver.findElement(By.id("search"));
        textbox.sendKeys(Keys.ENTER);

    }

    public void sayfaDegistir(){

        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException ie){
        }

        WebElement link = driver.findElement(By.xpath("//*[@id=\"pagedListContainer\"]/div[2]/div[2]/button"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",link);

        js.executeScript("window.scrollBy(0,-200)", "");

        link.click();

        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException ie){
        }

        String URL = driver.getCurrentUrl();
        Assert.assertEquals("URL Farklı",URL,"https://www.network.com.tr/search?searchKey=Ceket&page=2");
        driver.get(URL);
    }

    public String sayfaFiyat;
    public String sayfaBeden;

    public void indirimliUrunBul(){

        WebElement indirimliUrun = driver.findElement(By.className("-old"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",indirimliUrun);
        js.executeScript("window.scrollBy(0,-600)", "");

        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException ie){
        }

        Actions action = new Actions(driver);
        action.moveToElement(indirimliUrun).perform();

        WebElement beden = driver.findElement(By.xpath("//*[@id=\"product-112624\"]/div/div[1]/div/div/div/div[6]/label"));
        sayfaBeden = beden.getText();
        beden.click();

        WebElement fiyat = driver.findElement(By.xpath("//*[@id=\"product-112624\"]/div/div[2]/div/div[2]/div/span[2]"));
        sayfaFiyat = fiyat.getText();

        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException ie){
        }

    }

    public void sepeteGit(){

        WebElement sepeteGitBtn=driver.findElement(By.xpath("//*[@id=\"header__desktopBasket\"]/div/div[3]/a"));
        sepeteGitBtn.click();

        String URL = driver.getCurrentUrl();
        driver.get(URL);
        Assert.assertEquals("URL Farklı",URL,"https://www.network.com.tr/cart");

        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException ie){
        }

        WebElement sepetBedenElem = driver.findElement(By.className("cartItem__attrValue"));
        String sepetBeden = sepetBedenElem.getText();

        WebElement sepetFiyatElem = driver.findElement(By.className("cartItem__price"));
        String sepetFiyat = sepetFiyatElem.getText();


        Assert.assertEquals("Sepet Bedeni Eşit Değil",sepetBeden,sayfaBeden);
        Assert.assertEquals("Sepet Fiyatı Eşit Değil",sepetFiyat,sayfaFiyat);

        WebElement IndirimliFiyatElem = driver.findElement(By.className("-sale"));
        int indirimliFiyat = Integer.parseInt(IndirimliFiyatElem.getText().replaceAll("[A-Z/[.,\\/#!$%\\^&\\*;:{}=\\-_`~()]/g ]",""));

        WebElement eskiFiyatElem = driver.findElement(By.className("-old"));
        int eskiFiyat = Integer.parseInt(eskiFiyatElem.getText().replaceAll("[A-Z/[.,\\/#!$%\\^&\\*;:{}=\\-_`~()]/g ]",""));

        Assert.assertTrue("Fiyat İndirimli Değil", indirimliFiyat < eskiFiyat);

        WebElement devamEtBtnElem = driver.findElement(By.xpath("//*[@id=\"cop-app\"]/div/div[1]/div[1]/div[2]/div/div[2]/button"));
        devamEtBtnElem.click();
    }

    public void girisYap() throws IOException, CsvValidationException{
        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException ie){
        }

        String URL = driver.getCurrentUrl();
        driver.get(URL);
        Assert.assertEquals("URL Farklı",URL,"https://www.network.com.tr/customer/login?returnUrl=%2Fcheckout&isGuestCheckOut=True#delivery");

        csvReader = new CSVReader(new FileReader(CSV_PATH));

        while ((csvCell = csvReader.readNext()) != null) {
            String Mail = csvCell[0];
            String Password = csvCell[1];
            WebElement mailInputElem = driver.findElement(By.xpath("//*[@id=\"n-input-email\"]"));
            mailInputElem.clear();
            mailInputElem.sendKeys(Mail);
            WebElement passwordInputElem = driver.findElement(By.xpath("//*[@id=\"n-input-password\"]"));
            passwordInputElem.clear();
            passwordInputElem.sendKeys(Password);

            WebElement girisYapBtnElem = driver.findElement(By.xpath("//*[@id=\"login\"]/button"));
            girisYapBtnElem.click();
        }

    }

    public void anaSayfayaDon(){
        WebElement logoElem = driver.findElement(By.xpath("//*[@id=\"cop-app\"]/div/div[1]/header/div/div/div[2]/a"));
        logoElem.click();

        String URL = driver.getCurrentUrl();
        driver.get(URL);
        Assert.assertEquals("URL Farklı",URL,"https://www.network.com.tr/");
    }

    public void sepetKontrol(){
        WebElement cantaLogoElem = driver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[3]/div[3]/button"));
        cantaLogoElem.click();

        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException ie){
        }

        WebElement sepeteGitBtnElem = driver.findElement(By.xpath("//*[@id=\"header__desktopBasket\"]/div/div[3]/a"));
        sepeteGitBtnElem.click();

        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException ie){
        }

        WebElement silBtnElem = driver.findElement(By.className("-remove"));
        silBtnElem.click();

        WebElement onayBtnElem = driver.findElement(By.xpath("//*[@id=\"cop-app\"]/div/div[1]/div[1]/div[1]/div[2]/section/div[4]/div[2]/div[3]/button[1]"));
        onayBtnElem.click();

        WebElement logoElem = driver.findElement(By.xpath("//*[@id=\"cop-app\"]/div/div[1]/header/div/div/div[2]/a"));
        logoElem.click();

        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException ie){
        }

        WebElement cantaLogoElem2 = driver.findElement(By.xpath("/html/body/div[2]/header/div/div/div[3]/div[3]/button"));
        cantaLogoElem2.click();
    }

}
