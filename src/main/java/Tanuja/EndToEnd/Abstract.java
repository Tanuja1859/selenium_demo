package Tanuja.EndToEnd;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Abstract {

	WebDriver driver;
	public Abstract(WebDriver driver) {
		this.driver=driver;
	}
   
	
	
	public void explicitwait(By findby) {
		
		WebDriverWait w =new WebDriverWait(driver, Duration.ofSeconds(3));
		w.until(ExpectedConditions.visibilityOfElementLocated(findby));	

	}
	
}

