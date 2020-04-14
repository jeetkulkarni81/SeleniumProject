package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import framework.*;

public class Login {

	@FindBy(xpath="//input[@class=\"form-control\"]")
	WebElement we_user;
	
	//@FindBy(xpath="//input[@id=\"passwordInput\"]")
	@FindBy(xpath="//input[@type='password']")
	WebElement we_pwd;

	
	WebDriver driver;
	SeleniumUtility sh;
	
		
	public Login()
	{
		sh = new SeleniumUtility();
		driver = sh.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean isLogin(String url, String uid, String pwd) 
	{
		boolean isLogin=false;
		try 
		{
			driver.get(url);
			driver.findElement(By.xpath("//*[text()='Get started']")).click();
			driver.switchTo().frame("hrdIframe");
			sh.waitForPageLoad();
			
			we_user.sendKeys(uid);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			driver.switchTo().defaultContent();
			//isLogin=true;
			
			sh.WaitForElement(we_pwd);			
			we_pwd.sendKeys(pwd);
			//driver.findElement(By.xpath("//*[text()='LOGIN']")).click();
			driver.findElement(By.xpath("//span[@id='submitButton' and text()='Sign in']")).click();
			driver.findElement(By.xpath("//input[@id=\"idSIButton9\"]")).click();
			//driver.findElement(By.xpath("//input[@id=\"idSIButton9\"]")).click();
			
			isLogin=true;
		} 
		
		 catch (Exception ex) 
		{
		  ex.printStackTrace();
		}
		 
		return isLogin;
	}
	
}
