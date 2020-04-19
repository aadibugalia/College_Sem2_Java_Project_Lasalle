/**
 * 
 */
package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import common.Constants;
import models.CategoryModel;
import models.PlayerScoreBoardModel;
import models.QuestionsModel;

/**
 * @author Aditya Bugalia
 *
 */
class FileOperations {

	protected ArrayList<CategoryModel> readCategories() {

		ArrayList<CategoryModel> mCategories = new ArrayList<CategoryModel>();
		;
		JSONParser parser = new JSONParser();
		JSONObject mParentObj, mCategoryObj;
		Object obj = null;
		JSONArray categoryListArr;
		CategoryModel mCategoryModel = new CategoryModel();
		;

		try {
			obj = parser.parse(new FileReader(Constants.CATEGORIES_FILE_PATH));

			mParentObj = (JSONObject) obj;

			categoryListArr = (JSONArray) mParentObj.get("category");
			Iterator<JSONObject> iterator = categoryListArr.iterator();
			while (iterator.hasNext()) {
				// System.out.println(iterator.next());

				mCategoryObj = (JSONObject) iterator.next();

				mCategoryModel.setDesc(mCategoryObj.get("desc").toString());
				mCategoryModel.setName(mCategoryObj.get("name").toString());
				mCategoryModel.setForSelection(mCategoryObj.get("selection").toString());
				mCategories.add(mCategoryModel);
				mCategoryModel = new CategoryModel();

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return mCategories;

	}

	private void createFileBaseQuestions() {
		try {

			File file = new File(Constants.QUESTIONS_FILE_PATH);
			if (file.exists()) {
				file.delete();
			}

			JSONObject jsonobj = new JSONObject();
			JSONArray jarr = new JSONArray();
			jsonobj.put("questions", jarr);

			Files.write(Paths.get(Constants.QUESTIONS_FILE_PATH), jsonobj.toJSONString().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected ArrayList<QuestionsModel> readQuestions(String requiredCategory) {

		ArrayList<QuestionsModel> mQuestionsList = new ArrayList<QuestionsModel>();
		;
		JSONParser parser = new JSONParser();
		JSONObject mParentObj, mQuestionsObj;
		Object obj = null;
		JSONArray questionsListArr;
		QuestionsModel mQuestionsModel = new QuestionsModel();
		;

		try {
			obj = parser.parse(new FileReader(Constants.QUESTIONS_FILE_PATH));

			mParentObj = (JSONObject) obj;
			if (mParentObj == null) {

				createFileBaseQuestions();
				obj = parser.parse(new FileReader(Constants.QUESTIONS_FILE_PATH));

				mParentObj = (JSONObject) obj;
			}

			questionsListArr = (JSONArray) mParentObj.get("questions");
			Iterator<JSONObject> iterator = questionsListArr.iterator();
			while (iterator.hasNext()) {
				// System.out.println(iterator.next());

				mQuestionsObj = (JSONObject) iterator.next();
				if (mQuestionsObj.get("category").equals(requiredCategory)) {

					mQuestionsModel.setCorrectAns(mQuestionsObj.get("correctAns").toString());
					mQuestionsModel.setName(mQuestionsObj.get("name").toString());
					mQuestionsModel.setId(mQuestionsObj.get("id").toString());
					mQuestionsModel.setCategory(mQuestionsObj.get("category").toString());

					mQuestionsList.add(mQuestionsModel);
					mQuestionsModel = new QuestionsModel();
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return mQuestionsList;

	}

	protected ArrayList<PlayerScoreBoardModel> readScores(String requiredCategory) {

		ArrayList<PlayerScoreBoardModel> mScoreList = new ArrayList<PlayerScoreBoardModel>();
		;
		JSONParser parser = new JSONParser();
		JSONObject mParentObj, mQuestionsObj;
		Object obj = null;
		JSONArray scoreListArr;
		PlayerScoreBoardModel mPlayerScoreBoardModel = new PlayerScoreBoardModel();
		;

		try {
			obj = parser.parse(new FileReader(Constants.SCORES_FILE_PATH));

			mParentObj = (JSONObject) obj;
			
			scoreListArr = (JSONArray) mParentObj.get("scores");
			Iterator<JSONObject> iterator = scoreListArr.iterator();
			while (iterator.hasNext()) {
				// System.out.println(iterator.next());

				mQuestionsObj = (JSONObject) iterator.next();
				if (mQuestionsObj.get("category").equals(requiredCategory)) {
					mPlayerScoreBoardModel.setId(mQuestionsObj.get("id").toString());
					mPlayerScoreBoardModel.setCategory(mQuestionsObj.get("category").toString());
					mPlayerScoreBoardModel.setDisplayName(mQuestionsObj.get("displayName").toString());
					mPlayerScoreBoardModel.setUserEmail(mQuestionsObj.get("email").toString());
					mPlayerScoreBoardModel.setCorrectAnswerCount(Integer.parseInt(mQuestionsObj.get("correctAnsCount").toString()));
					mPlayerScoreBoardModel.setWrongAnswerCount(Integer.parseInt(mQuestionsObj.get("wrongAnsCount").toString()));

					mScoreList.add(mPlayerScoreBoardModel);
					mPlayerScoreBoardModel = new PlayerScoreBoardModel();
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return mScoreList;

	}

	protected ArrayList<QuestionsModel> updateQuestion(QuestionsModel mModel) {

		ArrayList<QuestionsModel> mQuestionsList = new ArrayList<QuestionsModel>();
		;
		JSONParser parser = new JSONParser();
		JSONObject mParentObj, mQuestionsObj;
		Object obj = null;
		JSONArray questionsListArr;
		QuestionsModel mQuestionsModel = new QuestionsModel();
		;
		FileReader fr;

		try {

			fr = new FileReader(Constants.QUESTIONS_FILE_PATH);
			obj = parser.parse(fr);

			mParentObj = (JSONObject) obj;

			if (mParentObj == null) {

				createFileBaseQuestions();
				obj = parser.parse(fr);

				mParentObj = (JSONObject) obj;
			}

			questionsListArr = (JSONArray) mParentObj.get("questions");

			for (int i = 0; i < questionsListArr.size(); i++) {

				JSONObject temp = (JSONObject) questionsListArr.get(i);
				if ((temp.get("id").toString().equalsIgnoreCase(mModel.getId()))) {

					questionsListArr.remove(i);

					JSONObject newObj = new JSONObject();

					newObj.put("name", mModel.getName());
					newObj.put("id", mModel.getId());
					newObj.put("category", temp.get("category").toString());
					newObj.put("correctAns", mModel.getCorrectAns());
					questionsListArr.add(newObj);

				}
			}

			fr.close();

			File file = new File(Constants.QUESTIONS_FILE_PATH);
			if (file.exists()) {
				file.delete();
			}

			Files.write(Paths.get(Constants.QUESTIONS_FILE_PATH), mParentObj.toJSONString().getBytes());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return mQuestionsList;

	}

	protected ArrayList<QuestionsModel> addQuestion(QuestionsModel mModel) {

		ArrayList<QuestionsModel> mQuestionsList = new ArrayList<QuestionsModel>();
		;
		JSONParser parser = new JSONParser();
		JSONObject mParentObj, mQuestionsObj;
		Object obj = null;
		JSONArray questionsListArr;
		QuestionsModel mQuestionsModel = new QuestionsModel();
		;
		FileReader fr;

		try {

			fr = new FileReader(Constants.QUESTIONS_FILE_PATH);
			obj = parser.parse(fr);

			mParentObj = (JSONObject) obj;
			if (mParentObj == null) {

				createFileBaseQuestions();
				obj = parser.parse(fr);

				mParentObj = (JSONObject) obj;
			}

			questionsListArr = (JSONArray) mParentObj.get("questions");

			JSONObject newObj = new JSONObject();

			newObj.put("name", mModel.getName());

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String id = (dtf.format(now).replace("/", "").replace(":", "").replace(" ", "")).trim();

			if (id.contains(" ")) {
				id = id.split("\\ ")[0] + id.split("\\ ")[1];
			}
			newObj.put("id", id);
			newObj.put("category", mModel.getCategory());
			newObj.put("correctAns", mModel.getCorrectAns());
			questionsListArr.add(newObj);

			fr.close();

			File file = new File(Constants.QUESTIONS_FILE_PATH);
			if (file.exists()) {
				file.delete();
			}

			Files.write(Paths.get(Constants.QUESTIONS_FILE_PATH), mParentObj.toJSONString().getBytes());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return mQuestionsList;

	}

	protected ArrayList<QuestionsModel> deleteQuestion(QuestionsModel mModel) {

		ArrayList<QuestionsModel> mQuestionsList = new ArrayList<QuestionsModel>();
		;
		JSONParser parser = new JSONParser();
		JSONObject mParentObj, mQuestionsObj;
		Object obj = null;
		JSONArray questionsListArr;
		QuestionsModel mQuestionsModel = new QuestionsModel();
		;
		FileReader fr;

		try {

			fr = new FileReader(Constants.QUESTIONS_FILE_PATH);
			obj = parser.parse(fr);

			mParentObj = (JSONObject) obj;
			if (mParentObj == null) {

				createFileBaseQuestions();
				obj = parser.parse(fr);

				mParentObj = (JSONObject) obj;
			}

			questionsListArr = (JSONArray) mParentObj.get("questions");

			for (int i = 0; i < questionsListArr.size(); i++) {

				JSONObject temp = (JSONObject) questionsListArr.get(i);
				if ((temp.get("id").toString().equalsIgnoreCase(mModel.getId().toString()))) {

					questionsListArr.remove(i);

//		    JSONObject newObj = new JSONObject();
//		    
//		    newObj.put("name", mModel.getName());
//			    newObj.put("id", mModel.getId());
//			    newObj.put("category", temp.get("category").toString());
//			    newObj.put("correctAns", mModel.getCorrectAns());
//				questionsListArr.add(newObj);

				}
			}

			fr.close();

			File file = new File(Constants.QUESTIONS_FILE_PATH);
			if (file.exists()) {
				file.delete();
			}

			Files.write(Paths.get(Constants.QUESTIONS_FILE_PATH), mParentObj.toJSONString().getBytes());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return mQuestionsList;

	}
	protected PlayerScoreBoardModel addScore(PlayerScoreBoardModel mModel) {

		ArrayList<PlayerScoreBoardModel> mScoreList = new ArrayList<PlayerScoreBoardModel>();
		;
		JSONParser parser = new JSONParser();
		JSONObject mParentObj, mQuestionsObj;
		Object obj = null;
		JSONArray scoreListArr;
		QuestionsModel mQuestionsModel = new QuestionsModel();
		;
		FileReader fr;

		try {

			fr = new FileReader(Constants.SCORES_FILE_PATH);
			obj = parser.parse(fr);

			mParentObj = (JSONObject) obj;
			if (mParentObj == null) {

				createFileBaseQuestions();
				obj = parser.parse(fr);

				mParentObj = (JSONObject) obj;
			}

			scoreListArr = (JSONArray) mParentObj.get("scores");

			JSONObject newObj = new JSONObject();
			
			
		

			newObj.put("displayName", mModel.getDisplayName());

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String id = (dtf.format(now).replace("/", "").replace(":", "").replace(" ", "")).trim();

			if (id.contains(" ")) {
				id = id.split("\\ ")[0] + id.split("\\ ")[1];
			}
			newObj.put("id", id);
			newObj.put("category", mModel.getCategory());
			newObj.put("email", mModel.getUserEmail());
			newObj.put("correctAnsCount", mModel.getCorrectAnswerCount());
			newObj.put("wrongAnsCount", mModel.getWrongAnswerCount());
			scoreListArr.add(newObj);

			fr.close();

			File file = new File(Constants.SCORES_FILE_PATH);
			if (file.exists()) {
				file.delete();
			}

			Files.write(Paths.get(Constants.SCORES_FILE_PATH), mParentObj.toJSONString().getBytes());

		} catch (Exception e) {

			e.printStackTrace();
		}

		return mModel;

	}

//public static void main(String[] args) 
//{
//	QuestionsModel model = new QuestionsModel();
//	model.setCorrectAns("updated anshhhhh");
//	model.setDelete(false);
//	model.setId("Q2");
//	model.setName("updated question hhhhhhh");
//	
//	updateQuestion(model);
//	
//	
//
//}

}
