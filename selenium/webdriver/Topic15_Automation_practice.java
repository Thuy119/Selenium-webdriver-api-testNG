package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic15_Automation_practice {
	WebDriver driver;
	WebDriverWait explicitWait;
	Select select;
	String date, month, year, state, country, currentpassword, newpassword, size;
	
	String projectLocation = System.getProperty("user.dir");
	
	By dateBy = By.id("days");
	By monthBy = By.id("months");
	By yearBy = By.id("years");
	By stateBy = By.id("id_state");
	By countryBy = By.id("id_country");
	By sizeBy = By.id("group_1");
	
	

	

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", ".\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver(); 
		
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		date = "8  ";
		month = "May ";
		year = "1994  ";
		state = "Washington";
		country = "United States";
		currentpassword = "Thuy@@123";
		newpassword = "Qa@@123";
		size = "M";
		
		
		
	}

	@Test
	public void TC_01() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	   
		
		//Enter email address
		driver.findElement(By.id("email_create")).sendKeys(generateEmail());
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("SubmitCreate"))).click();
		
		
		//Create an account
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Create an account']")).getText(), "CREATE AN ACCOUNT");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.id("id_gender2")));
		
		driver.findElement(By.id("id_gender2")).click();
		
		driver.findElement(By.id("customer_firstname")).sendKeys("Thuy");
		driver.findElement(By.id("customer_lastname")).sendKeys("Nguyenn");
		
		driver.findElement(By.id("passwd")).sendKeys(currentpassword);
		
		
		
		
		
		/*
		 * explicitWait.until(ExpectedConditions.elementToBeClickable(dateBy)); select =
		 * new Select(driver.findElement(dateBy)); select.selectByVisibleText(date);
		 * Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		 * Assert.assertEquals(select.getOptions().size(), 32);
		 */
		
			/*
			 * explicitWait.until(ExpectedConditions.elementToBeClickable(monthBy)); //khoi
			 * tao truyen vao 1 webElement select = new Select(driver.findElement(monthBy));
			 * //chon thang select.selectByVisibleText(month); //lay ra thang vua chon,
			 * verify Assert.assertEquals(select.getFirstSelectedOption(), month); //lay ra
			 * all item Assert.assertEquals(select.getOptions().size(), 13);
			 */
		 
		 
			/*
			 * explicitWait.until(ExpectedConditions.elementToBeClickable(yearBy)); select =
			 * new Select(driver.findElement(yearBy)); select.selectByVisibleText(year);
			 * Assert.assertEquals(select.getFirstSelectedOption(), year);
			 * Assert.assertEquals(select.getOptions().size(), 121);
			 * 
			 */
		 
		
		
		driver.findElement(By.id("newsletter")).click();
		driver.findElement(By.id("optin")).click();
		
		
		driver.findElement(By.id("company")).sendKeys("QA Tech");
		driver.findElement(By.id("address1")).sendKeys("10 Ngo Gia Tu");
		driver.findElement(By.id("address2")).sendKeys("Level 5");
		driver.findElement(By.id("city")).sendKeys("Da Nang");
		
		select = new Select(driver.findElement(stateBy));
		select.selectByVisibleText(state);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),
		  state); 
		Assert.assertEquals(select.getOptions().size(), 54);
		
		driver.findElement(By.id("postcode")).sendKeys("78119");
		
		
		select = new Select(driver.findElement(countryBy));
		select.selectByVisibleText(country);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), country);
		Assert.assertEquals(select.getOptions().size(), 2);
		
		
		driver.findElement(By.id("other")).sendKeys("This is for Testing");
		driver.findElement(By.id("phone")).sendKeys("05113788994");
		driver.findElement(By.id("phone_mobile")).sendKeys("090827626");
		
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys("Hoa Nhon, Hoa Vang");
		
		driver.findElement(By.id("submitAccount")).click();
		
		
		//My account
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='My account']")).getText(), "My account");
		
		//Order History & details
		driver.findElement(By.xpath("//span[text()='Order history and details']")).click();
		//sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Order history']")).getText(), "ORDER HISTORY");
		//sleepInSecond(2);
		
		//Back to your account
		driver.findElement(By.xpath("//i[@class='icon-chevron-left']")).click();
		
		//My Credit Slips
		driver.findElement(By.xpath("//span[text()='My credit slips']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(text(), 'Credit slips')]")).getText(), "CREDIT SLIPS");
		
		//sleepInSecond(2);
		driver.findElement(By.xpath("//i[@class='icon-chevron-left']")).click();
		
		//My address
		driver.findElement(By.xpath("//span[text()='My addresses']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='My addresses']")).getText(), "MY ADDRESSES");
		//Update
		driver.findElement(By.xpath("//span[text()='Update']")).click();
		
		//Update Address
		driver.findElement(By.id("address1")).clear();
		//sleepInSecond(2);
		driver.findElement(By.id("address1")).sendKeys("20 Le Duan");
		driver.findElement(By.id("submitAddress")).click();
		//sleepInSecond(2);
		
		//Back to your account
		driver.findElement(By.xpath("//i[@class='icon-chevron-left']")).click();
		
		//My personal Information
		driver.findElement(By.xpath("//span[text()='My personal information']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h1[contains(text(), 'Your personal information')]")).getText(), "YOUR PERSONAL INFORMATION");
		
		//Update password
		driver.findElement(By.id("old_passwd")).sendKeys(currentpassword);
		driver.findElement(By.id("passwd")).sendKeys(newpassword);
		driver.findElement(By.id("confirmation")).sendKeys(newpassword);
		driver.findElement(By.xpath("//span[text()='Save']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='alert alert-success']")).getText(), "Your personal information has been successfully updated.");
		
		//sleepInSecond(2);
		
		//Back to your account
		driver.findElement(By.xpath("//i[@class='icon-chevron-left']")).click();
		
		//My Wishlists
		driver.findElement(By.xpath("//span[text()='My wishlists']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='My wishlists']")).getText(), "MY WISHLISTS");
		
		//Select product in Top Sellers
		driver.findElement(By.xpath("//ul[@class='block_content products-block']/li[1]//img")).click();
		
		//Write a review
		driver.findElement(By.xpath("//a[contains(text(),'Write a review')]")).click();
		
		driver.findElement(By.xpath("//input[@id='comment_title']")).sendKeys("Thuy Nguyen Review");
		driver.findElement(By.id("content")).sendKeys("Amazing goodjob!");
		driver.findElement(By.id("submitNewMessage")).click();
		
		sleepInSecond(3);
		//Close popup
		driver.findElement(By.xpath("//div[@class='fancybox-outer']//button/span")).click();
		
		//Add quantity
		driver.findElement(By.xpath("//i[@class='icon-plus']")).click();
		
		//Select size
		select = new Select(driver.findElement(sizeBy));
		select.selectByVisibleText(size);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), size);
		Assert.assertEquals(select.getOptions().size(), 3);
		
		//Select color
		driver.findElement(By.xpath("//a[@title='Green']")).click();
		//Add to cart
		driver.findElement(By.xpath("//span[text()='Add to cart']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='our_price_display']")).getText(), "$16.40");
		
		
		
		//Verify Color, size, quantity, price
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Green, M']")).getText(), "Green, M");
		Assert.assertEquals(driver.findElement(By.xpath("//strong[text()='Quantity']/following-sibling::span[text()='2']")).getText(), "2");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='layer_cart_product_price']")).getText(), "$32.80");
		
		
		//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Proceed to checkout']/span")));
		driver.findElement(By.xpath("//a[@title='Proceed to checkout']/span")).click();
		
		//1 Summary
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(), 'Summary')]")).getText(), "01. Summary");
		Assert.assertEquals(driver.findElement(By.xpath("//td[@id='total_shipping']")).getText(), "$2.00");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='total_price']")).getText(), "$34.80");
		
		
		driver.findElement(By.xpath("//div[@id='center_column']//a[@title='Proceed to checkout']/span")).click();
		
		//03. Address
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@id='order_step']//span[contains(text(), 'Address')]")).getText(), "03. Address");
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("This is for Testing");
		
		driver.findElement(By.xpath("//button[@name='processAddress']/span")).click();
		
		//Shipping
		Assert.assertEquals(driver.findElement(By.xpath("//ul[@id='order_step']//span[contains(text(), 'Shipping')]")).getText(), "04. Shipping");
		
		driver.findElement(By.xpath("//input[@id='cgv']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='delivery_option_price']")).getText(), "$2.00");
		
		driver.findElement(By.xpath("//button[@name='processCarrier']")).click();
		
		//Payment
		Assert.assertEquals(driver.findElement(By.xpath("//li[@id='step_end']/span[contains(text(), 'Payment')]")).getText(), "05. Payment");
		Assert.assertEquals(driver.findElement(By.xpath("//td[@id='total_shipping']")).getText(), "$2.00");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='total_price']")).getText(), "$34.80");
		
		//Bank-wire payment
		driver.findElement(By.xpath("//a[@title='Pay by bank wire']")).click();
		driver.findElement(By.xpath("//span[text()='I confirm my order']")).click();
		
		
		Assert.assertEquals(driver.findElement(By.xpath("//h1[text()='Order confirmation']")).getText(), "ORDER CONFIRMATION");
		
		driver.findElement(By.xpath("//a[@title='Back to orders']")).click();
		
		driver.findElement(By.xpath("//td[@class='history_detail footable-last-column']//span")).click();
		
		scrollToElement("//textarea[@name='msgText']");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//textarea[@name='msgText']")).sendKeys("QA");
		driver.findElement(By.xpath("//button[@name='submitMessage']")).click();
		
		//Back to your account
		driver.findElement(By.xpath("//i[@class='icon-chevron-left']")).click();
		
		//Back to home
		driver.findElement(By.xpath("//a[@title='Home']")).click();
		
		//Search
		driver.findElement(By.xpath("//input[@id='search_query_top']")).sendKeys("Faded Short Sleeve T-shirts");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='submit_search']/span")));
		driver.findElement(By.xpath("//button[@name='submit_search']/span")).click();
		
		//Sign Out
		driver.findElement(By.xpath("//a[@class='logout']")).click();
		
		
		
	}

	
	public String generateEmail() {
		Random rand = new Random();
		return "qatest" + rand.nextInt(9999) + "@gmail.com";
		
	}
	
	public void sleepInSecond(long timeInSecond) {

		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void scrollToElement( String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		 WebElement element = driver.findElement(By.xpath(locator)); //bang voi hang ben tren
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	

}
