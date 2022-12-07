import java.util.Scanner;
import java.util.HashMap;
import java.lang.String;

public class CreateAccount {

	private String password;
	private String username;
	private userscount=0;


	public CreateAccount() {
		password = insertPassword();

	}


	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}




	public String insertPassword() {

		do {
			Scanner input = new Scanner(System.in);
			System.out.println("Please enter your password:");
			password = input.nextLine();
		}while (acceptPassword());
		userscount++
		return password;

	}


	public boolean acceptPassword(){

		boolean accept;
		boolean lengthIsValid= false;
		int length = password.length();
		if (length <=5 ) {
			System.out.println("Password is weak, more than 5 characters required");
		}else{
			lengthIsValid=true;
		}

		boolean containsNumber = false;

		for (int i=1;i<=9;i++) {
			String s= String.valueOf(i);
			if (password.contains(s)==true){
				containsNumber=true;
			}
		}

		if (containsNumber == false) {
			System.out.println("Your password should include numbers");
		}

		if (containsNumber==true && lengthIsValid==true) {
			accept=true;
		}else{
			accept=false;
		}

		return accept;
	}
}









