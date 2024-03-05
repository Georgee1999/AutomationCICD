package org.example.pageObjects;

import org.example.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;
    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "input[placeholder='Select Country']")
    private WebElement country;

    @FindBy(xpath = "//a[text()='Place Order ']")
    private WebElement submit;

    @FindBy(xpath = "(//button[contains(@class,'list-group')])[2]")
    private WebElement selectCountry;

    private By results = By.cssSelector("section[class*='list-group']");


    public void selectCountry(String countryName){
        Actions a = new Actions(driver);
        a.sendKeys(country,countryName).build().perform();
        waitForElementToAppear(results);
        selectCountry.click();
    }

    public ConfirmationPage sumbitOrder(){
        submit.click();
        return new ConfirmationPage(driver);
    }


}
