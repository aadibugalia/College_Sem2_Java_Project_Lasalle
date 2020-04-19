/**
 * 
 */
package callbacks;

import java.util.ArrayList;

import models.CategoryModel;
import models.PlayerScoreBoardModel;
import models.QuestionsModel;

/**
 * @author Aditya Bugalia
 *This interface is crucial for posting the results for FileOperations. It is used by Both Admin and Player class.
 */
public interface FileOperationCallbacks {
	
	
	// when category read is complete from file
	void onCategoriesRead(ArrayList<CategoryModel> categoriesList);
	// when questions read is complete from file
	void onQuestionsRead(ArrayList<QuestionsModel> questionsList, boolean isMenuRequired);
	// when score is written to file. It returns same model object provided for display.
	void onScoreAdded(PlayerScoreBoardModel model);
	// when scores read is complete from file
	void onScoresRead(ArrayList<PlayerScoreBoardModel> scoreList);

}
