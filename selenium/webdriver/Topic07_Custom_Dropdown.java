package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic07_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait expliciWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		expliciWait = new WebDriverWait(driver, 30);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Jquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		// 5
		// specific
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		sleepInSencond(2);
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"5");
		// 10
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
		sleepInSencond(2);
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"10");

		// 15
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "15");
		sleepInSencond(2);
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"15");

	}

	// Step 1: click vao 1 element bat ky cua dropdown de no so het all item ra
	// Step2: cho cho all item dc load len UI
	// Step 3: luu no lai vao 1 list chua all element
	// Step 4: lay ra text cua tung element
	// Step 5: kiem tra no co bang voi text can tim ko
	// Step 6: neu Co click vao, thoat khoi vong lap. Neu Ko co, tiep tuc duyet
	// nhung item khac
	// cho den khi het all items

	// Common
	public void selectItemInCustomDropdown(String parentXpath, String allItemXpath, String expectedValueItem) {
		// 1: click vao 1 element bat ky cua dropdown de no so het all item ra

		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSencond(2);

		// 2- chờ cho all các item dc load ra

		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));

		// 3: luu no lai vao 1 list chua all element
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		int allItemsNumber = allItems.size();
		// 19 items

		// Luu tru du lieu: (Data structure)
		// Array/List/Set/Queue-> index
		// 0-1-2-3

		// 4: Duyet qua tung element & lay ra text cua tung element
		// Tradition for
		for (int i = 0; i < allItemsNumber; i++) {
			String actualValueItem = allItems.get(i).getText();
			System.out.println(actualValueItem);

			// 5: kiem tra no co bang voi text can tim ko
			if (actualValueItem.equals(expectedValueItem)) {

				// 6: neu Co click vao, thoat khoi vong lap.
				allItems.get(i).click();
				break;
				// 7: Neu Ko co, tiep tuc duyet nhung item khac

			}
		}

		/*
		 * //For-Each for (WebElement item : allItems) { String actualValueItem =
		 * item.getText(); if (actualValueItem.equals(expectedValueItem)) {
		 * item.click(); break;
		 * 
		 * 
		 * }
		 */

	}

	// Lần chạy 1:
	// int i=0
	// i <19
	// text = 1
	// i = i+1 = 0+1=1

	// Lần chạy 2:
	// int i=1
	// i <19
	// text = 2
	// i = i+1 = 1+1=2

	// Lần chạy 19:
	// int i=18
	// i <19
	// text = 19
	// i =19

	// Lần chạy 20:
	// int i=19
	// i <19

	public void sleepInSencond(long timeInSenconds) {
		try {
			Thread.sleep(timeInSenconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
