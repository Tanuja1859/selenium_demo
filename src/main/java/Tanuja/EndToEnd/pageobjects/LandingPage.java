package Tanuja.EndToEnd.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Tanuja.EndToEnd.Abstract;

public class LandingPage extends Abstract {
	
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // go and initialize all the webelements
	}
	
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id ="userPassword")
	WebElement userpassword;
	
	@FindBy(id ="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalog loginpage(String email,String password ) {
		userEmail.sendKeys(email);
		userpassword.sendKeys(password);
		login.click();
		return new ProductCatalog(driver);
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage() {
		Abstract abw =new Abstract(driver);
		abw.explicitwait(By.cssSelector("[class*='flyInOut']"));
		String message = errorMessage.getText();
		System.out.println(message);
	    return message;
	
	}
	
	//By products = By.cssSelector(".mb3");
}
	
