package mi;

import java.util.List;

import api.DishCSClass;
import api.DishClass;
/*@Local
@EJB(beanName = "Teller1",name="DishServiceI",beanInterface=DishServiceI.class)*/
//@Local
public interface DishServiceI {

	void save(DishClass customer);

	void delete(int id);

	DishClass retrieve(int id);

	List<DishClass> getAll();

	List<DishCSClass> getAllCS();

	void saveCS(DishCSClass customer);

}
