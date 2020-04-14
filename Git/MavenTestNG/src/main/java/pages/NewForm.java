package pages;

import java.util.*;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;


import framework.SeleniumUtility;

public class NewForm 
{
	@FindBy(xpath="//*[@aria-label='Form title']")
	WebElement form_title;
	
	@FindBy(xpath="//*[@aria-label='Form subtitle']")
	WebElement form_desc;
	
	@FindBy(xpath = "//button[@aria-label='Add new']")
	WebElement we_AddNew;
	
	@FindBy(xpath = "//div[@title='Section' and @class='menu-item-active']//following::span[text()='Section']")
	WebElement we_section;
	
	@FindBy(xpath = "//textarea[@aria-label='Section title']")
	WebElement we_SecTitle;
	
	@FindBy(xpath = "//textarea[@aria-label='Section subtitle']")
	WebElement we_SecSubTitle;
	
	@FindBy(xpath="//*[@aria-label='Question title']")
	WebElement we_quest;
	
	@FindBy(xpath="//div[@aria-label='Multiple answers']")
	WebElement we_MultiAns;
	
	@FindBy(xpath = "//span[text()='Add option']")
	WebElement we_AddOption;
	
	@FindBy(xpath = "//span[text()='Add \"Other\" option']")
	WebElement we_AddOtherOpt;
	
	@FindBy(xpath = "//div[@class='design-question-bottombar-element menu-control-dropup']/button[@aria-label='More options']")
	WebElement we_MoreOptions;
	
	@FindBy(xpath = "//li/span[text()='Drop-down']")
	WebElement we_dropdown;
	
	@FindBy(xpath="//div[@aria-label='Long answer']")
	WebElement we_LongAns;
	
	@FindBy(xpath="//div[@aria-label='Required']")
	WebElement we_Required;
	
	@FindBy(xpath="//i[@title='More']")
	WebElement we_more;
	
	@FindBy(xpath = "//div[@class='headbar-button-container']//following::span[text()='Preview']")
	WebElement we_preview;
	
	@FindBy(xpath = "//div[@class='question-setting-dropdown']/div[@aria-label='File number limit: ']//following::i[@role='presentation' and @class='ms-Icon ms-Icon--ChevronDown select-placeholder-arrow forms-icon-size14x14']")
	WebElement we_filecount;
	
	@FindBy(xpath = "//div[@class='question-setting-dropdown']/div[@aria-label='Single file size limit: ']//following::i[@role='presentation' and @class='ms-Icon ms-Icon--ChevronDown select-placeholder-arrow forms-icon-size14x14']")
	WebElement we_filesize;
	
	@FindBy(xpath = "//div[contains(text(),'Responders will be able to upload their files to this folder.')]")
	WebElement we_FileuploadWarning;
	
	
	WebDriver driver;
	SeleniumUtility sh = new SeleniumUtility();
	
	public NewForm()
	{
		driver = sh.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method adds Title and Description for Microsoft page.
	 * @param strTitle This is used for adding Form Title.
	 * @param strDesc This is used for adding Form Description.
	 * @return 
	 * */
	public boolean formTitleDesc(String strTitle, String strDesc)
	{
		boolean isTyped=false;
		try 
		{
			form_title.sendKeys(strTitle);
			form_desc.sendKeys(strDesc);
			isTyped=true;
		} 
		catch (Exception e) 
		{
			isTyped=false;
			e.printStackTrace();
			throw e;
		}
		return isTyped;
	}

	/**
	 * This method is use to click "Add New" button for adding new question.
	 * @param Choice
	 * @param Text
	 * @param Date
	 * @param Rating
	 * @return 
	 * */
	public boolean AddNewQuest(String optionType, String strRequired, String strMultiAns, String strLongAns, String strAddOtheropt,String strDropDown, String strfilecount, String strfilesize)
	{
		boolean isClicked=false;
		try 
		{
			//WebElement we_AddNew = driver.findElement(By.xpath("//button[@aria-label='Add new']"));
			if(sh.WaitForElement(we_AddNew)==false)
				System.out.println("Disabled");
			we_AddNew.click();			
			String strxpath="";
			strxpath="//div[@class='insertPanel']/button[@title='"+optionType+"']";
			//strxpath="//span[text()='"+optionType+"']";
			/*if(optionType.equalsIgnoreCase("likert"))
				strxpath="//span[@class='design-insert-card-dropdown-text' and text()='"+optionType+"']";*/
			if(optionType.equalsIgnoreCase("likert") || optionType.equalsIgnoreCase("file upload") || optionType.contains("Net Promoter") || optionType.equalsIgnoreCase("section"))
			{
				we_more.click();
				strxpath ="//div[@class='menu-control-options-box design-insert-card-more-menu']//following::div[@role='menuitem' and @title='"+optionType+"']";
			}
				
			driver.findElement(By.xpath(strxpath)).click();
			
			
			
			switch (optionType) {
			case "Choice":
				if(strAddOtheropt.equalsIgnoreCase("yes"))
				{
					we_AddOtherOpt.click();
				}
				
				if(strDropDown.equalsIgnoreCase("yes"))
				{
					setDropdown();
				}
				
				if(strMultiAns.equalsIgnoreCase("yes"))
				{
					if(we_MultiAns.getAttribute("aria-pressed").equalsIgnoreCase("false"))
						we_MultiAns.click();
				}
				else if (strMultiAns.equalsIgnoreCase("no")) 
				{
					if(we_MultiAns.getAttribute("aria-pressed").equalsIgnoreCase("true"))
						we_MultiAns.click();
				}
				break;

			case "Text":
				/*
				 * if(strRequired.equalsIgnoreCase("yes")) {
				 * if(we_Required.getAttribute("aria-pressed").equalsIgnoreCase("false"))
				 * we_Required.click(); } else if (strRequired.equalsIgnoreCase("no")) {
				 * if(we_Required.getAttribute("aria-pressed").equalsIgnoreCase("true"))
				 * we_Required.click(); }
				 */
					
				
				if(strLongAns.equalsIgnoreCase("yes"))
				{
					if(we_LongAns.getAttribute("aria-pressed").equalsIgnoreCase("false"))
						we_LongAns.click();
				}
				else if (strLongAns.equalsIgnoreCase("no")) 
				{
					if(we_LongAns.getAttribute("aria-pressed").equalsIgnoreCase("true"))
						we_LongAns.click();
				}
				break;		
				
			case "Date":			
				break;
				
			case "Rating":			
				break;	
			case "Net Promoter Score�":
			break;
			case "Likert":
				break;
			case "File upload":	
				if(sh.IsWebElementExists(we_FileuploadWarning))
					we_FileuploadWarning.findElement(By.xpath("//span[text()='OK']")).click();
				
				String strfilecntXpath="//div[@class='question-setting-dropdown']/div[@aria-label='File number limit: ']//following::span[text()='"+strfilecount+"']";
				we_filecount.click();
				driver.findElement(By.xpath(strfilecntXpath)).click();
				
				String strfilesizeXpath="//div[@class='question-setting-dropdown']/div[@aria-label='Single file size limit: ']//following::span[text()='"+strfilesize+"']";
				we_filesize.click();
				driver.findElement(By.xpath(strfilesizeXpath)).click();
				break;
				
			default:
				break;
			}
			
			if(strRequired.equalsIgnoreCase("yes"))
			{
				if(we_Required.getAttribute("aria-pressed").equalsIgnoreCase("false"))
					we_Required.click();
			}
			else if (strRequired.equalsIgnoreCase("no")) 
			{
				if(we_Required.getAttribute("aria-pressed").equalsIgnoreCase("true"))
					we_Required.click();
			}
			isClicked=true;
		} 
		catch (Exception e) 
		{
			isClicked=false;
			e.printStackTrace();
		}
		return isClicked;
		//driver.findElement(By.xpath("//*[@aria-label='Required']")).click();		
	}

	public void setDropdown()
	{
		try 
		{
			if(we_MultiAns.getAttribute("aria-pressed").equalsIgnoreCase("false"))
			{
				we_MoreOptions.click();
				we_dropdown.click();
			}
			else if (we_MultiAns.getAttribute("aria-pressed").equalsIgnoreCase("false")) 
			{
				we_MultiAns.click();
				we_MoreOptions.click();
				we_dropdown.click();
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	public boolean setAns(String strAns,String strOpt, String quesType,boolean pressEnter)
	{
		boolean isSetAns = false;
		String strXPath = "";
		try 
		{
			//strOpt="Option 1";
			if(quesType.equalsIgnoreCase("likert"))
			{
				strXPath = "//*[@class='office-form-textfield']//*[text()='"+strOpt+"']";
			}
			else 
			{
				strXPath = "//*[@class='office-form-textfield']//*[@value='"+strOpt+"']";
			}
			
			
			WebElement we_ansXpath = driver.findElement(By.xpath(strXPath));
			//we_ansXpath.click();
			
			while(true)
			{
				we_ansXpath.sendKeys(Keys.HOME);
				we_ansXpath.sendKeys(Keys.SHIFT,Keys.END);
				we_ansXpath.sendKeys(strAns);
				if(we_ansXpath.getAttribute("value").equalsIgnoreCase(strAns))
					break;
			}
			
			if(pressEnter)
			{
				we_ansXpath.sendKeys(Keys.TAB);
				we_ansXpath.sendKeys(Keys.TAB);
			}
			isSetAns=true;	
			/*
			 * if(quesType.equalsIgnoreCase("likert")) we_ansXpath.sendKeys(Keys.ENTER);
			 */
		} 
		catch (Exception e) 
		{
			isSetAns=false;
			e.printStackTrace();
			throw e;
		}
		return isSetAns;
	}

	public boolean setQuest(String strQuest)
	{
		boolean isSet=false;
		try 
		{
			we_quest.sendKeys(Keys.HOME);
			we_quest.sendKeys(Keys.SHIFT, Keys.END);
			we_quest.sendKeys(strQuest);
			isSet=true;

			/*
			 * while(true) { we_quest.sendKeys(Keys.HOME);
			 * we_quest.sendKeys(Keys.SHIFT,Keys.END); we_quest.sendKeys(strQuest);
			 * if(we_quest.getText().equalsIgnoreCase(strQuest)) break; }
			 */
		} 
		catch (Exception e) 
		{
			isSet=false;
			e.printStackTrace();
		}
		return isSet;
	}

	public void ClickAddOption()
	{
		try 
		{
			we_AddOption.click();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public Map<Boolean, String> GetShareCollaborateLink()
	{
		Map<Boolean,String> dataLink = new HashMap<>();
		String data="";
		try 
		{
			//WebElement we_share = driver.findElement(By.xpath("//div[@class='headbar-button-container']//following::div[text()='Share']"));
			WebElement we_linkviewedit = null;
			WebElement we_arrow = null;
			WebElement we_optSelect = null;
			WebElement we_copyText = null;
			
			//we_share.click();
			we_linkviewedit = driver.findElement(By.xpath("//div[@class='flex-pane-sharelink-container']//following::span[text()='Get a link to view and edit']"));
			sh.WaitForElement(we_linkviewedit);
			if(sh.IsWebElementExists(we_linkviewedit))
			{
				we_linkviewedit.click();
				we_arrow = driver.findElement(By.xpath("//div[@aria-label='Share to collaborate']//following-sibling::i[@class='ms-Icon ms-Icon--ChevronDown select-placeholder-arrow forms-icon-size14x14']"));
				we_arrow.click();
				//we_optSelect = driver.findElement(By.xpath("//li[@class='select-option selected']//following::span[text()='Only people in my organization can view and edit']"));
				we_optSelect = driver.findElement(By.xpath("//li[@class='select-option selected']//following::span[text()='People in my organization can view and edit']"));
				we_optSelect.click();
				//we_copyText = driver.findElement(By.xpath("//div[@aria-label='Share to collaborate']//following::div[@class='flex-pane-share-content-copy']//following::button[@title='Copy' and text()='Copy']"));
				we_copyText = driver.findElement(By.xpath("//div[@aria-label='Share to collaborate']//descendant::input[@aria-label='Link for Sharing to Collaborate']"));
				we_copyText.click();
				data = we_copyText.getAttribute("value");
				//data = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
				dataLink.put(true, data);
			}
		} 
		catch (Exception e) 
		{
			dataLink.put(false, "");
			e.printStackTrace();
		}
		return dataLink;
	}

	
	public Map<Boolean, String> GetShareTemplateLink()
	{
		String data="";
		Map<Boolean,String> dataLink = new HashMap<>();
		try 
		{
			WebElement we_share = driver.findElement(By.xpath("//div[@class='headbar-button-container']//following::div[text()='Share']"));
			WebElement we_linkduplicate = null;				
			WebElement we_copyText = null;
			
			we_share.click();
			we_linkduplicate = driver.findElement(By.xpath("//div[@class='flex-pane-sharelink-container']//following::span[text()='Get a link to duplicate']"));
			sh.WaitForElement(we_linkduplicate);
			if(sh.IsWebElementExists(we_linkduplicate))
			{
				we_linkduplicate.click();
				we_copyText=driver.findElement(By.xpath("//div[@aria-label='Share as a template']//descendant::input[@aria-label='Link for Sharing template']"));
				//we_copy = driver.findElement(By.xpath("//div[@aria-label='Share as a template']//following::div[@class='flex-pane-share-content-copy']//following::button[@title='Copy' and text()='Copy']"));
				we_copyText.click();				
				data = we_copyText.getAttribute("value");
				//data = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
				dataLink.put(true, data);
			}
		}
		catch (Exception e) 
		{
			dataLink.put(false, "");
			e.printStackTrace();		
		}
		System.out.println("In the method before returning with link "+dataLink.get(true));
		return dataLink;
	}

	
}
