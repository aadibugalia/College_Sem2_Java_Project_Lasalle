/**
 * 
 */
package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import common.Constants;
import common.PrintUtils;
import users.Admin;
import users.Player;

/**
 * @author Aditya Bugalia
 * 
 * This class is entry to the system.
 * 
 * Start Game is the method that actully starts the game by presenting the choices.
 * 
 *
 */
public class Game {


		// It is required to get the value from user.
		private static Scanner inputScanner = new Scanner(System.in);

		public static void main(String[] args) {

			//start game
			startGame();
		}

		/**
		 * @pupose responsible to present main menu to the user.
		 * @param - not available
		 * 
		 * 	START
		 *	WRITE “Enter 1 for player”
		 *	WRITE “Enter 2 for admin”
		 *	WRITE “Enter 0 for exit”
		 *
		 *	Variable A
		 *	READ A
		 *
		 *	DO
		 *	CASE A = 1:
		 *		call PERSON
		 *
		 *	CASE A = 2: 
		 *		call ADMIN
		 *
		 *	CASE A = 0: 
		 *		END
		 *		
		 *	END WHILE A != 0
		 *	END
		 * 
		 */
		
		
		public static int getInputInt(Scanner scanner) {
			try {
				// return int value if it is int.
				return scanner.nextInt();

			} catch (InputMismatchException e) {

				// Shows warning
				PrintUtils.insertBreak();
				PrintUtils.print(Constants.ERROR_INPUT_INT_MISMATCH);
			}
			return 999;
		}
		
		
		// single attempt choice menu
		
		private static void startGame() {
			PrintUtils.println(Constants.WELCOME_MESSAGE);
			PrintUtils.insertBreak();

			// variable to hold user selected option for further options
			int optSignIn = 0;

		//	do {
				PrintUtils.println(Constants.OPTION_SIGNIN_ROLE_USER);
				PrintUtils.println(Constants.OPTION_SIGNIN_ROLE_ADMIN);
				PrintUtils.println(Constants.OPTION_SIGNIN_ROLE_EXIT);

				// read value from user and set in to "optSignIn" variable
				PrintUtils.print(Constants.YOUR_ANSWER);
				optSignIn =getInputInt(inputScanner);

				PrintUtils.insertBreak();

				switch (optSignIn) {
				case 1:
					// If user enter as a player
					Player player = new Player();
					
					//get display name
					player.requestDisplayname();
					
					// get emailid
					player.requestUserEMailId();

					// start fetching categories
					player.processInitialGameSetup();

					break;

				case 2:
					// If user enter as an admin
					Admin admin = new Admin();
					
					// get username
					if(!admin.requestDisplayname(true)) {
						
						PrintUtils.println(Constants.ADMIN_NAME_WRONG);
						break;
					}
					
					// get emailid
					
					if (!admin.requestUserEMailId(true)) {

						PrintUtils.println(Constants.ADMIN_NAME_WRONG);
						break;
					}
					// get passcode to authenticate admin
					admin.requestPasscode();
					break;

				case 0:
					// if user wish to quit
					PrintUtils.println(Constants.CONFIRM_SIGNIN_ROLE_EXIT);
					PrintUtils.insertBreak();
					PrintUtils.println(Constants.END_MESSAGE);
					break;

				default: {
					// If user enter any number except 1, 2 or 0, it gives warning to enter from
					// selected number
					PrintUtils.println(Constants.OPTION_SIGNIN_ROLE_DEFAULT);
					PrintUtils.insertBreak();
				}
				}
		//	} while (optSignIn != 0);
		}

		
	}

