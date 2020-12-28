package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic07_PracticeAgain_DefaultDropdown {
	WebDriver driver;
	String projectFolder = System.getProperty("user.dir");
	Select select;
	String email, firstName, lastName, companyName, password, confirmPassword;
	String date, month, year;

	By genderBy = By.id("gender-female");
	By firstNameBy = By.id("FirstName");
	By lastNameBy = By.id("LastName");
	By dateBy = By.name("DateOfBirthDay");
	By monthBy = By.name("DateOfBirthMonth");
	By yearBy = By.name("DateOfBirthYear");
	By emailBy = By.id("Email");
	By companyBy = By.id("Company");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		email = generateEmail();
		firstName = "Thuy";
		lastName = "Kute";
		companyName = "qa";
		password = "123456";
		confirmPassword = "123456";
		date = "1";
		month = "May";
		year = "1990";
		
	}

	@Test
	public void TC_01() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.className("ico-register")).click();

		driver.findElement(genderBy).click();
		driver.findElement(firstNameBy).sendKeys(firstName);
		driver.findElement(lastNameBy).sendKeys(lastName);
		// khoi tao truyen vao 1 web element
		select = new Select(driver.findElement(dateBy));
		select.selectByVisibleText(date);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);

		// kiem tra dropdown co 32 item:
		Assert.assertEquals(select.getOptions().size(), 32);

		select = new Select(driver.findElement(monthBy));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		// kiem tra dropdown co 13 item

		Assert.assertEquals(select.getOptions().size(), 13);

		select = new Select(driver.findElement(yearBy));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		Assert.assertEquals(select.getOptions().size(), 112);

		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(companyBy).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmPassword);
		driver.findElement(By.id("register-button")).click();

		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");

		driver.findElement(By.className("ico-account")).click();

		Assert.assertTrue(driver.findElement(genderBy).isSelected());
		Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"), lastName);

		select = new Select(driver.findElement(dateBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);

		select = new Select(driver.findElement(monthBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		select = new Select(driver.findElement(yearBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(companyBy).getAttribute("value"), companyName);

	}

	@Test
	public void TC_02_multipleDropdown() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		List<String> itemText = new ArrayList<String>();
		itemText.add("Automation");
		itemText.add("Mobile");
		itemText.add("Security");
		itemText.add("Functional UI");

		select = new Select(driver.findElement(By.name("user_job2")));

		select.selectByVisibleText("Automation");
		Thread.sleep(2000);
		select.selectByVisibleText("Mobile");
		Thread.sleep(2000);
		select.selectByVisibleText("Security");
		Thread.sleep(2000);
		select.selectByVisibleText("Functional UI");
		Thread.sleep(2000);

		List<WebElement> itemSelected = select.getAllSelectedOptions();
		// define ra 1 ArrayList (java collection)
		List<String> itemSelectText = new ArrayList<String>();

		// Verify 4 items da dc Selected
		Assert.assertEquals(itemSelected.size(), 4);

		for (WebElement item : itemSelected) {
			itemSelectText.add(item.getText());
			System.out.println(item.getText());

		}

		Assert.assertTrue(itemSelectText.contains("Automation"));
		Assert.assertTrue(itemSelectText.contains("Mobile"));
		Assert.assertTrue(itemSelectText.contains("Security"));
		Assert.assertTrue(itemSelectText.contains("Functional UI"));

		Assert.assertEquals(itemText, itemSelectText);
	}

	@Test
	public void TC_03_Run_On_Chrome() {
		System.setProperty("webdriver.chrome.driver", projectFolder + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		
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
