package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic06_Textbox_TextArea {
	// Khai bao bien: Bien toan cau (global variable)
	WebDriver driver;
	String email, userID, password, loginPageURL, customerID;
	String name, dobInput, dobOutput, address, city, state, pin, phone ;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");

		//khoi tao du lieu(data test)
		name ="Trump";  
		dobInput="08/06/1980";
		dobOutput="1980-08-06";
		address="PH";
		city="Da Nang";
		state="Hoa Vang";
		pin="978119";
		phone="0986678119";
		email = generateEmail();
		
		
		
		loginPageURL = driver.getCurrentUrl();
		
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();
		// Bien cuc bo (local variable)
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get(loginPageURL);
		driver.findElement(By.name("uid")).sendKeys("mngr288633");
		driver.findElement(By.name("password")).sendKeys("yheqyhA");
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");

	}

	@Test
	public void TC_03_New_Customer() throws InterruptedException {
		//NEW
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.id("dob")).sendKeys(dobInput);
		driver.findElement(By.name("addr")).sendKeys(address);
		driver.findElement(By.name("cityfff")).sendKeys(city);
		driver.findElement(By.name("state")).sendKeys(state);
		driver.findElement(By.name("pinno")).sendKeys(pin);
		driver.findElement(By.name("telephoneno")).sendKeys(phone);
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.name("sub")).click();
		
		
		Thread.sleep(1000);
		
		//Verify
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),
				"Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),name );
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dobOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		
	}
	
	

	@Test
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		//Verify value tai Edit Customer field matching with value at New customer
		Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(By.name("dob")), dobOutput);
		Assert.assertEquals(driver.findElement(By.name("addr")).getText(), address);
		
		
		
		
		

		
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return "thuynguyen" + rand.nextInt(9999) + "@gmail.com";
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
