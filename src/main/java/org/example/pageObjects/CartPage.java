package org.example.pageObjects;

import org.example.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;
    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='infoWrap'] //h3")
    List<WebElement> productTittles;

    @FindBy(xpath = "//li[@class='totalRow']/button")
    WebElement checkoutButton;

    public Boolean verifyProductDisplay(String productName){
        Boolean match = productTittles.stream().anyMatch(p->p.getText().equals(productName));
        return match;
    }

    public CheckoutPage goToCheckout(){
        checkoutButton.click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }



}
