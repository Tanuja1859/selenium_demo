package Tanuja.EndToEnd;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Tanuja.BaseTest.BaseTest;
import Tanuja.EndToEnd.pageobjects.MyCartPage;
import Tanuja.EndToEnd.pageobjects.OrderPage;
import Tanuja.EndToEnd.pageobjects.PaymentPage;
import Tanuja.EndToEnd.pageobjects.ProductCatalog;

public class EcommProjectTest extends BaseTest {

	@Test(dataProvider = "getdata", groups = {"New"})
	//public void execution(String Email, String Password, String P1, String P2) throws InterruptedException, IOException {
	public void execution(HashMap<String, String> map) throws InterruptedException, IOException {
		
		//initializeDriver();//return value or add beforetest annotation
		//login(); have pulled initializeDriver() in login and marked the annotation as @BeforeTest
		ProductCatalog pc=a.loginpage(map.get("Email"), map.get("Password"));	
		/*instead of creating object everytime. create the object in the last page itself
		ProductCatalog pc=new ProductCatalog(driver);*/
		pc.productToBeSelected(map.get("Product"),map.get("Product1"));
		pc.ProductCatalogPage();
		pc.addProductToCart(map.get("Product"),map.get("Product1")); 
		Abstract abw =new Abstract(driver);
		abw.explicitwait(By.id("toast-container"));
		MyCartPage mc=pc.clickcart();
		mc.myCartElements();
		mc.myCart("ADIDAS","IPHONE");
		mc.scroll();
		Thread.sleep(2000);
		PaymentPage pp=mc.checkout();
		pp.date();
		pp.countyInput("Ind");
		abw.explicitwait(By.cssSelector(".ta-results.list-group.ng-star-inserted"));
		pp.countrySelection();
		pp.Submit();
		String message=pp.ConfirmationMessage(); 
		//No Assertions should be Page Objects. Assertions tells the result so should be there in main class
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"execution"})
	public void orderListTest() {
		a.loginpage("rawattanuja01@gmail.com", "New12345");	
		OrderPage op=new OrderPage(driver);
		op.orders();
	}
	    

    @DataProvider
    //public Object[][] getdata() {
    //return new Object[][] {{"rawattanuja01@gmail.com", "New12345","ADIDAS","IPHONE"}, {"suchitshahbly@gmail.com","New@123456","ADIDAS","IPHONE"}};
    public Object[][] getdata() throws IOException {	
//    HashMap<String,String> map1 = new HashMap<String,String>();
//    	 map1.put("Email", "rawattanuja01@gmail.com");
//    	 map1.put("Password", "New12345");
//    	 map1.put("Product", "ADIDAS");
//    	 map1.put("Product1", "IPHONE");
//    	 
//    HashMap<String,String> map2 = new HashMap<String,String>();
//    	 map2.put("Email", "suchitshahbly@gmail.com");
//    	 map2.put("Password", "New@123456");
//    	 map2.put("Product", "ADIDAS");
//    	 map2.put("Product1", "IPHONE");
    	List<HashMap<String,String>> data =getJsonDataToMap("C:\\Users\\tanrawat\\Desktop\\Selenium\\EndToEnd\\src\\test\\java\\Tanuja\\BaseTest\\DataFile.json"); 
    	return new Object[][] {{data.get(0)},{data.get(1)}};  	 	   	
    }
    
}



