package Tanuja.EndToEnd;

import java.io.IOException;
import org.junit.Assert;
import org.testng.annotations.Test;
import Tanuja.BaseTest.BaseTest;
import Tanuja.BaseTest.Retry;

public class ErrorValidation extends BaseTest {

	@Test(groups= {"Regression"}, retryAnalyzer=Retry.class)
	public void loginerror() throws InterruptedException, IOException {
		
		a.loginpage("rawattanuja01@gmail.com", "New1345");
		String message=a.getErrorMessage();
		//Assert.assertEquals("Incorrect email or password.",message );
		Assert.assertEquals("Incorrect email password.",message );
		
	}
	
	@Test(enabled=false)
	public void producterror() throws InterruptedException, IOException {
		System.out.println("Error");
		
	}
	    
	}

