package Tanuja.EndToEnd.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class OrderPage{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		this.driver=driver;	
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement orders;
	
	@FindBy(xpath="//tbody/tr/td[2]")
	List<WebElement> products;
	
     public void orders() {
    	 
    	 orders.click();
    	 String list[]={"ADIDAS ORIGINAL","IPHONE 13 PRO"};
		 boolean adidas=false; 
		 boolean iphone=false; 
	   	 for (WebElement prod : products)
	   	 {    
	   		 String product = prod.getText();
	   		 for (String newPG: list) {
	   			 if (newPG.equals(product)) 
	   				 //count = count+1;
	   				if(newPG=="ADIDAS ORIGINAL") {
	   					System.out.println(newPG+"is present");
	   					adidas=true;
	   				}
	   				else if(newPG=="IPHONE 13 PRO") {
	   					System.out.println(newPG+"is present");
	   					iphone=true;
	   				}   	
	   			 } 
	   			 
	   		 }
	   		 
	   if(iphone && adidas) {
		   System.out.println("All items are present");
	   }	
	   else {
		   System.out.println("All items are not present");
	   }
    	 }  
}


