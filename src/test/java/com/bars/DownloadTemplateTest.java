package com.bars;


import com.codeborne.selenide.logevents.SelenideLogger;
import com.codeborne.selenide.testng.ScreenShooter;
import com.codeborne.selenide.testng.TextReport;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


@Listeners({TextReport.class, ScreenShooter.class})
public class DownloadTemplateTest {

    @BeforeClass
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
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
//        holdBrowserOpen=true;
//        System.setProperty("webdriver.ie.driver", ".\\IEDriverServer.exe");

        InternetExplorerDriverManager.getInstance(DriverManagerType.IEXPLORER).arch32().setup();

//        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        open("/");

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

    @AfterClass
    public static void tearDown() {
        SelenideLogger.removeListener("AllureSelenide");
    }
}
