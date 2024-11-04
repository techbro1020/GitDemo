package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject()
	{
		String path =System.getProperty("user.dir")+"\\reports\\index.html";//creates path for index.html within reports folder in the current project folder
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");//sets name of report within report as "Web Automation Results" at top rt of report
		reporter.config().setDocumentTitle("Test Results");//set the tab name to "Test Results"
		
		ExtentReports extent = new ExtentReports();//creates obj of ExtentReports() class
		extent.attachReporter(reporter); //attaches the obj of ExtentSparkReporter() class to edit the html file created
		extent.setSystemInfo("Tester", "Rahul More");//this method provides info to the file... eg: here it says that the tester is Rahul More visible on dashboard
		
		return extent;//returns extent which will be used in Listeners class 
	}
}
