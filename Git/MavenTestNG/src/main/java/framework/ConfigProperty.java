package framework;

import java.io.*;
import java.util.*;
import org.jasypt.encryption.pbe.*;

public class ConfigProperty {
	private static ConfigProperty inst=null;
	private String msurl;
	private String msuid;
	private String mspwd;
	public String msgrpname;
	public Long ImplicitWaitTime;
	public Long ExplicitWaitTime;
	private String browserName;
	private String strFileName;
	
	
	private ConfigProperty()
	{
		try {
			FileReader reader = new FileReader("Config\\MSConfig.cfg");
			Properties prop = new Properties();
			prop.load(reader);
			
			setMsurl(prop.getProperty("forms_url"));
			setMsuid(prop.getProperty("user_email"));
			setMspwd(DecryptString(prop.getProperty("password")));
			setBrowserName(prop.getProperty("browser"));
			setFileName(prop.getProperty("filename"));
			//mspwd = prop.getProperty("password");
			msgrpname = prop.getProperty("groupname");
			
			ImplicitWaitTime = Long.parseLong(prop.getProperty("Implicit"));
			ExplicitWaitTime = Long.parseLong(prop.getProperty("Explicit"));
			
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static ConfigProperty getInstance()
	{
		if(inst == null)
			inst = new ConfigProperty();
		return inst;
	}
	
	public String EncryptString(String StringToEncrypt)
	{
		String encrypted = "";
		try 
		{
			String key = "mango";
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			encryptor.setPassword(key);
			encrypted= encryptor.encrypt(StringToEncrypt);
			System.out.println("Encrypted password is "+encrypted);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return encrypted;
	}
	
	public String DecryptString(String StringToDecrypt)
	{
		String decrypted = "";
		try 
		{
			String key = "mango";
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			encryptor.setPassword(key);
			decrypted = encryptor.decrypt(StringToDecrypt);
			//System.out.println("Decrypted password is "+decrypted);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return decrypted;
	}

	public String getMsurl() {
		return msurl;
	}

	public void setMsurl(String msurl) {
		this.msurl = msurl;
	}

	public String getMsuid() {
		return msuid;
	}

	public void setMsuid(String msuid) {
		this.msuid = msuid;
	}

	public String getMspwd() {
		return mspwd;
	}

	public void setMspwd(String mspwd) {
		this.mspwd = mspwd;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getFileName() {
		return strFileName;
	}

	public void setFileName(String strFileName) {
		this.strFileName = strFileName;
	}
	 

}
