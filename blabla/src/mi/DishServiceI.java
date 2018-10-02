package mi;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;

import api.DishCSClass;
import api.DishClass;
/*@Local
@EJB(beanName = "Teller1",name="DishServiceI",beanInterface=DishServiceI.class)*/
@Local
public interface DishServiceI {

	void save(DishClass customer);

	void delete(int id);

	DishClass retrieve(int id);

	List<DishClass> getAll();

	List<DishCSClass> getAllCS();

	void saveCS(DishCSClass customer);

}
