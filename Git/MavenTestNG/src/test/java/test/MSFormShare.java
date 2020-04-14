package test;

import org.testng.annotations.Test;

import framework.SeleniumUtility;
import pages.NewForm;

import org.testng.annotations.BeforeClass;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class MSFormShare 
{
	WebDriver driver;
	SeleniumUtility sh;
	NewForm objnewForm;
	
  @Test(priority = 3,groups = {"FormShare"}, dependsOnGroups = {"FormCreation"})
  public void getTemplateLink() 
  {
	  String strShareTemplateLink = "";
	  Map<Boolean, String> mapData = objnewForm.GetShareTemplateLink();
	  if(mapData.containsKey(true)==true)
	  {
		  System.out.println("Inside if for value");
		  strShareTemplateLink = mapData.get(true);
		  //System.out.println("Share Template Link is -> "+strShareTemplateLink);
	  }
	  Assert.assertEquals(mapData.containsKey(true), true);
	  Reporter.log("The share Template Link is -> "+strShareTemplateLink);
	  
  }
  
  @Test(priority = 4, dependsOnMethods = {"getTemplateLink"},groups = {"FormShare"})
  public void getCollaborateLink()
  {
	  String strShareCollaborateLink = "";
	  Map<Boolean, String> mapData = objnewForm.GetShareCollaborateLink();
	  if(mapData.containsKey(true)==true)
	  {
		  System.out.println("Inside if for value");
		  strShareCollaborateLink = mapData.get(true);
		  //System.out.println("Share Template Link is -> "+strShareTemplateLink);
	  }
	  Assert.assertEquals(mapData.containsKey(true), true);
	  Reporter.log("The share Collaborate Link is -> "+strShareCollaborateLink);
  }
  @BeforeClass
  public void beforeClass() 
  {
	sh = new SeleniumUtility();
	driver = sh.getDriver();
	objnewForm = new NewForm();
  }

  @AfterClass
  public void afterClass() 
  {
	  try 
		{
			Thread.sleep(5000);
			driver.quit();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}  
  }

}
