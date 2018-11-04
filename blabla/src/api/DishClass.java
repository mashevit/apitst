package api;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

//import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class DishClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	//@JsonProperty("dishName")
	private String dishName;

	public DishClass() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

}
