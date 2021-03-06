package blabla;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the indish database table.
 * 
 */
@Entity
@NamedQuery(name="Indish.findAll", query="SELECT i FROM Indish i")
public class Indish implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idindish;

	//bi-directional many-to-one association to Dish
	@ManyToOne
	@JoinColumn(name="dishfid")
	private Dish dish;

	//bi-directional many-to-one association to Ingrename
	@ManyToOne
	@JoinColumn(name="dishforkey")
	private Ingrename ingrename;

	public Indish() {
	}

	public int getIdindish() {
		return this.idindish;
	}

	public void setIdindish(int idindish) {
		this.idindish = idindish;
	}

	public Dish getDish() {
		return this.dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Ingrename getIngrename() {
		return this.ingrename;
	}

	public void setIngrename(Ingrename ingrename) {
		this.ingrename = ingrename;
	}

}