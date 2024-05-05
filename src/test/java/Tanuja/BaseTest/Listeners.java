package Tanuja.BaseTest;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class Listeners extends BaseTest implements ITestListener{
	//implements --> is for interface
	//ITestListener will always listen your test results 
	ExtentTest test;
	ExtentReports er =ExtentReport.getReportObject();
	ThreadLocal<ExtentTest> t1=new ThreadLocal<ExtentTest>(); // <> tells you what king of variable we are storing in threadlocal
	@Override
	public void onTestStart(ITestResult result) {
		test=er.createTest(result.getMethod().getMethodName());
		t1.set(test); //unique thread id
	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//test.log(Status.PASS, "Test Passed");
		t1.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		    t1.get().fail(result.getThrowable());
		    try {
				driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String sr = null;
			try {
				sr = screenshot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    t1.get().addScreenCaptureFromPath(sr, result.getMethod().getMethodName() );
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		er.flush();
	}


}
