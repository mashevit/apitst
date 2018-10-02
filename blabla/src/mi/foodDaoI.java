package mi;

import java.util.List;

import blabla.Dish;
import blabla.Ingrename;
/*@Local
@EJB(beanName = "Teller",name="foodDaoI",beanInterface=foodDaoI.class)*/
//@Local
public interface foodDaoI {

	int save(Dish customer);

	void delete(int id);

	Dish retrieve(int id);

	List<Dish> getAll();

	List<Ingrename> getAllCS(Dish dish);

	void saveCS(String a, List<String> ingreds);

}
