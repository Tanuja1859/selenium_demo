package Tanuja.EndToEnd;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EcommProject {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:/Users/tanrawat/Desktop/Selenium/chromedriver-win64/chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(3));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("rawattanuja01@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("New12345");
		driver.findElement(By.id("login")).click();
		String list[]={"ADIDAS","IPHONE"};
		List<String> al = Arrays.asList(list);
		List<WebElement> items=driver.findElements(By.xpath("//h5"));	
		for(WebElement item:items){
			String newList=newItems(item);
			
			if(al.contains(newList)) {
				int index=items.indexOf(item);
				Thread.sleep(3000);
				driver.findElements(By.cssSelector(".btn.w-10.rounded")).get(index).click();		
			}
		}
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
	    //items.stream().map(s->s.getText()).forEach(s->System.out.println(s));
	    //items.stream().map(s->s.getText()).filter(s->s.contains("ADIDAS")&& s.contains("IPHONE")).collect(Collectors.toList());
		
		List<WebElement> itemsCart=driver.findElements(By.xpath("//div /div /h3"));	
		for (WebElement itemCart:itemsCart) {	
			String finalItems=newItems(itemCart);
			boolean result=al.contains(finalItems);					
			Assert.assertTrue(result);
			
		} 
		//WebElement ob=driver.findElement(By.cssSelector(".totalRow button"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
	    executor.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div /ul /li[3]/button")).click();
		
		WebElement dropdown = driver.findElement(By.xpath("//div /select[1]"));
		Select ab = new Select(dropdown);
		ab.selectByVisibleText("04");
		
		Actions dp = new Actions(driver);
		dp.sendKeys(driver.findElement(By.cssSelector("input[placeholder = 'Select Country']")), "Ind").build().perform();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group.ng-star-inserted")));
		List<WebElement> dpList=driver.findElements(By.cssSelector(".ta-results.list-group.ng-star-inserted"));
		for (WebElement dpListNew : dpList) {
			if(dpListNew.getText().equalsIgnoreCase("India"));
			dpListNew.click();
		}
		
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		
		String sub=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(sub.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	    
	public static String newItems(WebElement item) {
		
		String newItems1[] = item.getText().split(" ");
		String newItems = newItems1[0].trim();
		return newItems;
	}
	}

