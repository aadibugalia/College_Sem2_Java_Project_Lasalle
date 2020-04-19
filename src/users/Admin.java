/**
 * 
 */
package users;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import callbacks.FileOperationCallbacks;
import common.Constants;
import common.PrintUtils;
import common.Validations;
import files.FileOperationsThread;
import files.FileOperationsThread.FileOperationTypes;
import models.CategoryModel;
import models.PlayerScoreBoardModel;
import models.QuestionsModel;

/**
 * @author Aditya Bugalia
 * @category User
 * 
 *           This class represents one of the type of users, ADMIN which is
 *           child class of Class PERSON.
 * 
 *           This class allows 1- Display and Selection of Categories. 2- Add,
 *           Update, Delete, View Questions and their details. 3- View the
 *           scores saved in the file.
 * 
 *           Notes: The selection of categories is one chance only. If one
 *           enters wrong answer then need to start again. To delete a question,
 *           user needs to enter question ID, new question and answer.
 * 
 *           Basic Control Flow: The class relies on FileOperationsThread for
 *           all the operations related to files. FileOperationsThread responds
 *           through Interface Callback Methods (FileOperationCallbacks)
 *           implemented in class.
 * 
 *           When Requestpasscode method is called in Game Class, The methods
 *           after validating the passcode calls processAuthSuccess() and
 *           processAuthSuccess() starts FileOperationsThread to read
 *           categories. This is the actual start of Admin Functionality
 * 
 *           Authentication Info:
 * 
 *           Admin details are defined in Constants class and to access this
 *           class, same details need to be entered.
 * 
 */

//
public class Admin extends Person implements FileOperationCallbacks {

	// To get input from the admin
	private Scanner inputScanner = new Scanner(System.in);

	private ArrayList<CategoryModel> categoriesList;


	/**
	 * Helper method to get string from scanner class;
	 */
	public static String getInputString(Scanner scanner) {
		try {
			// return int value if it is int.
			return scanner.next();

		} catch (InputMismatchException e) {

			// Shows warning
			PrintUtils.insertBreak();
			PrintUtils.print(Constants.ERROR_INPUT_INT_MISMATCH);
		}
		return "";
	}

	/**
	 * Helper method to get integer from scanner class;
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

	/**
	 * asks user to enter password.
	 */
	public void requestPasscode() {
		PrintUtils.print("" + Constants.AUTHORIZATION_PASSCODE);

		// Check passcode with file set passcode
		if (!Validations.validatePassword(getInputString(inputScanner))) {

			PrintUtils.insertBreak();
			PrintUtils.print("" + Constants.AUTHORIZATION_ERROR);
			PrintUtils.insertBreak();

			PrintUtils.println("" + Constants.RE_AUTHORIZATION);

			requestPasscode();
		} else {

			PrintUtils.insertBreak();
			PrintUtils.print("" + Constants.AUTHORIZATION_SUCCESSFULL);
			PrintUtils.insertBreak();

			// display category menu
			processAuthSuccess();

		}
	}

	/**
	 *
	 *This method is called after all authrntication processes for admin are done.
	 *
	 *FLow:
	 *instantiate FileOperationsThread with READ_CATEGORIES categories use case.
	 *This will provide output in onCategoriesRead callback.
	 */
	private void processAuthSuccess() {

		FileOperationsThread fOT = new FileOperationsThread(FileOperationTypes.READ_CATEGORIES, this);
		fOT.start();

	}
	/**
	 * 
	 * This method displays all categories and presents chocices for user. This is  single attempt choice menu.
	 * user can select same option or any option only one time.
	 * 
	 * This method presents with following choices.
	 * 1- Display categories with details
	 * 2- exit
	 * 
	 * 
	 * FLOW:
	 * whichever option is seleted by user, if reading from file is requrired then it invokes processCategorySelection method.
	 * 
	 *
	 */
	@Override
	public void displayMenu() {
		PrintUtils.println(Constants.WELCOME_MESSAGE_FOR_USER_ADMIN_PRIOR_STRING + "" + getDisplayName()
				+ Constants.WELCOME_MESSAGE_FOR_USER_ADMIN_POST_STRING);

		PrintUtils.insertBreak();

		// variable to hold user selected option for further options
		int optcategory = 0;

		// do {

		PrintUtils.displayAllCategories(categoriesList);

		// read value from user and set in to "optSignIn" variable
		PrintUtils.print(Constants.YOUR_ANSWER);
		optcategory = getInputInt(inputScanner);

		PrintUtils.insertBreak();

		switch (optcategory) {
		case 1:
			//
			processCategorySelection(1);
			break;

		case 2:
			//
			processCategorySelection(2);
			break;

		case 0:
			PrintUtils.println(Constants.WELCOME_MESSAGE_FOR_USER_ADMIN_POST_STRING + Constants.EXIT + ""
					+ Constants.CATEGORY + Constants.WELCOME_MESSAGE_FOR_USER_ADMIN_POST_STRING);
			PrintUtils.insertBreak();
			break;

		default: {
			// If user enter any number except 1, 2 or 0, it gives warning to enter from
			// selected number
			PrintUtils.println(Constants.OPTION_CATEGOTY_MENU_DEFAULT);
			PrintUtils.insertBreak();
		}
		}
		// } while (optcategory != 0);
	}

	/**
	 * 
	 * This method gets seleted category name from list using getCategoryName method.
	 * 
	 * Flow:
	 * Get category using getCategoryName method.
	 * Display selected category
	 * Start FileOperationsThread class with READ_QUESTIONS use case and category name. The call of displaySubmenu method is required so isMenuRequired is set to true.
	 * 
	 * This thread will return with onQuestionsRead callback.
	 * 
	 *
	 */
	private void processCategorySelection(int selection) {

		String categoryName = getCategoryName(selection);

		PrintUtils.println(Constants.WELCOME_SELECTED_CATEGORY_PRIOR_POST + "" + categoryName
				+ Constants.WELCOME_SELECTED_CATEGORY_PRIOR_POST);

		PrintUtils.insertBreak();
		PrintUtils.insertBreak();

		FileOperationsThread fOT = new FileOperationsThread(FileOperationTypes.READ_QUESTIONS, this, categoryName, null,
				true);
		fOT.start();
	}

	/**
	 *
	 */
	private String getCategoryName(int selection) {

		String ret = "";
		for (int i = 0; i < categoriesList.size(); i++) {

			if (categoriesList.get(i).getForSelection().equalsIgnoreCase(selection + "")) {
				ret = categoriesList.get(i).getName();
			}

		}
		return ret;

	}

	/**
	 * 
	 * This method displays question and score operation choices. This is not single attempt choice menu.
	 * user can select same option or any option as per wish.
	 * 
	 * This method presents with following choices.
	 * 1- add question
	 * 2- modify/update question
	 * 3- delete question
	 * 4- view all questions
	 * 5- view all scores
	 * 6- exit
	 * 
	 * 
	 * FLOW:
	 * whichever option is seleted by user, if reading from file is requrired then it invokes FileOperationsThread with 
	 * approprite use case defined as enum in FileOperationsThread;
	 *
	 */
	private void displaySubmenu(String categoryName) {

		// variable to hold user selected option for further options
		int subCategory = 0;

		do {
			PrintUtils.println(Constants.OPTION_CATEGORY_ADD + categoryName);
			PrintUtils.println(Constants.OPTION_CATEGORY_MODIFY + categoryName);
			PrintUtils.println(Constants.OPTION_CATEGORY_DELETE + categoryName);
			PrintUtils.println(Constants.OPTION_CATEGORY_VIEW + categoryName);
			PrintUtils.println(Constants.OPTION_SCORE_VIEW + categoryName);
			PrintUtils.println(Constants.OPTION_CATEGORY_EXIT + categoryName);

			// read value from user and set in to "optSignIn" variable
			PrintUtils.print(Constants.YOUR_ANSWER);
			subCategory = inputScanner.nextInt();

			PrintUtils.insertBreak();

			switch (subCategory) {
			case 1:
				// To add new question

				// It store question, answer and description for the new one
				QuestionsModel questionsModel;

				// Ask to the admin and store in this model
				questionsModel = createNewQuestion(categoryName);

				// If there is data, proceed to add in the file using file manager
				if (questionsModel != null) {
					
					
					
					FileOperationsThread fOT = new FileOperationsThread(FileOperationTypes.ADD_QUESTION, this,
							categoryName, questionsModel);
					fOT.start();

				}

				break;

			case 2:
				// To modify existing question

				PrintUtils.print(Constants.UPDATE_QUESTION_SELECTION_REQUEST);

				// Get question number to modify
				String selectedQuestionToModify = inputScanner.next();

				// Check user enter valid question number or not. If user add any invalid digit,
				// will show warning
				if (selectedQuestionToModify.equalsIgnoreCase("0")) {
					PrintUtils.insertBreak();
					PrintUtils.print("" + Constants.ERROR_NO_OPTION_AVAILABLE);
					PrintUtils.insertBreak();
					break;
				}

				PrintUtils.insertBreak();
				PrintUtils.print(Constants.ENTER_NEW_DATA_TO_UPDATE);
				PrintUtils.insertBreak();

				// Get new question data and proceed to update data
				questionsModel = createNewQuestion(categoryName);
				questionsModel.setId(selectedQuestionToModify);

				if (questionsModel != null) {
					FileOperationsThread fOT = new FileOperationsThread(FileOperationTypes.UPDATE_QUESTION, this,
							categoryName, questionsModel);
					fOT.start();

				}

				break;

			case 3:
				// To delete existing question

				PrintUtils.print(Constants.DELETE_QUESTION_SELECTION_REQUEST);

				// Get question id to delete
				selectedQuestionToModify = inputScanner.next();

				// Check user enter valid question number or not. If user add any invalid digit,
				// will show warning
				if (selectedQuestionToModify.equalsIgnoreCase("0")) {
					PrintUtils.insertBreak();
					PrintUtils.print("" + Constants.ERROR_NO_OPTION_AVAILABLE);
					PrintUtils.insertBreak();
					break;
				}

				PrintUtils.insertBreak();
				PrintUtils.print(Constants.ENTER_NEW_DATA_TO_UPDATE);
				PrintUtils.insertBreak();

				// Get new question data and proceed to update data
				questionsModel = new QuestionsModel(selectedQuestionToModify + "");

				if (questionsModel != null) {
					FileOperationsThread fOT = new FileOperationsThread(FileOperationTypes.DELETE_QUESTION, this,
							categoryName, questionsModel);
					fOT.start();
				}

				break;

			case 0:
				// Just move to back menu
				PrintUtils.println(Constants.WELCOME_SELECTED_CATEGORY_PRIOR_POST + Constants.EXIT + "" + categoryName
						+ Constants.WELCOME_SELECTED_CATEGORY_PRIOR_POST);
				PrintUtils.insertBreak();
				break;

			case 4:

				FileOperationsThread fOT = new FileOperationsThread(FileOperationTypes.READ_QUESTIONS, this,
						categoryName, null, false);
				fOT.start();
				break;

			case 5:
				PlayerScoreBoardModel model = new PlayerScoreBoardModel();
				model.setCategory(categoryName);
				fOT = new FileOperationsThread(FileOperationTypes.READ_SCOREBOARD, this, model);
				fOT.start();
				break;

			default: {
				// If user enter any number except 1, 2, 3 or 0, it gives warning to enter from
				// selected number
				PrintUtils.println(Constants.OPTION_CATEGOTY_DEFAULT);
				PrintUtils.insertBreak();
			}
			}
		} while (subCategory != 0);
	}

	/**
	 *
	 * This method is called by callback method onQuestionsRead when reading of all
	 * questions from file is compelete.
	 *
	 * FLOW: Check question list size and if zero then display appropriate message
	 * and exit If Questions list is not zero then print all questions using print
	 * util class Obtain category name as return type of displayAllQuestions method
	 * in printutil class. check if sub menu is to be displayed using boolean
	 * isMenuRequired, if yes then call displaySubmenu method else simply print menu
	 * options.
	 *
	 *
	 */
	private void showAllQuestionAnswerData(ArrayList<QuestionsModel> questionsList, boolean isMenuRequired) {
		if (questionsList != null || questionsList.size() != 0) {

			String categoryName = PrintUtils.displayAllQuestions(questionsList);

			if (isMenuRequired) {
				displaySubmenu(categoryName);
			} else {
				PrintUtils.println(Constants.OPTION_CATEGORY_ADD + categoryName);
				PrintUtils.println(Constants.OPTION_CATEGORY_MODIFY + categoryName);
				PrintUtils.println(Constants.OPTION_CATEGORY_DELETE + categoryName);
				PrintUtils.println(Constants.OPTION_CATEGORY_VIEW + categoryName);
				PrintUtils.println(Constants.OPTION_SCORE_VIEW + categoryName);
				PrintUtils.println(Constants.EXIT_CATEGORY_LEVEL);
			}

		} else {

			PrintUtils.print(Constants.TOTAL_QUESTIONS + " = 0");
		}
	}

	/**
	 * This method prints all the scores present in the list using PrintUtils File.
	 * This method check for list size and if zero then prints appropriate message.
	 */
	private void showAllScoreData(ArrayList<PlayerScoreBoardModel> scoreList) {
		if (scoreList != null || scoreList.size() != 0) {
			String categoryName = "";

			PrintUtils.displayAllScores(scoreList);

			PrintUtils.insertBreak(3);
			PrintUtils.println(Constants.OPTION_CATEGORY_ADD + categoryName);
			PrintUtils.println(Constants.OPTION_CATEGORY_MODIFY + categoryName);
			PrintUtils.println(Constants.OPTION_CATEGORY_DELETE + categoryName);
			PrintUtils.println(Constants.OPTION_CATEGORY_VIEW + categoryName);
			PrintUtils.println(Constants.OPTION_SCORE_VIEW + categoryName);
			PrintUtils.println(Constants.EXIT_CATEGORY_LEVEL);

		} else {

			PrintUtils.print(Constants.TOTAL_SCORES + " = 0");
		}
	}

	/**
	 * This method creates and returns object of QuestionsModel class. This method
	 * is used by various processes like update, delete and add questions.
	 *
	 * Flow: Accept question from user Validate for * char. Accept answer from user.
	 * Validate answer if is 'T' or 'F' Invoke Constructor of QuestionsModel with
	 * the parameters. Return the object.
	 *
	 * Details category is provided as parameter and Id is left blank. ID is left
	 * blank because at the time of writing it to file, unique string will be
	 * generated and the object returned by this method will updated with ID.
	 */
	private QuestionsModel createNewQuestion(String category) {
		PrintUtils.print(Constants.ENTER_QUESTION);

		// get new question and due to chance of space char present in question,
		// nextline method is also used.
		String newQuestion = inputScanner.next();
		newQuestion += inputScanner.nextLine();

		// * is not allowed because all data is split using this special character in
		// local file.
		if (newQuestion.trim().contains("*")) {
			PrintUtils.print("1 " + Constants.ERROR_LOGICAL);

			return null;
		}

		PrintUtils.print(Constants.ANSWER_THE_QUESTION + " " + newQuestion + " : ");

		// get its answer
		String newAnswer = inputScanner.next();
		newAnswer += inputScanner.nextLine();

		// answer must be in T or F. If admin writes anything than these, shows
		// warning and ask once again
		if (newAnswer.equalsIgnoreCase("T") || newAnswer.equalsIgnoreCase("F")) {

			// If we have all the required information just return it to the calling method
			return new QuestionsModel(newQuestion, newAnswer, category, false);

		} else {
			// Return null, if there is any problem
			PrintUtils.insertBreak();
			PrintUtils.print(Constants.OPTION_TRUE_OR_FALSE);
			PrintUtils.insertBreak();

			return null;
		}
	}

	/**
	 * This is callback invoked by FileOperationsThread when categories are read
	 * from file. This callback calls displayMenu method to display choices for
	 * user. This also sets global variable categoriesList;
	 */
	@Override
	public void onCategoriesRead(ArrayList<CategoryModel> categoriesList) {
		this.categoriesList = categoriesList;
		displayMenu();

	}

	/**
	 * This is callback invoked by FileOperationsThread when questions are read from
	 * file. This callback calls showAllQuestionAnswerData method to display the
	 * questions for user. The boolean isMenuRequired is provided by This class to
	 * FileOperationsThread while creating its object. The use of this boolean is to
	 * make sure that questions menu is displayed withoud invoking Scanner class as
	 * its alread in loop.
	 */
	@Override
	public void onQuestionsRead(ArrayList<QuestionsModel> questionsList, boolean isMenuRequired) {
		showAllQuestionAnswerData(questionsList, isMenuRequired);

	}

	/**
	 *
	 */
	@Override
	public void onScoreAdded(PlayerScoreBoardModel model) {
		// TODO Auto-generated method stub

	}

	/**
	 * This is callback invoked by FileOperationsThread when scores are read from
	 * file. This callback calls showAllScoreData method to display the scores for
	 * user.
	 */
	@Override
	public void onScoresRead(ArrayList<PlayerScoreBoardModel> scoreList) {
		showAllScoreData(scoreList);
	}

}
