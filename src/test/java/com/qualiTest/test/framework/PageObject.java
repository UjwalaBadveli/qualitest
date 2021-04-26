package com.qualiTest.test.framework;

import com.qualiTest.test.framework.Helpers.WebDriverHelper;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageObject {
    @Getter
    private static final long DRIVER_WAIT_TIME = 10;
    @Getter @Setter
    private static WebDriverWait wait;
    @Getter @Setter
    protected static WebDriver webDriver;


    protected PageObject() {
        this.webDriver = WebDriverHelper.getWebDriver();
    }

    /**
     * Returns the current Url from page
     **/
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    /**
     * Returns the current page title from page
     */
    public String getCurrentPageTitle() {
        return webDriver.getTitle();
    }


    /**
     * Find the dynamic element wait until its visible
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    protected WebElement waitForExpectedElement(final By by) {
         wait = new WebDriverWait(webDriver,DRIVER_WAIT_TIME );
        return wait.until(visibilityOfElementLocated(by));
    }

    /**
     * Find the dynamic element wait until its presnt
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    protected WebElement waitForElementToPresent(final By by) {
        wait = new WebDriverWait(webDriver,DRIVER_WAIT_TIME );
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Find the dynamic elements wait until its visible
     *
     * @param by Element location found by css, xpath, id etc...
     **/
    protected List<WebElement> waitForExpectedElements(final By by) {
        wait = new WebDriverWait(webDriver,DRIVER_WAIT_TIME );
        wait.until(visibilityOfElementLocated(by));
        return webDriver.findElements(by);
    }

    /**
     * Find the dynamic element wait until its visible for a specified time
     *
     * @param by                Element location found by css, xpath, id etc...
     * @param waitTimeInSeconds max time to wait until element is visible
     **/

    public WebElement waitForExpectedElement(final By by, long waitTimeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, waitTimeInSeconds);
            return wait.until(visibilityOfElementLocated(by));
        } catch (NoSuchElementException e) {
            return null;
        } catch (TimeoutException e) {
            return null;
        }
    }

    private ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) throws NoSuchElementException {
        return driver -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            WebElement element = webDriver.findElement(by);
            return element.isDisplayed() ? element : null;
        };
    }


    public void jsScrollIntoView(By by) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView();", waitForExpectedElement(by));
    }

    public void moveToElementToClick(WebElement element) {
        Actions builder = new Actions(webDriver);
        builder.moveToElement(element);
        builder.perform();
    }

    public void dropDownSelectByVisualText(WebElement element,String visualText){
        Select select=new Select(element);
        select.selectByVisibleText(visualText);
    }

    public void dropDownSelectByValue(WebElement element,String value){
        Select select=new Select(element);
        select.selectByValue(value);
    }

    public void waitForPageLoad(){
        wait = new WebDriverWait(webDriver,30 );
        wait.until(webDriver -> "complete".equals((String) ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState")));
    }

    public void hoverOnElement(WebElement element){
        Actions action = new Actions(webDriver);
        action.moveToElement(element).build().perform();
    }

}
