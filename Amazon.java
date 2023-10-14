package demo;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
// import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {
    WebDriver driver;
    WebDriverWait wait;
    public Amazon()
    {
        System.out.println("Constructor: TestCases Amazon search");
        WebDriverManager.chromedriver().timeout(30).browserVersion("116.0.5845.0").setup();
        ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://www.amazon.in/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void endTest()
    {
        System.out.println("End Test: Closed Amazon search");
        driver.close();
        driver.quit();
    }

//sign in
By signinhylink = By.xpath("//div[@class='nav-line-1-container']");

//sign in text
By signintext = By.xpath("//h1[normalize-space()='Sign in']");

//mail
By usrname = By.xpath("//input[@type='email']");

//Continue button
By ctnbtn = By.xpath("//span[@id='continue']/span");

//email check
By emailtext = By.xpath("//span[text()='naveena.rajeei@gmail.com']");

//password
By psswrd = By.xpath("//input[@type='password']");

//keep me signed in check box
By signincheckbox = By.xpath("//input[@name='rememberMe']");

//Sign in button
By signin = By.xpath("//input[@id='signInSubmit']");

//holder name after login
By myname = By.xpath("//a[2]/div/descendant::span");

public void SignIn() throws InterruptedException{
driver.findElement(signinhylink).click();
Thread.sleep(2000);

Boolean textdisplayed = driver.findElement(signintext).isDisplayed();
System.out.println("Landed in Sign in page successfully? : " + textdisplayed);

driver.findElement(usrname).sendKeys("naveena.rajeei@gmail.com");
driver.findElement(ctnbtn).click();
Thread.sleep(3000);

if(driver.findElement(emailtext).getText().contains("naveena.rajeei"))
{
driver.findElement(psswrd).sendKeys("rani@123");
driver.findElement(signincheckbox).click();
Thread.sleep(3000);
driver.findElement(signin).click();
Thread.sleep(3000);
}
}

public void Naviname(){
String namestring = driver.findElement(myname).getText();
System.out.println("The Actual name in website : " + namestring);
//string to string array
String[] arr = namestring.split(", ");
for(int i=0; i<arr.length-1; i++)
{
    System.out.println("The Account holder name : " + arr[1]);
}
System.out.println("The title of website is " + driver.getTitle());
System.out.println("The URL od website is " + driver.getCurrentUrl());
    }

//select item
By search = By.xpath("//select[@name='url']");

//search box
By searchbox = By.xpath("(//input)[4]");

//list of seaches matched
By searchlist = By.xpath("//div[@class='s-suggestion-container']");

//Desired one from list
By desireditem = By.xpath("(//div[@class='s-suggestion-container']/div/span[@class='s-heavy'])[2]");

public void searchproducts(){
Select select = new Select(driver.findElement(search));
select.selectByVisibleText("Garden & Outdoors");
driver.findElement(searchbox).sendKeys("Flower pots");
  
List<WebElement> flowerpots = driver.findElements(searchlist);
System.out.println("The total listed items for desired search : " + flowerpots.size());
 //String itemtext = "flower pots" + driver.findElement(desireditem).getText();
String itemtext = "flower pots for home decoration";  
String ftext;
   for(WebElement flowerpot : flowerpots){
    ftext = flowerpot.getText();
    if(ftext.equals(itemtext))
    {
    flowerpot.click();
    break;
    }
}
driver.navigate().refresh();
}


//list of results by search
By fpotsresults = By.xpath("//div[@class='a-section aok-relative s-image-square-aspect']");

//one product from list
By oneproduct = By.linkText("TIED RIBBONS Cycle Shape Flower Vase with Peonies Bunches");

public void desriredproduct() throws InterruptedException
{
List<WebElement> items = driver.findElements(fpotsresults);
System.out.println("The total items of flower pots result: " + items.size());
driver.findElement(oneproduct).click();
Thread.sleep(3000);
}

//image
By image = By.xpath("//div[@id='imgTagWrapperId']/descendant::img");

//product title
By ptitle = By.xpath("//h1/span[1]");

//Ratings
By rating = By.xpath("(//span[@class='a-size-base a-color-base'])[2]");

//Price
By price = By.xpath("(//span[@aria-hidden='true']/span[@class='a-price-whole'])[6]");


public void product_page()
{
    //windows handle
String originalwindow = driver.getWindowHandle();
Set<String> childwindows = driver.getWindowHandles();
for(String childwindow : childwindows)
{
    if(!childwindow.equals(originalwindow))
    {
        driver.switchTo().window(childwindow);
        System.out.println("Title of the desired product page : " + driver.getTitle());
        System.out.println("URL of the desired product page : " + driver.getCurrentUrl());
        String imgurl = driver.findElement(image).getAttribute("src");
        System.out.println("The image url of flower vase: " + imgurl);
        System.out.println("The product title is " + driver.findElement(ptitle).getText());
        System.out.println("The rating of desired product is " + driver.findElement(rating).getText());
        System.out.println("The cost of the desired product is " + driver.findElement(price).getText());
    }
}
}

//Quantity
By qty = By.xpath("(//div[@class='a-column a-span12 a-text-left'])[4]");

//Qty dropdown
By qtydrpdwn = By.xpath("//select[@name='quantity']");

//Add to cart button
By cart2btn = By.xpath("//input[@id='add-to-cart-button']");

//BuyNow button
By buynow = By.xpath("//input[@id='buy-now-button']");


public void verifycart() throws InterruptedException{
Boolean quantity = driver.findElement(qty).isDisplayed();
System.out.println("The Quantity dropdown is displayed? : " + quantity);

Boolean cart = driver.findElement(cart2btn).isDisplayed();
System.out.println("The Add to Cart is displayed? : " + cart);

Boolean buy = driver.findElement(buynow).isDisplayed();
System.out.println("The BuyNow is displayed? : " + buy);

Select select = new Select(driver.findElement(qtydrpdwn));
select.selectByValue("3");
driver.findElement(cart2btn).click();
Thread.sleep(3000);
}

//cart image
By cartimg = By.xpath("//div[@id='nav-cart-count-container']");

//cart item count
By cartcount = By.xpath("//div[@id='nav-cart-count-container']/span[1]");

//cart product text
By cartptext = By.xpath("(//span[@class='a-truncate-cut'])[5]");

//added2cart text
By added2cart = By.xpath("//div[@id='NATC_SMART_WAGON_CONF_MSG_SUCCESS']/span");

public void costvalidation()
{
    double oneproduct = 499.00;
    String ss = driver.findElement(cartcount).getText();
    int i = Integer.parseInt(ss);
    double x = i * oneproduct;
    System.out.println("The Subtotal of cart ietms is " + x);
}
}
