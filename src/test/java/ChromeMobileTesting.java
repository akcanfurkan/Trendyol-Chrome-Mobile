import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ChromeMobileTesting {

    @Test
    public void chromeMobileTesting() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("deviceName", "PIXEL");
        caps.setCapability("appPackage", "com.android.chrome");
        caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");

        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


        driver.get("https://www.trendyol.com/");


        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        MobileElement logo = driver.findElement(By.xpath("//android.widget.Image[@text=\"Trendyol Logo\"]"));
        Assert.assertTrue(logo.isEnabled());
        System.out.println("Giriş sayfasına ulaşıldı.");

        MobileElement accountIconButton = driver.findElement(By.xpath("//android.view.View[@text=\"\uE91A\"]"));
        accountIconButton.click();

        MobileElement loginEmail = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"login-email\"]"));
        loginEmail.click();
        loginEmail.sendKeys("example@gmail.com");

        MobileElement loginPassword = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"login-password-input\"]"));
        loginPassword.click();
        loginPassword.sendKeys("Test12345.");

        MobileElement loginButton = driver.findElement(By.xpath("//android.widget.Button[@text=\"GİRİŞ YAP\"]"));
        loginButton.click();


        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[contains(@text, 'Böyle bir hesap bulunamadı')]")));


        MobileElement errorMessage = driver.findElement(By.xpath("//android.widget.TextView[contains(@text, 'Böyle bir hesap bulunamadı')]"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Böyle bir hesap bulunamadı ifadesi görüntülenmedi.");

        System.out.println("Böyle bir hesap bulunamadı");


        driver.quit();
    }
}

