package Tests;

import org.example.pageObjects.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;


public class StandAloneTest {

    public static void main(String[]args) throws InterruptedException {

        //new comments are added

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LandingPage landingPage = new LandingPage(driver);

        String productName = "ZARA COAT 3";

        driver.get("https://rahulshettyacademy.com/client");

        driver.findElement(By.id("userEmail")).sendKeys("frank0@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Georgesmith1");
        driver.findElement(By.id("login")).click();
// Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
        List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));

        WebElement productTittle = products.stream()
                .filter(p->p.findElement(By.cssSelector("b"))
                        .getText().equals(productName)).findFirst()
                .orElse(null);

        productTittle.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //.ng-animating (this is a class name of animating after click on the Add To Card Button)
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

       driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

       // Get text of products from our card list
        List<WebElement> cardProducts = driver.findElements(By.xpath("//div[@class='infoWrap'] //h3"));
        Boolean match = cardProducts.stream().anyMatch(p->p.getText().equals(productName));
        Assert.assertTrue(match);

        driver.findElement(By.xpath("//li[@class='totalRow']/button")).click();

        //driver.findElement(By.cssSelector(".form-group")).sendKeys("Ind");

        //List<WebElement> options = driver.findElements(By.cssSelector("span[class*='inserted']"));


        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"Ind")
                .build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class*='list-group']")));
        driver.findElement(By.xpath("(//button[contains(@class,'list-group')])[2]")).click();
        a.click(driver.findElement(By.cssSelector(".action__submit"))).build().perform();

        String confirmMessage = driver.findElement(By.tagName("h1")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

       // driver.close();





    }

}
