package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic03_Excercise {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("");
	}

	@Test
	public void TC_01_Login_With_Empty_Email_And_Password() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.id("send2")).click();

		String errorEmailMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(errorEmailMessage, "This is a required field.");
        // Gan tuc tiep, ko can khai bao bien
		//Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")), "This is a required field.");
		String errorPasswordMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(errorPasswordMessage, "This is a required field.");

	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("12341234@123.123");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();

		String invalidEmailMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(invalidEmailMessage, "Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void TC_03_Login_With_Invalid_Password() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();

		String InvalidPasswordMessage = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']"))
				.getText();
		Assert.assertEquals(InvalidPasswordMessage,
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_04_Login_With_Incorrect_Password() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");
		driver.findElement(By.id("send2")).click();

		String IncorrectPasswordMessage = driver.findElement(By.xpath("//div[@class='account-login']//li//span"))
				.getText();

		Assert.assertEquals(IncorrectPasswordMessage, "Invalid login or password.");

	}

	@Test
	public void TC_05_Login_With_Invalid_Emal_And_Password() {

		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("thuy@mailinator.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();

		String titleInfo = driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText();
		Assert.assertEquals(titleInfo, "MY DASHBOARD");

		String accountInfo = driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText();
		Assert.assertEquals(accountInfo, "Hello, Thuy Thanh Ng!");

		String contactInfo = driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']//p"))
				.getText();
		Assert.assertTrue(contactInfo.contains("Thuy Thanh Ng"));
		Assert.assertTrue(contactInfo.contains("thuy@mailinator.com"));

		driver.findElement(By.xpath("//span[@class='label' and text()= 'Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

	}

	@Test
	public void TC_06_Create_A_New_Account() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		String firstname = "Thuyqa";
		String lastname = "Madison";
		String email = "ThuyqaMadison" + randomNumber() + "@mailinator.com";

		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");

		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		String successRegisterMessage = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(successRegisterMessage, "Thank you for registering with Main Website Store.");
		
		String contactInfomation = driver.findElement(By.xpath("//div[@class='col-1']//div[@class='box-content']//p")).getText();
		Assert.assertTrue(contactInfomation.contains(firstname + " " + lastname ));
		Assert.assertTrue(contactInfomation.contains(email));
		
		driver.findElement(By.xpath("//span[@class='label' and text()= 'Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		
		Assert.assertTrue(driver.findElement(By.cssSelector("img[src$='logo.png']")).isDisplayed());
	
			
			
        
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	//@AfterClass
	//public void afterClass() {
		// driver.quit();
	}


