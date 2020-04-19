/**
 * 
 */
package models;

/**
 * @author Aditya Bugalia
 * Container class for Question Categories
 */
public class CategoryModel {
	
	private String name ="", desc="", forSelection="";;

	public String getForSelection() {
		return forSelection;
	}

	public void setForSelection(String forSelection) {
		this.forSelection = forSelection;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
