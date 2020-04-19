/**
 * 
 */
package users;

import java.util.Scanner;

import common.Constants;
import common.PrintUtils;
import common.Validations;

/**
 * @author Aditya Bugalia
 * Abstract  class for types of user in the system. It is parent class for admin and player.
 * This class contains common methods and functionalities used by Admin and player.
 */
public abstract class Person {

	// Scanner to get input data from the user
	private Scanner inputScanner = new Scanner(System.in);

	// display name is the alias that user uses to play the game. This can  be same for two or more person.
	protected String displayName = "";
	//email id the unique in the system. It acts as username too.
	private String emailID = "";

	public abstract void displayMenu();

	
	public void requestDisplayname() {
		PrintUtils.print("" + Constants.AUTHORIZATION_NAME);

		this.displayName = inputScanner.next();

		this.displayName += inputScanner.nextLine();

		boolean isValidDisplayname = Validations.validateDisplayName(this.displayName);
		
		

		if (!isValidDisplayname) {
			getCorrectDisplayName();
		}
	}

	public boolean requestDisplayname(boolean adminCheck) {
		PrintUtils.print("" + Constants.AUTHORIZATION_NAME);

		this.displayName = inputScanner.next();

		this.displayName += inputScanner.nextLine();

		boolean isValidDisplayname = false;
		
		if(Validations.validateDisplayName(this.displayName) && this.displayName.equalsIgnoreCase(Constants.ADMIN_NAME)){
			isValidDisplayname=true;
		
		}

		return isValidDisplayname;
	}
	public void requestUserEMailId() {
		PrintUtils.print("" + Constants.AUTHORIZATION_EMAIL_ID);

		this.emailID = inputScanner.next();
		this.emailID += inputScanner.nextLine();

		boolean isValidEmail = Validations.validateEmail(this.emailID);

		if (!isValidEmail) {
			getCorrectEmailId();
		}
	}
	public boolean requestUserEMailId(boolean isAdmin) {
		PrintUtils.print("" + Constants.AUTHORIZATION_EMAIL_ID);

		this.emailID = inputScanner.next();
		this.emailID += inputScanner.nextLine();

		boolean isValidEmail = false;

		if (Validations.validateEmail(this.emailID) && this.emailID.equalsIgnoreCase(Constants.ADMIN_EMAIL) ) {
			isValidEmail= true;
		}
		
		return isValidEmail;
	}

	private void getCorrectEmailId() {
		PrintUtils.insertBreak();
		PrintUtils.print("" + Constants.AUTHORIZATION_EMAIL_ID_FAILED);

		this.emailID = inputScanner.next();

		boolean isValidEmail = Validations.validateEmail(this.emailID);

		if (!isValidEmail) {
			getCorrectEmailId();
		}
	}

	public String getDisplayName() {
		return this.displayName;
	}

	private void getCorrectDisplayName() {
		PrintUtils.insertBreak();
		PrintUtils.print("" + Constants.AUTHORIZATION_NAME_FAILED);

		this.displayName = inputScanner.next();

		boolean isValidDisplayName = Validations.validateDisplayName(this.displayName);

		if (!isValidDisplayName) {
			getCorrectDisplayName();
		}
	}


	public String getEmailId() {
		return this.emailID;
	}
}

