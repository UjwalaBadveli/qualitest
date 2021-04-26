package com.qualiTest.test.pageObjects;

import com.qualiTest.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage extends PageObject {
   private By searchResults = By.cssSelector(".product-container");
   private By addToCartButton = By.cssSelector("[title='Add to cart']");
   private By proceedToCheckout = By.cssSelector("[title='Proceed to checkout']");

   public List<WebElement> getSearchResults() {
     return  waitForExpectedElements(searchResults);
   }

   public void addItemToCart(WebElement product) {
      hoverOnElement(product);
      product.findElement(addToCartButton).click();
   }

   public void proceedToCheckout() {
      waitForExpectedElement(proceedToCheckout).click();
   }

}
