package Tests;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.example.pageObjects.CartPage;
import org.example.pageObjects.ProductCatalogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
    public void loginErrorValidation() throws InterruptedException, IOException {

        landingPage.loginAplication("frank0@gmail.com", "Gesmith1");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void productErrorValidation() throws InterruptedException, IOException {

        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue = landingPage.loginAplication("frank0@gmail.com", "Georgesmith1");
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);

        CartPage cartPage = productCatalogue.goToCardPage();
        Boolean match = cartPage.verifyProductDisplay( "ZARA COAT 33");
        Assert.assertFalse(match);
    }


}
