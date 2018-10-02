package mi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import api.DishCSClass;
import api.DishClass;
import blabla.Dish;
import blabla.Ingrename;

@Singleton
public class DishService implements DishServiceI{
	@Inject
	private foodDaoI foodDao;
	
	
	public DishClass retrieve(int id) {
		return entityToClass(foodDao.retrieve(id));
	}

    public void delete(int id) {
        foodDao.delete(id);
    }

	public void save(DishClass customer) {
		foodDao.save(classToEntity(customer));
	}
	
	public void saveCS(DishCSClass customer) {
		Dish entity = new Dish();

		if (customer != null) {
		//	System.out.println(customer.getDishName()+"jjjh");
		//	entity.setIddish(customer.getId());
			entity.setDishname(customer.getDishName());
		//	entity.setLastName(customer.getLastName());
			foodDao.saveCS(customer.getDishName(),customer.getIngreds());
				
		}
		
	}
	
	
	public List<DishClass> getAll(){
		List<DishClass> a=new ArrayList<>();
		List<Dish> b=foodDao.getAll();
		a=b.stream().map(Dishq->entityToClass(Dishq)).collect(Collectors.toList());
		return a;
	}
	
	
	
	public List<DishCSClass> getAllCS(){
		List<DishCSClass> a=new ArrayList<>();
		List<Dish> b=foodDao.getAll();
		a=b.stream().map(Dishq->entityToClassCS(Dishq)).collect(Collectors.toList());
		return a;
	}
	
	
	private DishClass entityToClass(Dish entity) {
		DishClass dishClass = new DishClass();

		if (entity != null) {
			dishClass.setId(entity.getIddish());
			dishClass.setDishName(entity.getDishname());
			//customer.setLastName(entity.getLastName());
		}

		return dishClass;
	}
	
	
	private DishCSClass entityToClassCS(Dish entity) {
		DishCSClass dishClass = new DishCSClass();

		if (entity != null) {
			dishClass.setId(entity.getIddish());
			dishClass.setDishName(entity.getDishname());
			
			List<Ingrename> tmp=foodDao.getAllCS(entity);
			List<String> tmp1 = tmp.stream().map(iname->iname.getIngrname()).collect(Collectors.toList());
		dishClass.setIngreds(tmp1);
			
			//customer.setLastName(entity.getLastName());
		}

		return dishClass;
	}

	private Dish classToEntity(DishClass customer) {
		Dish entity = new Dish();

		if (customer != null) {
		//	System.out.println(customer.getDishName()+"jjjh");
		//	entity.setIddish(customer.getId());
			entity.setDishname(customer.getDishName());
		//	entity.setLastName(customer.getLastName());
		}

		return entity;
	}
	
	private Dish classToEntityCS(DishCSClass customer) {
		Dish entity = new Dish();

		if (customer != null) {
		//	System.out.println(customer.getDishName()+"jjjh");
		//	entity.setIddish(customer.getId());
			
			/*List<String> a= customer.getIngreds();
			List<Ingrename> tmp=a.stream().map(iname->(new Ingrename()).setIngrname(iname)).collect(Collectors.toList());
			entity.setDishname(customer.getDishName());*/
			
			
		//	entity.setLastName(customer.getLastName());
		}

		return entity;
	}
}
