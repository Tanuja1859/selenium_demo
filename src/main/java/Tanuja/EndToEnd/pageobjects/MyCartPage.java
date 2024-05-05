package Tanuja.EndToEnd.pageobjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Tanuja.EndToEnd.Abstract;

public class MyCartPage extends Abstract{
	
	WebDriver driver;
	
	public MyCartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div /div /h3")
	List<WebElement> cartElements;
	
	@FindBy(xpath="//div /ul /li[3]/button")
	WebElement checkout;

   public List<WebElement> myCartElements() {
	   return cartElements;
	   
   }
   
   public void myCart(String P1, String P2) {
	   for (WebElement itemCart:cartElements) {	
		   String[] list1=itemCart.getText().split(" ");
			String finalItems = list1[0].trim();
			ProductCatalog pc = new ProductCatalog(driver);
			boolean result=pc.productToBeSelected(P1, P2).contains(finalItems);					
			Assert.assertTrue(result);
			
		} 
   }
	public void scroll() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
	    executor.executeScript("window.scrollBy(0,500)");	   
   }
	
	public PaymentPage checkout() {
		checkout.click();
		return new PaymentPage(driver);
	}
   
	
}
