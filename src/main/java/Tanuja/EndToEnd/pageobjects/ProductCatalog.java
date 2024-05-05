package Tanuja.EndToEnd.pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Tanuja.EndToEnd.Abstract;

public class ProductCatalog extends Abstract{
	
    WebDriver driver;
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath = "//h5")
	List<WebElement> items; 
	
	@FindBy(css=".btn.w-10.rounded")
	List<WebElement> addToCartButton;
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement clickCart;
	
	

	public List<WebElement> ProductCatalogPage() {
		return items;
	}
	
	public List<String> productToBeSelected(String P1,String P2) {
		String list[]={P1,P2};
		List<String> al = Arrays.asList(list);
		return al;
	}
	
	public void addProductToCart(String P1, String P2) throws InterruptedException {
		for(WebElement item:ProductCatalogPage()){
			String[] list1=item.getText().split(" ");
			String newList = list1[0].trim();
			if(productToBeSelected(P1, P2).contains(newList)) {
				int index=items.indexOf(item);
				Thread.sleep(3000);
				addToCartButton.get(index).click();
			}
		}		
	}
		
	public MyCartPage clickcart() {
		clickCart.click();
		return new MyCartPage(driver);
	}
	
	
}
