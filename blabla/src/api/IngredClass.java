package api;

public class IngredClass {
	private static final long serialVersionUID = 1L;

	private int id;
	//@JsonProperty("dishName")
	private String ingrName;
	private int numOfDishes;
	public int getNumOfDishes() {
		return numOfDishes;
	}
	public void setNumOfDishes(int numOfDishes) {
		this.numOfDishes = numOfDishes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIngrName() {
		return ingrName;
	}
	public void setIngrName(String ingrName) {
		this.ingrName = ingrName;
	}
	
	public IngredClass() {
		// TODO Auto-generated constructor stub
	}
}
