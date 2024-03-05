package Tests;

import TestComponents.BaseTest;
import org.example.pageObjects.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData",groups = "Purchase")
    public void submitOrder(HashMap<String ,String > input) throws InterruptedException, IOException {

        ProductCatalogue productCatalogue = landingPage.loginAplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCardPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,150)");
        Thread.sleep(1000);
        ConfirmationPage confirmationPage = checkoutPage.sumbitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    // To verify ZARA COAT 3 is displaying in orders page
    @Test(dependsOnMethods = {"submitOrder"}) // only after submitOrder method
    public void orderHistoryTest(){
        ProductCatalogue productCatalogue = landingPage.loginAplication("frank0@gmail.com", "Georgesmith1");
        OrderPage orderPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }


    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//data//PurchaseOrder.json");
        return new Object[][]{
                {data.get(0)},
                {data.get(1)}
        };

        /*
        HashMap<String ,String > map = new HashMap<String ,String >();
        map.put("email", "frank0@gmail.com");
        map.put("password", "Georgesmith1");
        map.put("product", "ZARA COAT 3");

        HashMap<String ,String > map2 = new HashMap<String ,String >();
        map2.put("email", "shetty@gmail.com");
        map2.put("password", "Georgesmith1");
        map2.put("product", "ADIDAS ORIGINAL");
*/

    }


}
