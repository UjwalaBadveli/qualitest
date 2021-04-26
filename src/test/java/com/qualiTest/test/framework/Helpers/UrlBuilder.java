package com.qualiTest.test.framework.Helpers;

public class UrlBuilder {
    private static String url;
    static{
        url=Props.getProp("homePageUrl");
    }
    public static void openHomePage(){
        WebDriverHelper.configureWebDriver().navigate().to(url);
    }
}
