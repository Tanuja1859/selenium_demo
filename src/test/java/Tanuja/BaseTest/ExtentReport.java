package Tanuja.BaseTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"//Reports"+"//index.html";
		ExtentSparkReporter sr = new ExtentSparkReporter(path);
		sr.config().setReportName("Report");
		sr.config().setDocumentTitle("Execution Report");
		ExtentReports er = new ExtentReports();
		er.attachReporter(sr);
		er.setSystemInfo("Tester", "Tanuja");
		//ExtentTest test=er.createTest(path);
		return er;
		
	}

}
