package org.example.pageObjects;

import org.example.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;
    public OrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//tr[@class='ng-star-inserted']/td[2]")
    List<WebElement> productNames;

    @FindBy(xpath = "//li[@class='totalRow']/button")
    WebElement checkoutButton;

    public Boolean verifyOrderDisplay(String productName){
        Boolean match = productNames.stream().anyMatch(p->p.getText().equals(productName));
        return match;
    }

}
