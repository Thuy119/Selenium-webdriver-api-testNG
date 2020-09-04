package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class topic01_check_enviroment_console {
  static WebDriver driver;
	
	
	public static void main(String[] args) {
		System.out.print("Selenium Automation Test");
		
		driver  = new FirefoxDriver();
		
		driver.get("https://www.google.com/");
	}

}
