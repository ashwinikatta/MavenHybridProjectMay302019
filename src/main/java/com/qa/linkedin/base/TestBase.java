package com.qa.linkedin.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.linkedin.util.ListenerHelper;
import com.qa.linkedin.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestBase {
	
	public static WebDriver driver=null;
	protected static WebDriverWait wait=null;
	public static Properties prop=null;
	
	public TestBase() throws IOException {
		//create an object for Properties class
		
		prop=new Properties();
		
		//How to read the data from config properties file
		try {
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\linkedin\\config\\config.properties");
			//load all the properties
			prop.load(fis);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void initWebdriver() throws IOException {
		//fetch the browser name
		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();	
		}else if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			
		}else if(browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		}
		
		//add implicitwait
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		//maximize the window
		driver.manage().window().maximize();
		//open the url
		driver.get(prop.getProperty("url"));
		
		//create object for WebDriverWait class
		wait=new WebDriverWait(driver,TestUtil.EXPLICIT_WAIT);
		//create object for FluentWait class
		FluentWait<T> fw=new FluentWait(driver);
		fw.withTimeout(60, TimeUnit.SECONDS);
		fw.pollingEvery(5, TimeUnit.SECONDS);
		
		//create object for ListenerHelper class
		ListenerHelper lh=new ListenerHelper();
		
		//create object for EventFiringWebDriver class
		EventFiringWebDriver evnt=new EventFiringWebDriver(driver);
		//register the events
		evnt.register(lh);
		
		driver=evnt;
	}

}
