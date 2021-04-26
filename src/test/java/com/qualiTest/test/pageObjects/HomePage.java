package com.qualiTest.test.pageObjects;

import com.qualiTest.test.framework.PageObject;
import org.openqa.selenium.By;

public class HomePage extends PageObject {
   private By searchButton=By.cssSelector("#searchbox > button");
   private By searchBox=By.id("search_query_top");
   private By accountDetails= By.cssSelector("[title='View my customer account']");
   private By login = By.cssSelector("[title='Log in to your customer account']");

    public void clickSearchButton() {
      waitForExpectedElement(searchButton).click();
      waitForPageLoad();
   }

    public void enterSearchWord(String searchWord) {
       waitForExpectedElement(searchBox).sendKeys(searchWord);
    }

    public void viewAccountDetails(){
        waitForExpectedElement(accountDetails).click();
    }

    public void selectLoginUser(){
        waitForExpectedElement(login).click();
        waitForPageLoad();
    }

    public String getUserNameFromHeader() {
        return waitForExpectedElement(accountDetails).getText();
    }
}
