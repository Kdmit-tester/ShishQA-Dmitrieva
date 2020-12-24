import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class firstattempt {
    public static WebDriver driver;

    public static String screanshotpath = "C:\\KDmit\\image.jpg";

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?" +
                "orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc" +
                "0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c" +
                "3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknI" +
                "FpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCc" +
                "gVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f2" +
                "76dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void openPage() {
        String titleActual = driver.getTitle();
        String titleExpected = "Unlimint Payment Page";
        Assert.assertEquals("Title not matching", titleExpected, titleActual);
    }

    @Test
    public void screanShoot() throws IOException, InterruptedException {
        WebElement cardNumber = driver.findElement(By.id("input-card-number"));
        cardNumber.sendKeys("4000000000000002");
        WebElement cardHolder = driver.findElement(By.id("input-card-holder"));
        cardHolder.sendKeys("Karina Dmitrieva");
        WebElement selectMonth = driver.findElement(By.id("card-expires-month"));
        Select selectM = new Select(selectMonth);
        selectM.selectByVisibleText("06");
        WebElement selectYear = driver.findElement(By.id("card-expires-year"));
        Select selectY = new Select(selectYear);
        selectY.selectByVisibleText("2038");
        WebElement CVC = driver.findElement(By.id("input-card-cvc"));
        CVC.sendKeys("000");
        WebElement hintTgl = driver.findElement(By.id("cvc-hint-toggle"));
        hintTgl.click();

        WebElement bodyElm = driver.findElement(By.id("main-container"));

        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(driver,bodyElm);

        File outputfile = new File(screanshotpath);
        BufferedImage bufImg = screenshot.getImage();
        ImageIO.write(bufImg, "jpg", outputfile);
        Assert.assertTrue(new File(screanshotpath).exists());
    }

    @Test
    public void firstPayment() throws IOException {
        WebElement cardNumber = driver.findElement(By.id("input-card-number"));
        cardNumber.sendKeys("4000000000000002");
        WebElement cardHolder = driver.findElement(By.id("input-card-holder"));
        cardHolder.sendKeys("Karina Dmitrieva");
        WebElement selectMonth = driver.findElement(By.id("card-expires-month"));
        Select selectM = new Select(selectMonth);
        selectM.selectByVisibleText("06");
        WebElement selectYear = driver.findElement(By.id("card-expires-year"));
        Select selectY = new Select(selectYear);
        selectY.selectByVisibleText("2038");
        WebElement CVC = driver.findElement(By.id("input-card-cvc"));
        CVC.sendKeys("000");
        WebElement searchHint = driver.findElement(By.cssSelector("#cvc-hint"));

        Actions action = new Actions(driver);
        action.moveToElement(searchHint).perform();

        File cvcHint = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(cvcHint, new File("src/test/cvcHint.png"));
        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement Success = driver.findElement(By.id("success"));
        Success.click();
    }
@Test
    public void Payment1() {
    String currency = driver.findElement(By.id("currency")).getText();
    String totalAmount = driver.findElement(By.id("total-amount")).getText();
    String orderNumber = driver.findElement(By.id("order-number")).getText();
    WebElement cardNumber = driver.findElement(By.id("input-card-number"));
    cardNumber.sendKeys("4000000000000002");
    WebElement cardHolder = driver.findElement(By.id("input-card-holder"));
    cardHolder.sendKeys("Karina Dmitrieva");
    WebElement selectMonth = driver.findElement(By.id("card-expires-month"));
    Select selectM = new Select(selectMonth);
    selectM.selectByVisibleText("06");
    WebElement selectYear = driver.findElement(By.id("card-expires-year"));
    Select selectY = new Select(selectYear);
    selectY.selectByVisibleText("2038");
    WebElement CVC = driver.findElement(By.id("input-card-cvc"));
    CVC.sendKeys("000");
    WebElement payButton = driver.findElement(By.id("action-submit"));
    payButton.click();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    WebElement Success = driver.findElement(By.id("success"));
    Success.click();
    String paymentStatusTitleActual = driver.findElement(By.id("payment-status-title")).getText();
    String paymentStatusTitleExpected = "Success";
    Assert.assertEquals("Statuses do not match", paymentStatusTitleExpected,paymentStatusTitleActual);
    String paymentStatusActual = driver.findElement(By.id("payment-item-status")).getText();
    String paymentStatusExpected = "Payment status Confirmed";
    Assert.assertEquals("Statuses do not match", paymentStatusExpected,paymentStatusActual);
    String orderNumberActual = driver.findElement(By.id("payment-item-ordernumber")).getText();
    String orderNumberExpected = "Order number "+orderNumber;
    Assert.assertEquals("Statuses do not match", orderNumberExpected,orderNumberActual);
    String CurrencyActual = driver.findElement(By.xpath("//div[@id='payment-item-total']")).getText();
    String CurrencyExpected = "Total amount "+currency+"   "+totalAmount;
    Assert.assertEquals("Statuses do not match", CurrencyExpected,CurrencyActual);
    String totalAmountActual = driver.findElement(By.id("payment-item-total-amount")).getText();
    Assert.assertEquals("Statuses do not match", totalAmount,totalAmountActual);

}
    @Test
    public void Payment2() {
        String currency = driver.findElement(By.id("currency")).getText();
        String totalAmount = driver.findElement(By.id("total-amount")).getText();
        String orderNumber = driver.findElement(By.id("order-number")).getText();
        WebElement cardNumber = driver.findElement(By.id("input-card-number"));
        cardNumber.sendKeys("5555555555554444");
        WebElement cardHolder = driver.findElement(By.id("input-card-holder"));
        cardHolder.sendKeys("Karina Dmitrieva");
        WebElement selectMonth = driver.findElement(By.id("card-expires-month"));
        Select selectM = new Select(selectMonth);
        selectM.selectByVisibleText("06");
        WebElement selectYear = driver.findElement(By.id("card-expires-year"));
        Select selectY = new Select(selectYear);
        selectY.selectByVisibleText("2038");
        WebElement CVC = driver.findElement(By.id("input-card-cvc"));
        CVC.sendKeys("000");
        WebElement payButton = driver.findElement(By.id("action-submit"));
        payButton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement Success = driver.findElement(By.id("success"));
        Success.click();
        String paymentStatusTitleActual = driver.findElement(By.id("payment-status-title")).getText();
        String paymentStatusTitleExpected = "Success";
        Assert.assertEquals("Statuses do not match", paymentStatusTitleExpected,paymentStatusTitleActual);
        String paymentStatusActual = driver.findElement(By.id("payment-item-status")).getText();
        String paymentStatusExpected = "Payment status Confirmed";
        Assert.assertEquals("Statuses do not match", paymentStatusExpected,paymentStatusActual);
        String orderNumberActual = driver.findElement(By.id("payment-item-ordernumber")).getText();
        String orderNumberExpected = "Order number "+orderNumber;
        Assert.assertEquals("Statuses do not match", orderNumberExpected,orderNumberActual);
        String CurrencyActual = driver.findElement(By.xpath("//div[@id='payment-item-total']")).getText();
        String CurrencyExpected = "Total amount "+currency+"   "+totalAmount;
        Assert.assertEquals("Statuses do not match", CurrencyExpected,CurrencyActual);
        String totalAmountActual = driver.findElement(By.id("payment-item-total-amount")).getText();
        Assert.assertEquals("Statuses do not match", totalAmount,totalAmountActual);

    }

}
