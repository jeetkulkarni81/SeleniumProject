package framework;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumUtility {
	
	private static WebDriver driver=null;
	
	ConfigProperty objconf = ConfigProperty.getInstance();
	
	public WebDriver getDriver()
	{
		if(driver==null)
		{
			switch (objconf.getBrowserName()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
				
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
				
			case "ie":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;

			default:
				break;
			}
		}			
		driver.manage().timeouts().implicitlyWait(objconf.ImplicitWaitTime, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	return driver;
	}
	
	public boolean WaitForElement(WebElement we)
	{
		boolean isClickable = false;
		WebDriverWait wt = new WebDriverWait(driver, objconf.ExplicitWaitTime);
		try 
		{
			wt.until(ExpectedConditions.elementToBeClickable(we));
			isClickable = true;
			/*
			wt.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver d)
				{
					if(we.isDisplayed())
						return true;
					return false;
				}
			});*/
		} 
		catch (Exception e) 
		{
			isClickable = false;
			e.printStackTrace();
		}
		return isClickable;
	}
	
	
	public boolean IsWebElementExists(WebElement we)
	{
		boolean isExists=false;
		try 
		{
			if(we.isDisplayed())
				isExists=true;
		} 
		catch (Exception e) 
		{
			isExists=false;
		}
		return isExists;
	}
	
	public boolean waitForPageLoad()
	{
		boolean isLoaded = false;
		try 
		{
			WebDriverWait wt = new WebDriverWait(driver, objconf.ExplicitWaitTime);
			wt.until(ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";"));
			System.out.println("Page loaded successfully");
			isLoaded = true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			isLoaded = false;
		}
		
		return isLoaded;
	}
	
	

}
