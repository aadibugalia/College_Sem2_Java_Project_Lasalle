/**
 * 
 */
package models;


/**
 * @author Aditya Bugalia
 * 
 * Container class for Scores
 *
 */
public class PlayerScoreBoardModel {
	
	
	private String displayName="";
	private String userEmail="";
	private String category="";
	private int correctAnswerCount=0;
	private int wrongAnswerCount=0;
	private String id="";
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public PlayerScoreBoardModel(java.lang.String displayName, java.lang.String userEmail, java.lang.String category,
			int correctAnswerCount, int wrongAnswerCount) {
		super();
		this.displayName = displayName;
		this.userEmail = userEmail;
		this.category = category;
		this.correctAnswerCount = correctAnswerCount;
		this.wrongAnswerCount = wrongAnswerCount;
	}

	/**
	 * 
	 */
	public PlayerScoreBoardModel() {
		// TODO Auto-generated constructor stub
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCorrectAnswerCount() {
		return correctAnswerCount;
	}

	public void setCorrectAnswerCount(int correctAnswerCount) {
		this.correctAnswerCount = correctAnswerCount;
	}

	public int getWrongAnswerCount() {
		return wrongAnswerCount;
	}

	public void setWrongAnswerCount(int wrongAnswerCount) {
		this.wrongAnswerCount = wrongAnswerCount;
	}
	
	

}
