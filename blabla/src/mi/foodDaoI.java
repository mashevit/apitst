package mi;

import java.util.List;

import blabla.Dish;
import blabla.Ingrename;

public interface foodDaoI {

	int save(Dish customer);

	void delete(int id);

	Dish retrieve(int id);

	List<Dish> getAll();

	List<Ingrename> getAllCS(Dish dish);

	void saveCS(String a, List<String> ingreds);

}
