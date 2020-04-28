package framework;
import java.util.Scanner;

import org.jasypt.encryption.pbe.*;

public class TestEncryptDecrypt {

	public static void main(String[] args) 
	{
		try {
			System.out.println("Enter the password:- ");
			Scanner sc = new Scanner(System.in);
			String strInput = sc.nextLine();
			String key = "mango";
			
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
