package org.example.pageObjects;

import org.example.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        // Pomocí super předáváme driver [LandingPage -> AbstractComponent] Child To Parent
        super(driver);
        this.driver = driver;
        //Aby anotace @FindBy věděla jaký má použít driver, musíme ho předat do paramteru téhle metody:
            // Pak nemusíme psát driver.findElement.....
        PageFactory.initElements(driver,this);
    }

    //WebElement userEmail = driver.findElement(By.id("userEmail"));
    // This called PageFactory
    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement passwordEle;

    @FindBy(id = "login")
    WebElement submit;
    @FindBy(css = "div[class*='flyInOut']")
    WebElement errorMessage;

    public String  getErrorMessage(){
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }

    public ProductCatalogue loginAplication(String email, String password){
        userEmail.sendKeys(email);
        passwordEle.sendKeys(password);
        submit.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }


}
