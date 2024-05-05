package Tanuja.EndToEnd.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Tanuja.EndToEnd.Abstract;

public class PaymentPage extends Abstract {
	
	WebDriver driver;
	public PaymentPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div /select[1]")
	WebElement dropdown;
	
	@FindBy(css= "input[placeholder = 'Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-results.list-group.ng-star-inserted")
	List<WebElement> dpList;
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement placeOrder;
	
	@FindBy(css=".hero-primary")
	WebElement confirmation;
	
	
	
	public void date() {
		Select ab = new Select(dropdown);
		ab.selectByVisibleText("04");
	}
	
	public void countyInput(String Con) {
		Actions dp = new Actions(driver);
		dp.sendKeys(country, Con).build().perform();	
	}
	
	public void countrySelection() {
		for (WebElement dpListNew : dpList) {
			if(dpListNew.getText().equalsIgnoreCase("India"));
			dpListNew.click();
		}
		
	}
	
	public void Submit() {
		placeOrder.click();
	}
	
	//No Assertions should be in page objects
	public String ConfirmationMessage() {   
		 String message = confirmation.getText();
		 return message;
		}

}
