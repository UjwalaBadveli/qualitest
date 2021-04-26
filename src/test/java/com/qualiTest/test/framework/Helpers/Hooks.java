package com.qualiTest.test.framework.Helpers;

import cucumber.api.java.After;

public class Hooks {
    @After
    public void closeDriver(){
        if(WebDriverHelper.getWebDriver() !=null)
            WebDriverHelper.getWebDriver().quit();
    }

}
