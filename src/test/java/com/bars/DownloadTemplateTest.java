package com.bars;

import com.codeborne.selenide.junit.ScreenShooter;
import com.codeborne.selenide.junit.TextReport;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DownloadTemplateTest { private static WebDriver driver;
    @Rule
    public ScreenShooter screenShooter = ScreenShooter.failedTests().to("test-results/reports");
    @Rule
    public TestRule report = new TextReport().onFailedTest(true).onSucceededTest(false);

    @BeforeClass
    public static void setup() {
        timeout = 40000;
//        baseUrl = "http://10.10.17.22:8080/barsroot/account/login";
//        baseUrl = "http://10.10.17.24:8080/barsroot/account/login";
//        baseUrl = "http://10.10.17.50:8080/barsroot/account/login";
//        baseUrl = "http://10.10.17.40:8080/barsroot/account/login";
//        baseUrl = "http://10.10.17.40:8082/barsroot/account/login";
//        baseUrl = "http://10.10.10.198:11111/barsroot/account/login";
//        browser = "chrome";
        browser = "ie";
        startMaximized = true;
//        System.setProperty("webdriver.ie.driver", ".\\IEDriverServer.exe");
        InternetExplorerDriverManager.getInstance(DriverManagerType.IEXPLORER).arch32().setup();
//        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        open("/");

    }
    private void userDelay(int time) {
        try {Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace(); }
    }
    @Test
    public void DownloadTemplate() throws IOException {
        $("#txtUserName").setValue("absadm01");
        $("#txtPassword").setValue("qwerty").pressEnter();
//        $("#btLogIn").click();
        $x("//input[@value = 'Продовжити']").click();
        switchTo().frame($("#mainFrame"));
        $(By.tagName("h1")).shouldHave(text("Оголошення"));
        switchTo().defaultContent();
        $(".btn_branches").shouldBe(visible).click();
        $(byXpath("//div[@id='treeview']/ul/li/ul/li/div/span[2]/span")).shouldBe(visible).shouldHave(text("300465")).click();
        getWebDriver().navigate().refresh();
        //!!!!Адміністрування шаблонів!!!!
        $("#findOpersText").setValue("Адміністрування шаблонів").pressEnter();
        $x("//*[@class='oper-name']/span[text()='Адміністрування шаблонів']").shouldBe(visible).click();
        switchTo().frame($("#mainFrame"));
        $x(".//*[text()='Фільтр перед населенням таблиці']").shouldBe(visible);
        $x("(//*[@class='x-btn-inner x-btn-inner-center'])[text()='Далі']").shouldBe(visible).click();
        $x("//*[text()='DPT_STROK_PENS']").shouldBe(visible).click();

        Runtime.getRuntime().exec(".\\DownloadTemplate.exe");
        $(byCssSelector("a[data-qtip='скачати шаблон']")).shouldBe(visible).click();
        switchTo().defaultContent();
        $("#btnProfile").shouldBe(visible).click();
        $x("//*[@id='userProfile']/div[2]/a[2]").shouldBe(visible).click();
    }

}
