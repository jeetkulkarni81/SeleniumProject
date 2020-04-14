package framework;
import org.jasypt.encryption.pbe.*;

public class TestEncryptDecrypt {

	public static void main(String[] args) 
	{
		try {
			
			String key = "mango";
			String strInput = "password@123";
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			encryptor.setPassword(key);
			String encrypted = encryptor.encrypt(strInput);
			System.out.println("Encrypted password is "+encrypted);
			
			String decrypted = encryptor.decrypt(encrypted); 
			System.out.println("Decrypted password is "+decrypted);

      } catch(Exception e) {
         System.out.println("Error :" + e.getMessage());
      }	

	}

}
