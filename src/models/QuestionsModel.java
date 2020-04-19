/**
 * 
 */
package models;

/**
 * @author Aditya Bugalia
 * 
 * Container class for Questions s
 *
 */
public class QuestionsModel {
	
	private String name = "", correctAns="", id="", category="";
	
	
	


	/**
	 * @param newQuestion
	 * @param newAnswer
	 * @param category2
	 * @param isDelete2
	 */
	public QuestionsModel(String newQuestion, String newAnswer, String category, boolean isDelete) {
		this.name = newQuestion;
		this.correctAns = newAnswer;
		this.category = category;
		this.isDelete = isDelete;
	}

	public QuestionsModel(String id) {
		this.id=id;
	}
	public QuestionsModel() {}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	private boolean isDelete = false;
	
	

	public boolean isDelete() {
		return isDelete;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCorrectAns() {
		return correctAns;
	}

	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}

}
