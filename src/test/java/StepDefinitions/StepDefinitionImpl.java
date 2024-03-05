package StepDefinitions;

import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pageObjects.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void i_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }

    // (.*) it means that the matches any strings
    // Když používám Regular expression musím to zabalit do = ^String$
    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String userName, String password){
        productCatalogue = landingPage.loginAplication(userName,password);
    }

    @When("^I add product (.*) to Cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.*) add submit the order*")
    public void checkout_add_submit_the_order(String productName) throws InterruptedException {
        CartPage cartPage = productCatalogue.goToCardPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,150)");
        Thread.sleep(1000);
        confirmationPage = checkoutPage.sumbitOrder();
    }

    @Then("{string} message is displayed on Confirmation Page")
    public void message_displayed_on_confirmation_page(String string){
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
    }

    @Then("{string} message is displayed")
    public void message_is_displayed(String string) throws Throwable{
        Assert.assertEquals(string, landingPage.getErrorMessage());
        driver.close();
    }


}
