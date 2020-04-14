package test;

import java.io.*;

import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.Assert;
import org.testng.annotations.*;

import framework.*;
import pages.Login;
import pages.MSForm;
import pages.NewForm;

public class MSFormCreation 
{
	String strurl = "";
	String struid = "";
	String strpwd = "";
	String strgrpname = "";
	Login objlogin;
	MSForm objMSForm;
	NewForm objnewForm;
	
  @Test(priority = 0,groups = {"FormCreation"})
  public void Login() 
  {
	  Assert.assertEquals(objlogin.isLogin(strurl, struid, strpwd), true);
	  Assert.assertEquals(objMSForm.ClickNewForm(),true);
  }
  
	
	@Test(priority = 1, groups = {"FormCreation"}, dependsOnMethods = { "Login" })
	public void AddTitle() 
	{
		Assert.assertEquals(objnewForm.formTitleDesc("Test Title", "Test Description"), true);
	}

	@Test(priority = 2, groups = {"FormCreation"}, dependsOnMethods = { "Login", "AddTitle" }, dataProvider = "ReadExcel")
	public void AddQuest(String Title, String Desc, String QuestType, String strRequired, String strMultiAns, String strLongAnsw, String strAddOthOpt, String strDropdown, String strFileCnt, 
			String strFileSize, String strQuest, String strOpt1, String strOpt2, String strOpt3, String strOpt4) 
	{
		
		Assert.assertEquals(objnewForm.AddNewQuest(QuestType, strRequired, strMultiAns, strLongAnsw, strAddOthOpt, strDropdown, strFileCnt, strFileSize),true);
		Assert.assertEquals(objnewForm.setQuest(strQuest), true);
		objnewForm.setAns(strOpt1, "Option 1", QuestType, false);
		objnewForm.setAns(strOpt2, "Option 2", QuestType, false);
		objnewForm.ClickAddOption();
		objnewForm.setAns(strOpt3, "Option 3", QuestType, false);
		objnewForm.ClickAddOption();
		objnewForm.setAns(strOpt4, "Option 4", QuestType, false);
		/*
		 * Assert.assertEquals(objnewForm.AddNewQuest("Choice", "yes", "no", "no", "no",
		 * "no", "1", "100MB"), true);
		 * Assert.assertEquals(objnewForm.setQuest("This is test quest"), true);
		 * objnewForm.setAns("answ1", "Option 1", "Choice", false);
		 * objnewForm.setAns("answ2", "Option 2", "Choice", false);
		 * objnewForm.ClickAddOption(); objnewForm.setAns("answ3", "Option 3", "Choice",
		 * false);
		 */
		
	}
	 
  
  @BeforeClass
  public void beforeClass() 
  {
	  ConfigProperty objCfg = ConfigProperty.getInstance();
	  strurl =objCfg.getMsurl();
	  struid = objCfg.getMsuid();
	  strpwd = objCfg.getMspwd();
	  objlogin = new Login();
	  objMSForm = new MSForm(); 
	  objnewForm = new NewForm();
  }

  @AfterClass
  public void afterClass() 
  {
		
	  
  }
  
  @DataProvider
	public Object[][] ReadExcel() 
	{
	  Object Data[][] = null;
	  XSSFWorkbook workbook;
	  XSSFSheet worksheet;
	  DataFormatter formatter = new DataFormatter();
		try 
		{
			ConfigProperty objCfg = ConfigProperty.getInstance();
			FileInputStream fileInputStream= new FileInputStream(objCfg.getFileName());
			workbook = new XSSFWorkbook (fileInputStream);
			worksheet = workbook.getSheet("TestData");
			XSSFRow Row = worksheet.getRow(0);
			
			int RowNum = worksheet.getPhysicalNumberOfRows();
			int ColNum= Row.getLastCellNum();
			
			Data = new Object[RowNum-1][ColNum];
			
			for(int i=0; i<RowNum-1; i++)
			{
				XSSFRow row= worksheet.getRow(i+1);
				
				for (int j=0; j<ColNum; j++)
				{
					if(row==null)
                        Data[i][j]= "";
                    else
                    {
                        XSSFCell cell= row.getCell(j);
                        if(cell==null)
                            Data[i][j]= ""; //if it get Null value it pass no data 
                        else
                        {
                            String value=formatter.formatCellValue(cell);
                            Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
                        }
                    }
					
				}
			}
			workbook.close();
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return Data;
	}

}
