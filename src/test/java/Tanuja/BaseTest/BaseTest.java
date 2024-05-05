package Tanuja.BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Tanuja.EndToEnd.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage a; // to move it main @Test //Public so that variable can be used in another call
	//@BeforeTest
	public void initializeDriver() throws IOException {
		
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:/Users/tanrawat/Desktop/Selenium/EndToEnd/src/main/java/Tanuja/GlobalFile/GlobalData.properties");
		prop.load(fis);
		String browserName=prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "C:/Users/tanrawat/Desktop/Selenium/chromedriver-win64/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();	   	
		}
		
		else if (browserName.equals("Firefox")) {
			
			//FireFox		
		}
        else if (browserName.equals("Edge")) {
			
			//Edge
		}
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 driver.manage().window().maximize();
		 	 		 
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(FilePath),StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
		});
		return data;
		}
	
	public String screenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File("C:\\Users\\tanrawat\\Desktop\\Selenium\\EndToEnd\\src\\test\\screenshot\\"+testCaseName+".png");
		FileUtils.copyFile(source, target);
		return "C:\\Users\\tanrawat\\Desktop\\Selenium\\EndToEnd\\src\\test\\screenshot\\"+testCaseName+".png";
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public void login() throws IOException {
		initializeDriver();
		a = new LandingPage(driver);
		a.goTo();	
	}

	@AfterMethod(alwaysRun=true)
	public void exit() {
		driver.close();
	}
}
