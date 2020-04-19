/**
 * 
 */
package common;

import java.util.ArrayList;

import models.CategoryModel;
import models.PlayerScoreBoardModel;
import models.QuestionsModel;

/**
 * @author Aditya Bugalia
 *
 *This class provides static methods to display data on screen in various formats. 
 *
 *
 */
public class PrintUtils {

	public static void print(String message) {
		System.out.print("" + message);
	}

	public static void print(String message, String substring) {
		System.out.printf("" + message, substring);
	}

	public static void println(String message) {
		System.out.println("" + message);
	}

	public static void println(String message, String substring) {
		System.out.printf("" + message, substring);
		insertBreak();
	}

	public static void insertBreak() {
		System.out.println("\n");
	}

	public static void insertBreak(int numberOfLines) {
		for (int i = 0; i < numberOfLines; i++) {
			insertBreak();
		}
	}

	public static void displayAllCategories(ArrayList<CategoryModel> categoriesList) {

		insertBreak();
		System.out.printf("==================== Categories  ====================");
		insertBreak();
		insertBreak();

		for (int i = 0; i < categoriesList.size(); i++) {
			insertBreak();
			CategoryModel model = categoriesList.get(i);
			print(Constants.NAME_OF_CATEGORY + model.getName());
			insertBreak();
			print(Constants.DESC_OF_CATEGORY + model.getDesc());
			insertBreak();
			print(Constants.SELECTION_OF_CATEGORY + model.getForSelection());
			insertBreak();
			insertBreak();

		}
		insertBreak();
		print(Constants.OPTION_CATEGORY_MENU_EXIT);
		insertBreak();
		System.out.printf("==================== XXXXXXXXXX  ====================");
		insertBreak();
	}
	
	
	public static String displayAllQuestions(ArrayList<QuestionsModel> questionsList) {

		insertBreak();
		
		insertBreak();

		String categoryName="";
		for (int i = 0; i < questionsList.size(); i++) {
			QuestionsModel questionsModel = questionsList.get(i);

			
			PrintUtils.println(Constants.QUESTION + questionsModel.getName());
			PrintUtils.println(Constants.ANSWER + questionsModel.getCorrectAns());
			PrintUtils.println(Constants.QUESTIONID + questionsModel.getId());
			categoryName= questionsModel.getCategory();

			PrintUtils.insertBreak();
			
			
		}
		insertBreak();
		
		return categoryName;
		
	}

	public static void displayAllScores(ArrayList<PlayerScoreBoardModel> scoreList) {

		insertBreak();
		System.out.printf("==================== Score List  ====================");
		insertBreak();
		insertBreak();

		for (int i = 0; i < scoreList.size(); i++) {
			insertBreak();
			PlayerScoreBoardModel model = scoreList.get(i);
			insertBreak();

			print(Constants.AUTHORIZATION_NAME + " : " + model.getDisplayName());
			insertBreak();
			print(Constants.AUTHORIZATION_EMAIL_ID + " : " + model.getUserEmail());
			insertBreak();
			print(Constants.CATEGORY + " : " + model.getCategory());
			insertBreak();
			print(Constants.TOTAL_CORRECT_ANSWER + " : " + model.getCorrectAnswerCount());
			insertBreak();
			print(Constants.TOTAL_WRONG_ANSWER + " : " + model.getWrongAnswerCount());
			insertBreak();
			insertBreak();

		}
		insertBreak();
		print(Constants.EXIT_CATEGORY_LEVEL);
		insertBreak();
		System.out.printf("==================== XXXXXXXXXX  ====================");
		insertBreak();
	}

	public static void displayScore(PlayerScoreBoardModel model) {

		insertBreak();
		System.out.printf("==================== Final Score  ====================");
		insertBreak();
		insertBreak();

		insertBreak();

		print(Constants.AUTHORIZATION_NAME + " : " + model.getDisplayName());
		insertBreak();
		print(Constants.AUTHORIZATION_EMAIL_ID + " : " + model.getUserEmail());
		insertBreak();
		print(Constants.CATEGORY + " : " + model.getCategory());
		insertBreak();
		print(Constants.TOTAL_CORRECT_ANSWER + " : " + model.getCorrectAnswerCount());
		insertBreak();
		print(Constants.TOTAL_WRONG_ANSWER + " : " + model.getWrongAnswerCount());
		insertBreak();

		insertBreak();
		// print(Constants.OPTION_CATEGORY_MENU_EXIT);
		insertBreak();
		System.out.printf("==================== XXXXXXXXXX  ====================");
		insertBreak();
	}
}
