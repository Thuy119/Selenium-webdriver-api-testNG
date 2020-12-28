package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic16_Wait_Part2 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		// Timeout nay dc apply duy nhat cho viec tim element (findElement, findElements)
		//Neu nhu ko set timeout = 0
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
	}

	@Test
	public void TC_01_Find_Element() {
		//Tra ve (return) 1 element (WebElement)
		
		//Tim element nhung ko thaY element nao het ->  0 matching node
		//Moi 0.5 s se tim lai 1 lan cho den khi het timeout
		//Neu nhu het timeout mÃ  van k tim thay element thi:
		// + Danh fail TC
		// + Throw (nem ra) 1 exception: NoSuchElementException
		// Trong tg dang tim neu element xuat hien thi pass dc dieu kien(findElement)
		//& ko can phai cho cho het timeout
		
		//driver.findElement(By.xpath("//input[@id='FirstName']"));
		
		
		
		// Tim element nhung chi co duy nhat 1 cai -> 1 matching node
		driver.findElement(By.xpath("//input[@id='Email']"));
		
		// Tim thay Element thay ngay 
		//ko can cho het timeout con lai
		
		
		// Tim element nhung thay nhieu hon 1 cai -> n matching node
		//Tim thay element thay ngay -> return lai element dau tien
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("test@gmail.com");
	}

	
	@Test
	
	public void TC_02_Find_Elements() {
		//Tra ve (return) >=1 elements (List<WebElement>)
		
		
		//Tim element nhung ko thay element nao het ->  0 matching node
		//Moi 0.5 s se tim lai 1 lan cho den khi het timeout
		//Neu nhu het timeout ma  van k tim thay element thi:
		
		// + KO danh fail TC
		// + Tra ve 1 list empty ko chua element nao het
		// Trong tg dang tim neu element xuat hien thi pass dc dieu kien (findElements)
		//& ko can phai cho cho het timeout	
		
		List<WebElement> radioButton =	driver.findElements(By.xpath("//input[@type='radio']"));
		System.out.println("Size= " + radioButton.size());
		
		Assert.assertTrue(radioButton.isEmpty());
		
		
		
		
		// Tim element nhung chi co duy nhat 1 cai -> 1 matching node
		//Tim thay element nhung ket qua tra ve la 1 list chua element (1 element)
		List<WebElement> emailTextbox =	driver.findElements(By.xpath("//input[@type='Email']"));
		Assert.assertFalse(emailTextbox.isEmpty());
				
		emailTextbox.get(0).sendKeys("automation@gmail.com");
		
		// Tim element nhung thay nhieu hon 1 cai -> n matching nodes
		// Can thao tac voi nhieu fields/elements:
		////Tim thay element nhung ket qua tra ve la 1 list chua nhieu element
		List<WebElement> Textboxs =	driver.findElements(By.xpath("//input[@type='text']"));
		System.out.println("Size= " + Textboxs.size());
		
		for (WebElement textbox : Textboxs) {
			textbox.sendKeys("List WebElement");
			
		}
		
	}
	

	

	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	

}
