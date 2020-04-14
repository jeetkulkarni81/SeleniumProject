package pages;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import framework.*;

import java.util.*;

public class MSForm {

	//@FindBy(xpath = "//span[text()='Section']")
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
	
	List<String> lstRow = new ArrayList<String>();
	
	public MSForm()
	{
		driver = sh.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	public void AddNewForm()
	{
		try 
		{
			driver.findElement(By.linkText("Forms")).click();
			driver.findElement(By.xpath("//div/button[@title='New Form']//following::div[text()='New Form']")).click();
			driver.findElement(By.xpath("//div[@class='__title-designer__ form-designer-title-view office-form-title-container']")).click();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	public void AddNewGroupForm()
	{
		try 
		{
			driver.findElement(By.linkText("Forms")).click();
			driver.findElement(By.xpath("//button[@title='Group forms']")).click();
			/*
			 * WebElement we_grpform =
			 * driver.findElement(By.xpath("//div[@aria-label='Recent group forms']"));
			 * sh.WaitForElement(we_grpform); we_grpform.click();
			 */
			//driver.findElement(By.xpath("//div[@class='button-content']//following::div[@class='fl-displayname']/span[text()='TelePacific_CRM_team']")).click();
			driver.findElement(By.xpath("//div/button[@title='New group Form']//following::div[text()='New group Form']")).click();
			//driver.findElement(By.xpath("//div/button[@title='New group Quiz']//following::div[text()='New group Quiz']")).click();
			driver.findElement(By.xpath("//div[@class='__title-designer__ form-designer-title-view office-form-title-container']")).click();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public boolean ClickNewForm()
	{
		boolean isClicked=false;
		try 
		{
			driver.findElement(By.xpath("//*[text()='Create a new form']")).click();
			driver.findElement(By.xpath("//div[@id='form-designer']//descendant::span[contains(text(),'Untitled')]")).click();
			isClicked=true;
		} 
		catch (Exception e) 
		{
			isClicked=false;
			e.printStackTrace();
			throw e;
		}
		return isClicked;
	}
	
	public void NavigateToForm()
	{ConfigProperty objcfg = ConfigProperty.getInstance();
	String strgrpname = objcfg.msgrpname;
		try 
		{
			driver.findElement(By.xpath("//button[@title='Close']")).click();
			driver.findElement(By.xpath("//button[@title='Group forms']")).click();
			WebElement we_grpform = driver.findElement(By.xpath("//div[@aria-label='Recent group forms']"));
			sh.WaitForElement(we_grpform);
			we_grpform.click();
			driver.findElement(By.xpath("//div[@class='button-content']//following::div[@class='fl-displayname']/span[text()='"+strgrpname+"']")).click();						
		} catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	public void ClickNewGroupForm()
	{
		ConfigProperty objcfg = ConfigProperty.getInstance();
		String strgrpname = objcfg.msgrpname;
		try 
		{
			driver.findElement(By.xpath("//button[@title='Close']")).click();
			driver.findElement(By.xpath("//button[@title='Group forms']")).click();
			WebElement we_grpform = driver.findElement(By.xpath("//div[@aria-label='Recent group forms']"));
			sh.WaitForElement(we_grpform);
			we_grpform.click();
			driver.findElement(By.xpath("//div[@class='button-content']//following::div[@class='fl-displayname']/span[text()='"+strgrpname+"']")).click();
			driver.findElement(By.xpath("//div/button[@title='New group Form']//following::div[text()='New group Form']")).click();
			//driver.findElement(By.xpath("//div/button[@title='New group Quiz']//following::div[text()='New group Quiz']")).click();
			driver.findElement(By.xpath("//div[@class='__title-designer__ form-designer-title-view office-form-title-container']")).click();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public void setImage(String strSecImg) throws Exception
	{
		try 
		{
			WebElement we_setMedia = driver.findElement(By.xpath("//button[@title='Insert media']"));
			sh.WaitForElement(we_setMedia);
			we_setMedia.click();
			
			WebElement we_image = driver.findElement(By.xpath("//div[text()='Image']"));
			sh.WaitForElement(we_image);
			we_image.click();
			
			WebElement we_upload = driver.findElement(By.xpath("//div/button[@title='Upload']/div[@class='button-content' and text()='Upload']"));
			sh.WaitForElement(we_upload);
			we_upload.click();
			
			StringSelection strSel = new StringSelection(strSecImg);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSel, null);
			
			Thread.sleep(3000);
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			
			WebElement we_imageExist = driver.findElement(By.xpath("//div[@class='title-designer-image-editor']"));
			sh.WaitForElement(we_imageExist);
			
			WebElement we_imageLarge = driver.findElement(By.xpath("//div[@class='title-designer-image-editor']//following::button[@title='Large']"));
			sh.WaitForElement(we_imageLarge);
			we_imageLarge.click();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	public void setSection(String SecTitle, String SecDesc,String strSecImg) throws Exception
	{
		try 
		{
			driver.findElement(By.xpath("//*[text()='Add new']")).click();
			we_more.click();
			we_section.click();
			we_SecTitle.sendKeys(SecTitle);
			if(strSecImg.length() >0 )
			{
				setImage(strSecImg);
			}
			we_SecSubTitle.sendKeys(SecDesc);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * This method is use to click "Add New" button for adding new question.
	 * @param Choice
	 * @param Text
	 * @param Date
	 * @param Rating
	 * */
	public void AddNewQuest(String optionType, String strRequired, String strMultiAns, String strLongAns, String strAddOtheropt,String strDropDown, String strfilecount, String strfilesize) throws Exception
	{
		try 
		{
			WebElement we_AddNew = driver.findElement(By.xpath("//button[@aria-label='Add new']"));
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
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
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
	
	public void setQuest(String strQuest)
	{
		try 
		{
			
			  we_quest.sendKeys(Keys.HOME); 
			  we_quest.sendKeys(Keys.SHIFT,Keys.END);
			  we_quest.sendKeys(strQuest);
			 
			/*
			 * while(true) { we_quest.sendKeys(Keys.HOME);
			 * we_quest.sendKeys(Keys.SHIFT,Keys.END); we_quest.sendKeys(strQuest);
			 * if(we_quest.getText().equalsIgnoreCase(strQuest)) break; }
			 */
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public void setAns(String strAns,String strOpt, String quesType,boolean pressEnter)
	{
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
				
			/*
			 * if(quesType.equalsIgnoreCase("likert")) we_ansXpath.sendKeys(Keys.ENTER);
			 */
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	
	/*public String getAttribVal(String strXpath) throws Exception
	{
		xmloperation objxml = xmloperation.getInstance();
		String str = "";
		try 
		{
			NodeList nl = objxml.getXpath(strXpath);
			if(nl.getLength()==1)
			{
				for(int i=0;i<nl.getLength();i++)
				{
					str = nl.item(i).getNodeValue();
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		return str;
	}*/

	
	/*public void getQuestAns(String strXPath) throws Exception
	{
		String strXpathOption ="//div[@class='office-form-textfield']/textarea[@aria-label='Please enter a name for this option.' and contains(text(),'Option')]";
		String strXpathStatement="//div[@class='office-form-textfield']/textarea[@aria-label='Likert statement text' and contains(text(),'Statement')]";
		xmloperation objxml = xmloperation.getInstance();
		String strSecForCSV="";
		String str = "";
		int cntQuest=0;
		String strFormLink="";
		String strFormID="";
		String strShareTemplateLink="";
		String strShareCollaborateLink ="";
		
		try 
		{
			NodeList sec1 = objxml.getXpath(strXPath);
			NodeList ql;
			End100Outer:
			for(int cntsec=0;cntsec<sec1.getLength();cntsec++)
			{
				WebElement we_AddNew = driver.findElement(By.xpath("//button[@aria-label='Add new']"));
				if(!(we_AddNew.getAttribute("title").equalsIgnoreCase("add new")))
					break End100Outer;
				if(cntQuest == 100)
					break End100Outer;
				String strSecTitle = sec1.item(cntsec).getAttributes().getNamedItem("title").getNodeValue();
				String strSecDesc = sec1.item(cntsec).getAttributes().getNamedItem("description").getNodeValue();
				String strSecImg = sec1.item(cntsec).getAttributes().getNamedItem("bg_image").getNodeValue();
				if(strSecImg.length() >0) 
					strSecForCSV = strSecImg+";"+strSecForCSV;
				setSection(strSecTitle, strSecDesc, strSecImg);
				Element eleSec = (Element)sec1.item(cntsec);
				ql = eleSec.getElementsByTagName("question");				
				for(int i=0;i<ql.getLength();i++)
				{					
					cntQuest++;					
					if(cntQuest > 100)
						break End100Outer;
					String strOptionType = ql.item(i).getAttributes().getNamedItem("option_type").getNodeValue();
					String strRequired = ql.item(i).getAttributes().getNamedItem("required").getNodeValue();
					String strAddOtherOpt = "no";
					String strMultiAns = "";
					String strDropdown="No";
					String strfilecount="";
					String strfilesize="";
					if(strOptionType.equalsIgnoreCase("time"))
						strOptionType = "Date";
					if(strOptionType.equalsIgnoreCase("choice")||strOptionType.equalsIgnoreCase("text")||strOptionType.equalsIgnoreCase("date"))
						strOptionType = strOptionType.substring(0, 1).toUpperCase()+strOptionType.substring(1).toLowerCase();
					if(strOptionType.equalsIgnoreCase("choice"))
					{
						strAddOtherOpt = ql.item(i).getAttributes().getNamedItem("add_otheropt").getNodeValue();
						strMultiAns = ql.item(i).getAttributes().getNamedItem("multi_answer").getNodeValue();
						strDropdown = ql.item(i).getAttributes().getNamedItem("drop_down").getNodeValue();
					}
					if(strOptionType.equalsIgnoreCase("file upload"))
					{
						strfilecount = ql.item(i).getAttributes().getNamedItem("filecount").getNodeValue();
						strfilesize = ql.item(i).getAttributes().getNamedItem("singlefilesize").getNodeValue();
					}
						
					String strLongAnswer = "";
					if(strOptionType.equalsIgnoreCase("text"))
						strLongAnswer = ql.item(i).getAttributes().getNamedItem("long_answer").getNodeValue();
										
					we_AddNew = driver.findElement(By.xpath("//button[@aria-label='Add new']"));
					if(!(we_AddNew.getAttribute("title").equalsIgnoreCase("add new")))
						break End100Outer;					
					AddNewQuest(strOptionType,strRequired,strMultiAns,strLongAnswer,strAddOtherOpt,strDropdown, strfilecount, strfilesize);
					String strQuest = ql.item(i).getAttributes().getNamedItem("title").getNodeValue();
					strQuest = strQuest.trim();
					setQuest(strQuest);
					if(strOptionType.equalsIgnoreCase("likert"))
					{
						DeleteControl(strXpathOption,"Option 1");
						DeleteControl(strXpathStatement,"Statement 1");
					}
					
					
					Element eleq1 = (Element)ql.item(i);
					
					
					NodeList a1 = eleq1.getElementsByTagName("answer");
					Element elea1 = (Element)a1.item(0);
					elea1.getAttribute("data_type");
					
					NodeList a2 = elea1.getElementsByTagName("option");
					int cntLikertOption=0;int cntLikertStatement=0;
					for(int j=0;j<a2.getLength();j++)
					{
						Element elea2 = (Element)a2.item(j);
						elea2.getAttribute("value");
						str = elea2.getTextContent();
						str = str.trim();
						if(j>0)
						{
							if(strOptionType.equalsIgnoreCase("likert"))
							{
								if(elea2.getAttribute("type").equalsIgnoreCase("horizontal"))
								{
									cntLikertOption++;
									if(cntLikertOption < 7)
									{
										Robot r = new Robot();
										r.keyPress(KeyEvent.VK_ENTER);
										r.keyRelease(KeyEvent.VK_ENTER);
										setAns(str, elea2.getAttribute("value"),strOptionType,false);
									}
									
								}
								if(elea2.getAttribute("type").equalsIgnoreCase("vertical"))
								{
									cntLikertStatement++;
									if(cntLikertStatement >1)
										driver.findElement(By.xpath("//span[text()='Add statement']")).click();
									setAns(str, elea2.getAttribute("value"),strOptionType,false);
								}											
							}
							else
							{
								if(j>1 && (strOptionType.equalsIgnoreCase("choice")))
									driver.findElement(By.xpath("//span[text()='Add option']")).click();
								setAns(str, elea2.getAttribute("value"),strOptionType,false);
							}
							
						}
						else
						{
							setAns(str, elea2.getAttribute("value"),strOptionType,false);
						}						
					}
					
				}
			}
		
			SetRowForCSV(strSecForCSV);
			strFormLink=driver.getCurrentUrl();
			SetRowForCSV(strFormLink);
			if(strFormLink.contains("="))
			{
				strFormID=strFormLink.split("=")[1];
			}
			else
			{
				System.out.println("Form Link contains no form id");
			}
				
			SetRowForCSV(strFormID);
			strShareTemplateLink = GetShareTemplateLink();
			strShareCollaborateLink = GetShareCollaborateLink();
			if((strShareTemplateLink.length() < 1)||(strShareCollaborateLink.length() < 1)||!(strFormLink.contains("=")))
			{
				System.out.println("Found blank in Link");
			}
			SetRowForCSV(strShareTemplateLink);			
			SetRowForCSV(strShareCollaborateLink);
			//ql = objxml.getXpath(strXPath);
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}
	*/
	public void ClickPreview()
	{
		try 
		{
			we_preview.click();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}

	public void DeleteControl(String strXpath, String strExclude) throws Exception
	{
		
		String strText="";
		String strXpathDel = "";
		WebElement we_delete = null;
		List<WebElement> lstElements = driver.findElements(By.xpath(strXpath));
		try 
		{
			Robot r = new Robot();
			for (WebElement we : lstElements) 
			{
				sh.WaitForElement(we);
				if(!(we.getText().contains(strExclude)))
				{
					strText = we.getText();
					sh.WaitForElement(we);
					we.click();
					we.sendKeys(Keys.TAB);
					r.keyPress(KeyEvent.VK_ENTER);
					r.keyRelease(KeyEvent.VK_ENTER);
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}

	public void SetRowForCSV(String strData)
	{
		try 
		{
			lstRow.add(strData);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}

	public List<String> GetRowForCSV()
	{
		return lstRow;
	}

	public void ClearRowCSV()
	{
		lstRow = new ArrayList<String>();
	}

	public String GetShareCollaborateLink() throws Exception
	{
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
				we_optSelect = driver.findElement(By.xpath("//li[@class='select-option selected']//following::span[text()='Only people in my organization can view and edit']"));
				we_optSelect.click();
				//we_copyText = driver.findElement(By.xpath("//div[@aria-label='Share to collaborate']//following::div[@class='flex-pane-share-content-copy']//following::button[@title='Copy' and text()='Copy']"));
				we_copyText = driver.findElement(By.xpath("//div[@aria-label='Share to collaborate']//descendant::input[@aria-label='Link for Sharing to Collaborate']"));
				we_copyText.click();
				data = we_copyText.getAttribute("value");
				//data = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		return data;
	}

	
	public String GetShareTemplateLink() throws Exception
	{
		String data="";
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
				
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
		return data;
	}
	
	
	public void DuplicateForm() throws Exception
	{
		WebDriverWait wt = new WebDriverWait(driver,10);
		try 
		{
			List<WebElement> lstwefrm = driver.findElements(By.xpath("//div[@class='fl-history-tile fl-tile']"));
			
			for (WebElement wefrm : lstwefrm) 
			{
				wefrm.click();
				String strTemplateLink = GetShareTemplateLink();
				Robot r = new Robot();
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_T);
				r.keyRelease(KeyEvent.VK_T);
				r.keyRelease(KeyEvent.VK_CONTROL);
				
				ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
			    driver.switchTo().window(tabs.get(1));
			    driver.get(strTemplateLink);
				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public void DeleteForms()
	{
		WebDriverWait wt = new WebDriverWait(driver,10);
		Actions action = new Actions(driver);
		try 
		{
			List<WebElement> lstmoreOptions = driver.findElements(By.xpath("//button[@title='More options']"));
			for (WebElement we : lstmoreOptions) 
			{
				we.click();
				driver.findElement(By.xpath("//button[@title='Delete this form' and @aria-label='Delete']")).click();
				driver.findElement(By.xpath("//button[@aria-label='Continue']")).click();
				wt.until(ExpectedConditions.invisibilityOf(we));
				
			}
			WebElement we_recycle = driver.findElement(By.xpath("//div[@class='recycle-bin-button']/button[@aria-label='Recycle bin']//following-sibling::img[@class='recycle-bin-button-img']"));
			wt.until(ExpectedConditions.visibilityOf(we_recycle));
			action.moveToElement(we_recycle).perform();
			we_recycle.click();
			List<WebElement> lstrecycle = driver.findElements(By.xpath("//div[@class='fl-history-tile fl-tile']"));
			
			
			for (WebElement werecycle : lstrecycle) 
			{
				action.moveToElement(werecycle).perform();
				WebElement we_del = driver.findElement(By.xpath("//span[text()='Delete']"));
				wt.until(ExpectedConditions.visibilityOf(we_del));
				we_del.click();
				WebElement we_cont = driver.findElement(By.xpath("//button[@aria-label='Continue']"));	
				sh.WaitForElement(we_cont);
				we_cont.click();
				wt.until(ExpectedConditions.invisibilityOf(werecycle));
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public void CloseDriver()
	{
		try 
		{
			driver.quit();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
}
