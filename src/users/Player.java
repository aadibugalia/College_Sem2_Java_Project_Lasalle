/**
 * 
 */
package users;

import java.util.ArrayList;
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
 * 
 * This class is type of player user.
 *
 */
public class Player  extends Person implements FileOperationCallbacks {
	
	
	private ArrayList<CategoryModel> categoriesList;
	
	// Keep track of current question of any category.
	private int currentQuestionIndex = 0;
	
	// Keep the information save for player questionaire
	private PlayerScoreBoardModel scoreModel = new PlayerScoreBoardModel();

	// Allows player to input data
	private Scanner inputScanner = new Scanner(System.in);
	
	

	
	/**
	 * @purpose Display the category to start question answer game. Player has to select any one listed category to proceed further.
	 * @param - not available
	 * 
	 * START
	Variables A,B,C, ans, correctAns = 0, wrongAns = 0, P

	WRITE “Enter Display Name: ”
	READ A
	WRITE “Enter email: ”
	READ B
	


	READ C

	DO
		CASE C = 1:

			FOR index FROM 0 To 5 INCREMENT
				WRITE “Que index”

				READ ans
					IF ans == “No”
						WRITE “Correct Answer”
						wrongAns++
					ELSE
						correctAns++
					END IF
			END INCREMENT

			WRITE “Total Correct Answers: ” + correctAns
			WRITE “TOTAL Wrong Answers: ” + wrongAns

			P <- (correctAns * 100) / (correctAns + wrongAns)

			WRITE P

		CASE C = 2: 

			FOR index FROM 0 To 5 INCREMENT
				WRITE “Que index”

				READ ans
					IF ans == “No”
						WRITE “Correct Answer”
						wrongAns++
					ELSE
						correctAns++
					END IF
			END INCREMENT

			WRITE “Total Correct Answers: ” + correctAns
			WRITE “TOTAL Wrong Answers: ” + wrongAns

			P <- (correctAns * 100) / (correctAns + wrongAns)

			WRITE P

		CASE C = 0: 
			END
			
	END WHILE C != 0

END
	 */
	@Override
	public void displayMenu() {
		PrintUtils.insertBreak();
		PrintUtils.println(Constants.WELCOME_MESSAGE_FOR_USER_ADMIN_PRIOR_STRING + "" + getDisplayName()
				+ Constants.WELCOME_MESSAGE_FOR_USER_ADMIN_POST_STRING);

		PrintUtils.insertBreak();

		// variable to hold user selected option for further options
		int optcategory = 0;

	//	do {
			PrintUtils.displayAllCategories(categoriesList);
			

			// read value from user and set in to "optSignIn" variable
			PrintUtils.print(Constants.YOUR_ANSWER);
			optcategory =inputScanner.nextInt();

			PrintUtils.insertBreak();

			switch (optcategory) {
			case 1:
				processCategorySelection(1);
				// Default player is at first question. So it is initialized as 1.
				currentQuestionIndex = 0;
				
			
			

			

				break;

			case 2:
				processCategorySelection(2);
				// Default player is at first question. So it is initialized as 1.
				currentQuestionIndex = 0;
				
				break;

			case 0:
				// To exist from the category
				PrintUtils.println(Constants.WELCOME_MESSAGE_FOR_USER_ADMIN_POST_STRING + Constants.EXIT
						+ "" + Constants.CATEGORY + Constants.WELCOME_MESSAGE_FOR_USER_ADMIN_POST_STRING);
				PrintUtils.insertBreak();
				break;

			default: {
				// If user enter any number except 1, 2 or 0, it gives warning to enter from
				// selected number
				PrintUtils.println(Constants.OPTION_CATEGOTY_MENU_DEFAULT);
				PrintUtils.insertBreak();
			}
			}
		//} while (optcategory != 0);
	}

	/**
	 * @purpose Present question 1 by 1.
	 * @param - ArrayList<QuestionsModel> list of all available question to present to the user
	 * 
	 * 
	 * START
	 * 
	 * FOR index FROM 0 To 5 INCREMENT
				WRITE “Que index”

				READ ans
					IF ans == “No”
						WRITE “Correct Answer”
						WRITE "des"
						wrongAns++
						
					ELSE IF ans  == "Yes"
						correctAns++
					END ELSE IF
					
					ELSE
						WRITE "ONLY Yes and No ans is allowed"
					END ELSE
					
					END IF
			END INCREMENT
	 * 
	 * END
	 * 
	 */
	
private void processCategorySelection(int selection) {
		
		String categoryName = getCategoryName(selection);

		PrintUtils.println(Constants.WELCOME_SELECTED_CATEGORY_PRIOR_POST + "" + categoryName
				+ Constants.WELCOME_SELECTED_CATEGORY_PRIOR_POST);

		PrintUtils.insertBreak();
		PrintUtils.insertBreak();
		
		// Default player has 0 correct answer and wrong answer.
		//So, it initialize score model to store temporary data for player.
		scoreModel = new PlayerScoreBoardModel(getDisplayName(), getEmailId(), categoryName, 0, 0);

		
		FileOperationsThread fOT = new FileOperationsThread(FileOperationTypes.READ_QUESTIONS,this,categoryName,null, true);
		fOT.start();
	}

private String getCategoryName(int selection) {

	String ret = "";
	for (int i = 0; i < categoriesList.size(); i++) {

		if (categoriesList.get(i).getForSelection().equalsIgnoreCase(selection + "")) {
			ret = categoriesList.get(i).getName();
		}

	}
	return ret;

}
	private void showQuestionOneByOne(ArrayList<QuestionsModel> arrAllQuestionAnswers) {
		for (int i = 0; i < arrAllQuestionAnswers.size(); i++) {

			// If user enter any invalid answer (For example, it only takes "yes" or "no"), it gives error and ask question from the last one. 
			// So this condition checks where user were at last time using currentQuestionINdex
			if ((currentQuestionIndex ) <= i) {
				QuestionsModel QuestionsModel = arrAllQuestionAnswers.get(i);

				PrintUtils.println(Constants.QUESTION + " " + (i + 1));
				PrintUtils.println("# " + QuestionsModel.getName() + " ");
				PrintUtils.print(Constants.ANSWER);

				// Get answer from the player and store in the variable
				String playerAnswer = inputScanner.next();
				playerAnswer += inputScanner.nextLine();
				playerAnswer = playerAnswer.trim();

				// Check what player has entered. If there are any other answer than "T" or "F", it will give you warning and ask same question once again.
				if (playerAnswer.equalsIgnoreCase("T") || playerAnswer.equalsIgnoreCase("F")) {

					// Increase counter for next question
					currentQuestionIndex++;

					// Check answer and decide it will be correct or wrong
					if (playerAnswer.equalsIgnoreCase(QuestionsModel.getCorrectAns())) {
						// Increase correct answer count
						scoreModel.setCorrectAnswerCount(scoreModel.getCorrectAnswerCount() + 1);

						PrintUtils.insertBreak();
						PrintUtils.println(Constants.CORRECT_ANSWER);
						PrintUtils.insertBreak();
					} else {
						// Increase wrong answer count
						scoreModel.setWrongAnswerCount(scoreModel.getWrongAnswerCount() + 1);

						// If is is wrong, present correct answer to the user
						PrintUtils.insertBreak();
						PrintUtils.println(Constants.WRONG_ANSWER);
						PrintUtils.println(
								Constants.CORRECT_ANSWER_IS + "\"" + QuestionsModel.getCorrectAns() + "\".");
						
						PrintUtils.insertBreak();
					}

				} else {
					// Shows warning that only "yes" or "no" is accepted
					PrintUtils.insertBreak();
					PrintUtils.println(Constants.OPTION_TRUE_OR_FALSE);
					PrintUtils.insertBreak();

					break;
				}
			}
		}
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
public void processInitialGameSetup() {
	FileOperationsThread fOT = new FileOperationsThread(FileOperationTypes.READ_CATEGORIES,this);
	fOT.start();
	
	
}

public void requestDisplayname() {
	PrintUtils.print("" + Constants.AUTHORIZATION_NAME);

	this.displayName = inputScanner.next();
	this.displayName += inputScanner.nextLine();

	boolean isValidDisplayname = Validations.validateDisplayName(this.displayName);

	if (!isValidDisplayname) {
		getCorrectDisplayName();
	}
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

	@Override
	public void onCategoriesRead(ArrayList<CategoryModel> categoriesList) {
		this.categoriesList = categoriesList;
		displayMenu();
		
	}

	@Override
	public void onQuestionsRead(ArrayList<QuestionsModel> questionsList, boolean isMenuRequired) {
		PrintUtils.insertBreak();
		PrintUtils.insertBreak();
		PrintUtils.print(Constants.TITLE_TOTAL_QUESTIONS, "" + questionsList.size());
		PrintUtils.insertBreak();

		// Keep trak and present questions one by one
		while (currentQuestionIndex < questionsList.size()) {
			showQuestionOneByOne(questionsList);
		}

		// Trigger thread to save and shows score to user 
		
		FileOperationsThread fOT = new FileOperationsThread(FileOperationTypes.UPDATE_SCOREBOARD,this,scoreModel);
		fOT.start();
		
		
	}

	@Override
	public void onScoreAdded(PlayerScoreBoardModel model) {
		PrintUtils.displayScore(model);
		
	}

	@Override
	public void onScoresRead(ArrayList<PlayerScoreBoardModel> scoreList) {
		// TODO Auto-generated method stub
		
	}

}

