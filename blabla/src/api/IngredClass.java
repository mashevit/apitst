package api;

public class IngredClass {
	private static final long serialVersionUID = 1L;

	private int id;
	//@JsonProperty("dishName")
	private String ingrName;
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
