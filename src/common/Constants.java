/**
 * 
 */
package common;


/**
 * @author Aditya Bugalia
 * 
 * 
 * This class is collection of all the strings used in the system.
 * 
 * This contains:
 * 
 * Text message used for displaying
 * File paths for Questions, categories, and scores.
 * The details for admin.
 * 
 *
 */
public class Constants {
	
//File Paths for different files used in the program	
	public static String QUESTIONS_FILE_PATH = "/Users/Tech/Development/Java WorkSpace/Java_Final_Project_Aditya_Winter2020_1930599/src/Questions.json";
	public static String CATEGORIES_FILE_PATH = "/Users/Tech/Development/Java WorkSpace/Java_Final_Project_Aditya_Winter2020_1930599/src/Categories.json";

	public static String SCORES_FILE_PATH = "/Users/Tech/Development/Java WorkSpace/Java_Final_Project_Aditya_Winter2020_1930599/src/scores.json";

	
	// ADMIN DETAILS
	
	public static String  ADMIN_NAME="Admin";
	public static String  ADMIN_EMAIL="admin@gmail.com";
	public static String  ADMIN_PASSWORD="12345";
	// Strings used for display in the program
	
	public static String  ADMIN_NAME_WRONG="Admin name not correct. start again and enter correct name";
	public static String  ADMIN_EMAIL_WRONG="Admin email not correct. start again and enter correct email";
	public static String  ADMIN_PASSWORD_WRONG="Admin password not correct. start again and enter correct password";
	public static String WELCOME_MESSAGE = "------------------- Welcome to Trivia Game -------------------";
	public static String END_MESSAGE = "------------------- End Trivia Game -------------------";
	public static String WELCOME_MESSAGE_FOR_USER_ADMIN_PRIOR_STRING = "************ Welcome ";
	public static String WELCOME_MESSAGE_FOR_USER_ADMIN_POST_STRING = " ************ ";
	
	public static String WELCOME_SELECTED_CATEGORY_PRIOR_POST = " ####### ";
	
	public static String OPTION_SIGNIN_ROLE_USER = "Enter 1 to start game as a new player.";
	public static String OPTION_SIGNIN_ROLE_ADMIN = "Enter 2 to manage game as an admin.";
	public static String OPTION_SIGNIN_ROLE_EXIT = "Enter 0 to exit Trivia Game.";
	public static String OPTION_SIGNIN_ROLE_DEFAULT = "Enter your answer from 0, 1 or, 2.";
	public static String QUESTION = "Question: ";
	public static String CORRECT_ANSWER = "Correct answer!";
	public static String WRONG_ANSWER = "Unlucky! Your answer is wrong.";
	public static String CORRECT_ANSWER_IS = "Correct answer is ";
	public static String YOUR_ANSWER = "Your Answer: ";
	
	public static String UPDATE_QUESTION_SELECTION_REQUEST = "Enter Question ID to modify/update question";
	
	public static String DELETE_QUESTION_SELECTION_REQUEST = "Enter Question ID to delete question";
	
	
	public static String ANSWER = "Answer: ";
	public static String QUESTIONID = "Question ID: ";
	public static String DESCRIPTION = "Description: ";
	public static String EXIT = "Exit ";
	public static String EXIT_CATEGORY_LEVEL = "Press 0 to Exit ";

	
	public static String CONFIRM_SIGNIN_ROLE_EXIT = "You have decided to quit Trivia Game.";
	
	public static String AUTHORIZATION_NAME = "Enter your name: ";
	public static String AUTHORIZATION_NAME_FAILED = "Please enter valid name: ";
	public static String AUTHORIZATION_EMAIL_ID = "Enter your emailId: ";
	public static String AUTHORIZATION_EMAIL_ID_FAILED = "Please enter valid emailId: ";
	public static String AUTHORIZATION_PASSCODE = "Enter passcode to authorize as an admin: ";
	public static String AUTHORIZATION_SUCCESSFULL = "Authorization Successfull! ";
	public static String AUTHORIZATION_ERROR = "Authorization Failed! Please try again.";
	
	public static String RE_AUTHORIZATION = "Do you want to re-authorize?";
	public static String OPTION_TRUE_OR_FALSE = "Enter T for TRUE & F for FALSE. You are only allowed to choose from \"TRUE\" or \"FALSE\"";
	
	public static String CATEGORY = "Category ";
	
	public static String OPTION_CATEGORY_MENU_EXIT = "Enter 0 to exit.";
	public static String OPTION_CATEGOTY_MENU_DEFAULT = "Enter your answer from 0, 1 or, 2.";
	
	public static String OPTION_CATEGORY_ADD = "Enter 1 to ADD new question and answer for the category ";
	public static String OPTION_CATEGORY_MODIFY = "Enter 2 to MODIFY existing question and answer for the category ";
	public static String OPTION_CATEGORY_DELETE = "Enter 3 to DELETE existing question and answer for the category ";
	public static String OPTION_CATEGORY_EXIT = "Enter 0 to exit category ";
	public static String OPTION_CATEGORY_VIEW = "Enter 4 to View all Questions ";
	public static String OPTION_SCORE_VIEW = "Enter 5 to View all Scores : ";
	public static String OPTION_CATEGOTY_DEFAULT = "Enter your answer from 0, 1, 2 or 3.";
	
	public static String SUCCESS_ADDING_FILE = "New question is added successfully.";
	public static String SUCCESS_UPDATING_FILE = "Question is modified successfully.";
	public static String SUCCESS_DELETING_FILE = "Question is deleted successfully.";
	public static String ERROR_READING_FILE = "An error occurred on reading the file\n";
	
	public static String ENTER_NEW_DATA_TO_UPDATE = "Now, enter new data to update selected question.";
	public static String NO_DATA_TO_UPDATE = "No Record found to modify. Please try again later.";
	public static String NO_DATA_TO_DELETE = "No Record found to delete. Please try again later.";
	
	public static String FILE_NOT_EXIST = "Requested file does not exist. Please contact admin.";
	
	public static String ERROR_LOGICAL = "You are not allowed to add * in the text. It is restricted for logical code executing purpose.";
	public static String ERROR_NO_OPTION_AVAILABLE = "No option available. Please try again.";
	public static String ERROR_INPUT_INT_MISMATCH= "Input Mismatch! Please enter only number: ";
	
	public static String TITLE_TOTAL_QUESTIONS = "You will have total %s Questions";
	public static String COMPLETED_GAME = "You have completed Trivia game for category ";
	public static String TOTAL_QUESTIONS = "Total Questions: ";
	public static String TOTAL_SCORES = "Total Scores: ";
	public static String TOTAL_CORRECT_ANSWER = "Total Correct Answers: ";
	public static String TOTAL_WRONG_ANSWER = "Total Wrong Answers: ";
	public static String TOTAL_PERCENTAGE = "You earned %s for category ";
	
	public static String ENTER_QUESTION = "Enter question: ";
	public static String ANSWER_THE_QUESTION = "Answer the question of ";
	public static String DESCRIPTION_OF_THE_ANSWER = "Description of the false answer: ";
	
	public static String NAME_OF_CATEGORY = "Category Name : ";
	public static String DESC_OF_CATEGORY = "Category Description : ";
	public static String SELECTION_OF_CATEGORY = "For Selection, Enter :";
	
	
	
	
	
	
}
