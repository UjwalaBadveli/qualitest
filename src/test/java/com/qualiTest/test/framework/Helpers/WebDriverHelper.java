package com.qualiTest.test.framework.Helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverHelper {
    private static WebDriver DRIVER;
    private static String BROWSER;
    private static String HUB;
    private static String PORT;
    static {
        BROWSER=System.getProperty("Browser",Props.getProp("Browser"));
        HUB=System.getProperty("HubHost",Props.getProp("HubHost"));
        PORT=System.getProperty("HubPort",Props.getProp("HubPort"));
        System.setProperty("webdriver.chrome.driver","tools/drivers/chrome/windows/chromedriver.exe");
    }
public static WebDriver getWebDriver(){
        return  DRIVER;
}

    public static WebDriver configureWebDriver() {
        if(BROWSER.equalsIgnoreCase("chrome"))
        {
            ChromeOptions options=new ChromeOptions();
            DRIVER=new ChromeDriver(options);
        }
         else if(BROWSER.equalsIgnoreCase("firefox"))
        {
            FirefoxOptions options=new FirefoxOptions();
            DRIVER=new FirefoxDriver(options);
        }
        return DRIVER;
    }
}
