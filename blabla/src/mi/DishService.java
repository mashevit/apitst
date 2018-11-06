package mi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import api.DishCSClass;
import api.DishClass;
import api.IndishClass;
import api.IngredClass;
import blabla.Dish;
import blabla.Indish;
import blabla.Ingrename;
//@ApplicationScoped

public class DishService/* implements DishServiceI*/{
	//@Inject
	private foodDao foodDao;
	public DishService() {
		
		foodDao=new foodDao();
		// TODO Auto-generated constructor stub
	}
	public DishClass retrieve(int id) {
		return entityToClass(foodDao.retrieve(id));
	}

    public void delete(int id) {
        foodDao.delete(id);
    }

	public void save(DishClass customer) {
		foodDao.save(classToEntity(customer));
	}
	
	public int addnewIngre(IngredClass customer) {
		return foodDao.saveNewIngre(customer.getIngrName());
	}
	
	
	public IngredClass retIngred(int id) {
		return entityToClassIngre(foodDao.retrieveIngredById(id));
		
		
	}
	public int delOneIngred(IndishClass indishClass) {
		return foodDao.delIngrediFromFood(indishClass);
	}
	
	public void addOneIngred(IndishClass indishClass) {
		foodDao.addIngrediToFood(indishClass);
	}

	public int counBy(int a) {
		return foodDao.CountDishByIngreds(a);
		
	}
	public void edit(DishClass customer) {
		foodDao.edit(classToEntityfull(customer));
	}
	
	public void editingr(IngredClass customer) {
		foodDao.editingre(classToEntityFullIngre(customer));
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
	
	public List<DishClass> search(String name) {
		List<DishClass> a=new ArrayList<>();
		List<Dish> b=foodDao.search(name);
		a=b.stream().map(Dishq->entityToClass(Dishq)).collect(Collectors.toList());
		return a;
				
		}
		
	
	
	public List<IngredClass> searchIngredi(String name) {
		List<IngredClass> a=new ArrayList<>();
		List<Ingrename> b=foodDao.searchIngred(name);
		a=b.stream().map(Dishq->entityToClassIngre(Dishq)).collect(Collectors.toList());
		return a;
				
		}
	
	
	public List<IngredClass> getForId(int id){
		List<IngredClass> a = new ArrayList<>();
		List<Ingrename> b=foodDao.ingredById(id);
		a= b.stream().map(k->entityToClassIngre(k)).collect(Collectors.toList());
		return a;
		
	}
	
	public List<DishClass> getAll(){
		List<DishClass> a=new ArrayList<>();
		List<Dish> b=foodDao.getAll();
		a=b.stream().map(Dishq->entityToClass(Dishq)).collect(Collectors.toList());
		return a;
	}
	
	public List<IngredClass> getAllIngreds(){
		List<IngredClass> a=new ArrayList<>();
		List<Ingrename> b=foodDao.getAllIngreds();
		a=b.stream().map(Dishq->entityToClassIngre(Dishq)).collect(Collectors.toList());
		return a;
	}
	
	public List<DishCSClass> getAllCS(){
		
		List<DishCSClass> a=new ArrayList<>();
		List<Dish> b=foodDao.getAll();
		a=b.stream().map(Dishq->entityToClassCS(Dishq)).collect(Collectors.toList());
		return a;
		
	}
	
	public List<DishClass> getDishforIngredId(int id){
		List<Dish> ans=foodDao.findDishesForIngredId(id);
		List<DishClass> a=new ArrayList<>();
		//List<Dish> b=foodDao.getAll();
		a=ans.stream().map(Dishq->entityToClass(Dishq)).collect(Collectors.toList());
		return a;
		
	}
	
	public int countDishforIngredId(int id){
		return foodDao.CountDishByIngreds(id);
		
	}
	
	
	public void delforIngredId(int id){
		foodDao.delForIngredId(id);
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

	
	private IngredClass entityToClassIngre(Ingrename entity) {
		IngredClass ingredClass = new IngredClass();

		if (entity != null) {
			ingredClass.setId(entity.getIdingrenames());
			ingredClass.setIngrName(entity.getIngrname());
		}

		return ingredClass;
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
	private Dish classToEntityfull(DishClass customer) {
		Dish entity = new Dish();

		if (customer != null) {
		//	System.out.println(customer.getDishName()+"jjjh");
		//	entity.setIddish(customer.getId());
			entity.setDishname(customer.getDishName());
		//	entity.setLastName(customer.getLastName());
			entity.setIddish(customer.getId());
		}

		return entity;
	}
	
	
	private Ingrename classToEntityFullIngre(IngredClass customer) {
		Ingrename entity = new Ingrename();

		if (customer != null) {
		//	System.out.println(customer.getDishName()+"jjjh");
		//	entity.setIddish(customer.getId());
			entity.setIngrname(customer.getIngrName());
		//	entity.setLastName(customer.getLastName());
			entity.setIdingrenames(customer.getId());
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
