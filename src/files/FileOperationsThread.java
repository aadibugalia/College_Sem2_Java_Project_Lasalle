/**
 * 
 */
package files;

import java.util.ArrayList;

import callbacks.FileOperationCallbacks;

import models.CategoryModel;
import models.PlayerScoreBoardModel;
import models.QuestionsModel;

/**
 * @author Aditya Bugalia
 * 
 * This class is designed not to block the user interaction thread. with small size of data user does not experience lag but when the data is large.\
 * ..seperate thread is important. Since the data is structured as JSON, this gives capability to have thousands of questions at the same time in different categories.
 * This class, handles the file reading operations from FileOperation Class on seperate thread and gives optimal user experience to player and admin.
 * 
 */
public class FileOperationsThread extends Thread {
	
	
	private FileOperationCallbacks mfileOperationCallbacks ;

	
	public enum FileOperationTypes {
		
		READ_CATEGORIES,
		GET_CATEGORYNAME,
		READ_QUESTIONS,
		DELETE_QUESTION,
		ADD_QUESTION,
		UPDATE_QUESTION,
		UPDATE_PASSWORD,
		UPDATE_SCOREBOARD,
		READ_SCOREBOARD
		
	}
	
	private FileOperationTypes mFileOperationType;
	private String mCategory;
	private QuestionsModel mQuestionsModel;
	private PlayerScoreBoardModel mPlayerScoreBoardModel;
	
	private ArrayList<CategoryModel> categoriesList;
	private ArrayList<QuestionsModel> questionsList;
	private ArrayList<PlayerScoreBoardModel> scoreList;
	private boolean isMenuRequired;
	
	
	public FileOperationsThread(FileOperationTypes fileOperationType, FileOperationCallbacks fileOperationCallbacks){
		this.mFileOperationType = fileOperationType;
		this.mfileOperationCallbacks =fileOperationCallbacks;
	
	}
	
	public FileOperationsThread(FileOperationTypes fileOperationType, FileOperationCallbacks fileOperationCallbacks, String category, QuestionsModel model){
		this.mFileOperationType = fileOperationType;
		this.mfileOperationCallbacks =fileOperationCallbacks;
		this.mCategory= category;
		this.mQuestionsModel = model;
	
	}
	
	public FileOperationsThread(FileOperationTypes fileOperationType, FileOperationCallbacks fileOperationCallbacks, PlayerScoreBoardModel model){
		this.mFileOperationType = fileOperationType;
		this.mfileOperationCallbacks =fileOperationCallbacks;
		
		this.mPlayerScoreBoardModel = model;
	
	}
	public FileOperationsThread(FileOperationTypes fileOperationType, FileOperationCallbacks fileOperationCallbacks, String category, QuestionsModel model, boolean isMenuRequired){
		this.mFileOperationType = fileOperationType;
		this.mfileOperationCallbacks =fileOperationCallbacks;
		this.mCategory= category;
		this.mQuestionsModel = model;
		this.isMenuRequired=isMenuRequired;
	
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		FileOperations m = new FileOperations();
		
		switch(mFileOperationType) {
		
		case READ_CATEGORIES:
			categoriesList= new ArrayList<CategoryModel>();
			categoriesList=m.readCategories();
			
			break;
			
		case READ_QUESTIONS:
			questionsList= new ArrayList<QuestionsModel>();
			questionsList=m.readQuestions(mCategory);
			break;
			
		case READ_SCOREBOARD:
			
			
			scoreList= new ArrayList<PlayerScoreBoardModel>() ;
			scoreList=m.readScores(mPlayerScoreBoardModel.getCategory());
			break;
			
		case ADD_QUESTION:
			
			questionsList= new ArrayList<QuestionsModel>();
			questionsList=m.addQuestion(mQuestionsModel);
			break;
			
	case UPDATE_QUESTION:
			
			questionsList= new ArrayList<QuestionsModel>();
			questionsList=m.updateQuestion(mQuestionsModel);
			break;
			
	case DELETE_QUESTION:
		
		questionsList= new ArrayList<QuestionsModel>();
		questionsList=m.deleteQuestion(mQuestionsModel);
		break;
			
	case UPDATE_SCOREBOARD:
		
		
		mPlayerScoreBoardModel=m.addScore(mPlayerScoreBoardModel);
		break;
			
		default:
			break;
		
		
		}
		
		
		postMessage();
		
		
	}
	
	
	private void postMessage() {
		
switch(mFileOperationType) {
		
		case READ_CATEGORIES:
			mfileOperationCallbacks.onCategoriesRead(categoriesList);
			break;
		case READ_QUESTIONS:
			mfileOperationCallbacks.onQuestionsRead(questionsList, isMenuRequired);
			break;
		case READ_SCOREBOARD:
			mfileOperationCallbacks.onScoresRead(scoreList);
			break;
		case ADD_QUESTION:
			//mfileOperationCallbacks.onQuestionsRead(questionsList);
			break;
		case UPDATE_SCOREBOARD:
			mfileOperationCallbacks.onScoreAdded(mPlayerScoreBoardModel);
			break;
			
default:
	break;
}
		
	}
	
	
	

}
