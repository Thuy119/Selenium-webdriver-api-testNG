package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic07_Default_Dropdown {
	WebDriver driver;
	//add thu vien select cua selenium
	Select select;
	String firstName, lastName, email, companyName, password, confirmPassword;
	String date, month, year;
	
	By genderFemaleBy = By.id("gender-female");
	By firstNameBy = By.id("FirstName");
	By lastNameBy = By.id("LastName");
	By emailBy = By.id("Email");
	By comapanyBy = By.id("Company");
	By dateBy= By.name("DateOfBirthDay");
	By monthBy = By.name("DateOfBirthMonth");
	By yearBy = By.name("DateOfBirthYear");
	
	
	
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
		
		firstName ="Thuy"; 
		lastName ="Nguyen";  
		email = generateEmail();
		companyName ="QA";
		password ="123123";
		confirmPassword ="123123";
		date = "3";
		month = "April";
		year = "1914";
		
		
	}

	@Test
	public void TC_01_Register() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.className("ico-register")).click();
		driver.findElement(genderFemaleBy).click();
		driver.findElement(firstNameBy).sendKeys(firstName);
		driver.findElement(lastNameBy).sendKeys(lastName);
		
		//khoi tao, truyen vao 1 web element
		select = new Select(driver.findElement(dateBy));
		
		//Verify "date of birth" is single dropdown
		Assert.assertFalse(select.isMultiple());
		
		//Chon ngay 10
		select.selectByVisibleText(date);
		
		
		//verify item selected
		//lay ra item vua select: select.getFirstSelectedOption().getText(); 
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		
		//verify all items trong dropdown = ?
		//lay ra all items: select.getOptions().size();
		Assert.assertEquals(select.getOptions().size(), 32);
		
		
		select = new Select(driver.findElement(monthBy));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		Assert.assertEquals(select.getOptions().size(), 13);
		
		select = new Select(driver.findElement(yearBy));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		Assert.assertEquals(select.getOptions().size(), 112);
		
		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(comapanyBy).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmPassword);
		driver.findElement(By.id("register-button")).click();
		
		
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
		//Navigate to my account link
		driver.findElement(By.className("ico-account")).click();
		
		
		Assert.assertTrue(driver.findElement(genderFemaleBy).isSelected());
		
		Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(comapanyBy).getAttribute("value"), companyName);
		
		
		select = new Select(driver.findElement(dateBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		
		select = new Select(driver.findElement(monthBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		
		select = new Select(driver.findElement(yearBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
	
	}

	
	@Test
	public void TC_02_Multiple() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.id("job2")));
		select.selectByVisibleText("Manual");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Security");
		select.selectByVisibleText("Functional UI");
		
		
		List<WebElement>itemSelected = select.getAllSelectedOptions();
		
		//Java collection
		List<String> itemSelectedText = new ArrayList<String>();
		
		
		//Verify 4 item da dc Selected
		Assert.assertEquals(itemSelected.size(), 4);
		
		for (WebElement item: itemSelected) {
			itemSelectedText.add(item.getText());
			System.out.println(item.getText());
		}
		
		
		
	}

	@Test
	public void TC_03 () {

		
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
