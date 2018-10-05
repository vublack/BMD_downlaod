package com.bars;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DownloadTemplateTest { private static WebDriver driver;

    @BeforeClass
    public static void setup() {
        String browser = new File(DownloadTemplateTest.class.getResource("/IEDriverServer.exe").getFile()).getPath();
        System.setProperty("webdriver.ie.driver", browser);
        driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://10.10.17.40:8080/barsroot/account/login/");
    }
    public void userDelay(int time) {
        try {Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace(); }
    }

    @Test
    public void DownloadTemplate() throws IOException {
        WebElement loginField = driver.findElement(By.id("txtUserName"));
        loginField.clear();
        loginField.sendKeys("absadm01");
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("qwerty");
        WebElement loginButton = driver.findElement(By.id("btLogIn"));
        loginButton.click();
        WebElement ProdovjButton = driver.findElement(By.xpath("//input[@value = 'Продовжити']"));
        (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.visibilityOf(ProdovjButton));
        ProdovjButton.click();
        driver.switchTo().frame(driver.findElement(By.id("mainFrame")));
        userDelay(1000);
        WebElement H1 = driver.findElement(By.xpath(".//*[text()='Оголошення']"));
        (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.visibilityOf(H1));
        driver.switchTo().defaultContent();

        //!!!!Адміністрування шаблонів!!!!
        userDelay(1000);
        WebElement findOpers = driver.findElement(By.id("findOpersText"));
        findOpers.clear();
        findOpers.sendKeys("Адміністрування шаблонів");
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ENTER).perform();
        WebElement AdmTempl = driver.findElement(By.xpath("//*[@class='oper-name']/span[text()='Адміністрування шаблонів']"));
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOf(AdmTempl));
        AdmTempl.click();
        userDelay(1000);

        //!!!! Перехід на фрейм!!!!
        driver.switchTo().frame(driver.findElement(By.id("mainFrame")));
        userDelay(1000);
        WebElement FilterWindow = driver.findElement(By.xpath(".//*[text()='Фільтр перед населенням таблиці']"));
        (new WebDriverWait(driver, 100))
                .until(ExpectedConditions.visibilityOf(FilterWindow));

        WebElement FurtherButton = driver.findElement(By.xpath("(//*[@class='x-btn-inner x-btn-inner-center'])[text()='Далі']"));
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(FurtherButton));
        FurtherButton.click();
        userDelay(1000);
        WebElement ChooseRow = driver.findElement(By.xpath("//*[text()='DPT_STROK_PENS']"));
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(ChooseRow));
        ChooseRow.click();
        Process proc = Runtime.getRuntime().exec("d:\\AUTOtest\\DownloadTemplate\\DownloadTemplate.exe");
        WebElement DownloadTemplate = driver.findElement(By.cssSelector("a[data-qtip='скачати шаблон']"));
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOf(DownloadTemplate));
        DownloadTemplate.click();

    }
    @AfterClass
    public static void tearDown() {
        driver.switchTo().defaultContent();
        WebElement profileButton = driver.findElement(By.id("btnProfile"));
        profileButton.click();
        WebElement logoutButton = driver.findElement(By.xpath("//*[@id='userProfile']/div[2]/a[2]"));
        logoutButton.click();
        driver.quit();
    }
}
