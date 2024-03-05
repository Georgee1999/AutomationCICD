package org.example.pageObjects;

import org.example.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Collection;
import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    WebDriver driver;
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");


    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        //Aby anotace @FindBy věděla jaký má použít driver, musíme ho předat do paramteru téhle metody:
        // Pak nemusíme psát driver.findElement.....
        PageFactory.initElements(driver, this);
    }

    //List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
    @FindBy(css = ".col-lg-4")
    List<WebElement> products;
    @FindBy(css = ".ng-animating")
    WebElement spinner;


    public List<WebElement> getProductList() {
        waitForElementToAppear(By.cssSelector(".col-lg-4"));
        return products;
    }

    public WebElement getProductByName(String productName) {
        WebElement productTittle = getProductList().stream()
                .filter(p -> p.findElement(By.cssSelector("b"))
                .getText().equals(productName)).findFirst()
                .orElse(null);
        return productTittle;
    }

    //productTittle.findElement(By.cssSelector(".card-body button:last-of-type")).click();

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement productTittle = getProductByName(productName);
        productTittle.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }

}
