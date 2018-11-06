package mi;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import api.IndishClass;
import blabla.Dish;
import blabla.Indish;
import blabla.Ingrename;
//@ApplicationScoped

public class foodDao/* implements foodDaoI*/{
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("blabla");
	
	public Dish retrieve(int id) {
		EntityManager em = emf.createEntityManager();
		Dish entity = null;

		try {
			entity = em.find(Dish.class, id);
		} finally {
			em.close();
		}
		
		return entity;

		
	}
	
	
	public Ingrename retrieveIngredById(int id) {
		EntityManager em = emf.createEntityManager();
		Ingrename entity = null;

		try {
			entity = em.find(Ingrename.class, id);
		} finally {
			em.close();
		}
		
		return entity;

		
	}
	@SuppressWarnings("unchecked")
	public List<Dish> getAll(){
		
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Dish.findAll");
		List<Dish> ans = query.getResultList();
		em.close();
		//emf.close();
		return ans;
	}
	public List<Ingrename> getAllCS(Dish dish){
		
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();		
		String statement = "SELECT i FROM Indish i1 Join i1.ingrename i WHERE i1 IN (SELECT w FROM Indish w JOIN w.dish z WHERE (z.dishname =:cn))";
		Query q = em.createQuery(statement).setParameter("cn", dish.getDishname());
		@SuppressWarnings("unchecked")
		List<Ingrename> lct = q.getResultList();			
		em.close();
		//emf.close();
		return lct;
	}
	
	public List<Indish> getAllIndish(/*Dish dish*/){
		EntityManager em = emf.createEntityManager();
		//query query = em.createQuery("Select i From Indish i Join i.dish j Where j.iddish =: id").setParameter("id", dish.getIddish());
		Query query = em.createNamedQuery("Indish.findAll");
		@SuppressWarnings("unchecked")
		List<Indish> ans = query.getResultList();
		em.close();
		//emf.close();
		return ans;
		
		
	}
	  public void delete(int id) {
	        EntityManager em = emf.createEntityManager();

	   //     EntityTransaction transaction = em.getTransaction();

	        try {
	          //  transaction.begin();

	            Dish entity = em.find(Dish.class, id);

	            if(entity == null) {
	                System.out.println("Error Deleting Dish: Dish not found");
	            }
	            else {
	            	List<Ingrename> tmpp= getAllCS(entity);
	            	List<Indish> tmpi=getAllIndish();
	            	List<Integer> c =tmpp.stream().map(Ingren->Ingren.getIdingrenames()).collect(Collectors.toList());
	            	//List<Integer> d =tmpi.stream().map(Ingrenid->Ingrenid.getIngrename().getIdingrenames()).collect(Collectors.toList());
	            	for(int i=0;i<c.size();i++) {
	            		int curr = c.get(i).intValue();
	            	 List<Indish> Exists = tmpi.stream().filter(item -> (curr==item.getIngrename().getIdingrenames())).collect(Collectors.toList());
	            	 for(int k=0;k<Exists.size();k++) {
	            		 Indish curre=Exists.get(k);
	            		 if(curre.getDish().getIddish()==entity.getIddish()) {
	            			 em.getTransaction().begin();	            				
	            			 Indish abaa= em.find(Indish.class,curre.getIdindish());
	            			 em.remove(abaa);
	            			 em.getTransaction().commit();
	            			 Exists.remove(k);
	            		 }
	            		 if(Exists.size()==0) {		 em.getTransaction().begin();
	            			 Ingrename aab=em.find(Ingrename.class, tmpp.get(i).getIdingrenames());
	            			 
	            		 	em.remove(aab);
	            		 	em.getTransaction().commit();
	            		 	tmpp.remove(i);
	            		 	c.remove(i);i--;
	            		 }		 em.getTransaction().begin();
	            		 em.getTransaction().commit();
	            		 
	            	 }
	            	 }		 em.getTransaction().begin();
	                em.remove(entity); 
	                em.getTransaction().commit();
	            }

	           
	        } catch (Exception e) {
	            System.out.println("Error Deleting Dish: " + e.getMessage());

	            em.getTransaction().rollback();
	        } finally {
	            em.close();
	        }
	    }
	  
	  	public int delIngrediFromFood(IndishClass ids) {
	  		 EntityManager em = emf.createEntityManager();
	  		 int idingre=ids.getIdIngred();
	  		 String statement = "select a from Indish a join a.dish d join a.ingrename i where d.iddish=:iddi and i.idingrenames=:idid";
	  		 Query q = em.createQuery(statement).setParameter("iddi", ids.getIdDish()).setParameter("idid", idingre);
	  		 @SuppressWarnings("unchecked")
			List<Indish> ans = q.getResultList();
	  		 if(ans.size()==0) return -1;
	  		 else if (CountDishByIngreds(idingre)>1){ em.getTransaction().begin(); em.remove(ans.get(0)); 
              em.getTransaction().commit();
              }
	  		 else { em.getTransaction().begin();
	  			em.remove(ans.get(0)); 
	  		  em.getTransaction().commit();
	  		 em.getTransaction().begin();
	  			Ingrename acac=em.find(Ingrename.class, idingre);
	  		  em.remove(acac);
	  		  em.getTransaction().commit();
	  		  }
	  		 return 0;//ans.get(0).getIdindish();
	  	}
	  	
	  	public void addIngrediToFood(IndishClass ids) {
	  		 EntityManager em = emf.createEntityManager();
	  		 Indish med=new Indish();
	  		 med.setDish(retrieve(ids.getIdDish()));
	  		 med.setIngrename(retrieveIngredById(ids.getIdIngred()));
	 		try {
	  		em.getTransaction().begin();
	  		em.persist(med);
	  		em.getTransaction().commit();
	 		} catch (Exception e) {
				System.out.println("Error Saving Customer: " + e.getMessage());

				em.getTransaction().rollback();
			} finally {
				
				em.close();
				
			}

	  		 
	  	}
	  	
		public int edit(Dish customer) {
			EntityManager em = emf.createEntityManager();
			Dish entity=null;
//			int a = findByStr(customer.getDishname());
//			if (a!=-1) return a;
			EntityTransaction transaction = em.getTransaction();
			
			try {
				entity = em.find(Dish.class, customer.getIddish());
				transaction.begin();
				entity.setDishname(customer.getDishname());
	            em.merge(entity);

				transaction.commit();
			
			} catch (Exception e) {
				System.out.println("Error Saving Customer: " + e.getMessage());

				transaction.rollback();
			} finally {
				
				em.close();
				
			}
			return customer.getIddish();
			///return -1;
		}
		
		
		public int editingre(Ingrename customer) {
			EntityManager em = emf.createEntityManager();
			Ingrename entity=null;
//			int a = findByStr(customer.getDishname());
//			if (a!=-1) return a;
			EntityTransaction transaction = em.getTransaction();
			
			try {
				entity = em.find(Ingrename.class, customer.getIdingrenames());
				transaction.begin();
				entity.setIngrname(customer.getIngrname());
	            em.merge(entity);

				transaction.commit();
			
			} catch (Exception e) {
				System.out.println("Error Saving Ingred: " + e.getMessage());

				transaction.rollback();
			} finally {
				
				em.close();
				
			}
			return customer.getIdingrenames();
			///return -1;
		}
		
		public List<Dish> search(String name){
			//EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
			EntityManager em = emf.createEntityManager();
			String statement = "SELECT i FROM Dish i WHERE lower(i.dishname) like lower(concat('%', :nm,'%'))";
			Query q = em.createQuery(statement).setParameter("nm", name);
			@SuppressWarnings("unchecked")
			List<Dish> ans = q.getResultList();
			em.close();
			//emf.close();
			return ans;
				
		}
		
		public List<Ingrename> searchIngred(String name){
			EntityManager em = emf.createEntityManager();
			String statement = "SELECT i FROM Ingrename i WHERE lower(i.ingrname) like lower(concat('%', :nm,'%'))";
			Query q = em.createQuery(statement).setParameter("nm", name);
			@SuppressWarnings("unchecked")
			List<Ingrename> ans = q.getResultList();
			em.close();
			return ans;
				
		}		
		
		public int save(Dish customer) {
			EntityManager em = emf.createEntityManager();
			int a = findByStr(customer.getDishname());
			if (a!=-1) return a;
			EntityTransaction transaction = em.getTransaction();
			
			try {
				transaction.begin();

	            em.persist(customer);

				transaction.commit();
			
			} catch (Exception e) {
				System.out.println("Error Saving Customer: " + e.getMessage());

				transaction.rollback();
			} finally {
				
				em.close();
				
			}
			return customer.getIddish();
			///return -1;
		}

		public void saveCS(String a,List<String> ingreds) {
			EntityManager em = emf.createEntityManager();

			//EntityTransaction transaction = em.getTransaction();
			int b= findByStr(a);
			Dish touse;
			if(b!=-1)  touse= retrieve(b);
			else {touse=new Dish();
			touse.setDishname(a);
			em.getTransaction().begin();
			
			em.persist(touse);
			em.getTransaction().commit();}
			try {
			for(int i=0;i<ingreds.size();i++) {
				//transaction.begin();
				int tmp=findByStrIngredi(ingreds.get(i));
				Ingrename ing;
				if (tmp!=-1) 
					ing=em.find(Ingrename.class, tmp);
				else {ing = new Ingrename();
				    ing.setIngrname(ingreds.get(i));
					em.getTransaction().begin();
					em.persist(ing);
					em.getTransaction().commit();
	
				    }
				Indish ind=new Indish();
			
				ind.setIngrename(ing);//
				ind.setDish(touse);
			//	transaction.begin();
				em.getTransaction().begin();
				em.persist(ind);
			
				em.getTransaction().commit();
			//	a.setIndish(ind);
				
				/*em.getTransaction().begin();
				
				em.persist(a);
				em.getTransaction().commit();*/
			}
			/*em.getTransaction().begin();
			
			em.persist(a);
			em.getTransaction().commit();*/
			} catch (Exception e) {
				System.out.println("Error Saving Customer: " + e.getMessage());

				em.getTransaction().rollback();
			} finally {
				em.close();
			}
			
		}
		public int findByStr(String name) {
	
			EntityManager em = emf.createEntityManager();		
			String statement = "SELECT d FROM Dish d WHERE d.dishname = :cn";
			Query q = em.createQuery(statement).setParameter("cn", name);
			@SuppressWarnings("unchecked")
			List<Dish> lct = q.getResultList();		
			if(lct.size()==0) return -1;
			return lct.get(0).getIddish();
			
		}
		
		public int findByStrIngredi(String name) {
			
			EntityManager em = emf.createEntityManager();		
			String statement = "SELECT i FROM Ingrename i WHERE i.ingrname = :cn";
			Query q = em.createQuery(statement).setParameter("cn", name);
			@SuppressWarnings("unchecked")
			List<Ingrename> lct = q.getResultList();		
			if(lct.size()==0) return -1;
			return lct.get(0).getIdingrenames();
			
		}

		public int CountDishByIngreds(int idIngrename){
			EntityManager em = emf.createEntityManager();		
			String statement = "SELECT Count(d) from Indish a Join a.dish d Join a.ingrename i Where i.idingrenames=:id";
			Query q = em.createQuery(statement).setParameter("id", idIngrename);
			int ans =  Math.toIntExact((long) q.getSingleResult());	
			return ans;
			
			
		}
		public int saveNewIngre(String name) {
			EntityManager em = emf.createEntityManager();
			
			EntityTransaction transaction = em.getTransaction();
			Ingrename toSave = new Ingrename();
			toSave.setIngrname(name);
			try {
				transaction.begin();

	            em.persist(toSave);

				transaction.commit();
			
			} catch (Exception e) {
				System.out.println("Error Saving Customer: " + e.getMessage());

				transaction.rollback();
			} finally {
				
				em.close();
				
			}
			return toSave.getIdingrenames();
			///return -1;
			
		}
		public List<Ingrename> ingredById(int id){
	
				
				//EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
				EntityManager em = emf.createEntityManager();		
				String statement = "SELECT i FROM Indish i1 Join i1.ingrename i WHERE i1 IN (SELECT w FROM Indish w JOIN w.dish z WHERE (z.iddish =:cn)) ORDER BY i.idingrenames";
				Query q = em.createQuery(statement).setParameter("cn", id);
				@SuppressWarnings("unchecked")
				List<Ingrename> lct = q.getResultList();			
				em.close();
				//emf.close();
				return lct;
		
			
		}
		
		
		public List<Ingrename> getAllIngreds(){
			
			EntityManager em = emf.createEntityManager();
			Query query = em.createNamedQuery("Ingrename.findAll");
			List<Ingrename> ans = query.getResultList();
			em.close();
			return ans;
			
	}
		
		public List<Dish> findDishesForIngredId(int id) {
			EntityManager em = emf.createEntityManager();
			String statement = "SELECT i FROM Indish i1 Join i1.dish i WHERE i1 IN (SELECT w FROM Indish w JOIN w.ingrename z WHERE (z.idingrenames =:cn)) ORDER BY i.iddish";
			Query q = em.createQuery(statement).setParameter("cn", id);
			@SuppressWarnings("unchecked")
			List<Dish> lct = q.getResultList();			
			em.close();
			//emf.close();
			return lct;
			
		}
		
		
		public List<Dish> countDishesForIngredId(int id) {
			EntityManager em = emf.createEntityManager();
			String statement = "SELECT count(i) FROM Indish i1 Join i1.dish i WHERE i1 IN (SELECT w FROM Indish w JOIN w.ingrename z WHERE (z.idingrenames =:cn))";
			Query q = em.createQuery(statement).setParameter("cn", id);
			@SuppressWarnings("unchecked")
			List<Dish> lct = q.getResultList();			
			em.close();
			//emf.close();
			return lct;
			
		}
		
		
		public void delForIngredId(int id) {
			EntityManager em = emf.createEntityManager();
	
			
			 em.getTransaction().begin();	            				
			 Ingrename abaa= em.find(Ingrename.class,id);
			 List<Dish> lst = findDishesForIngredId(id);
			 
			 if(lst.isEmpty()) {
			 em.remove(abaa);
			 em.getTransaction().commit();
			 em.close();
			}
						 
      }
}
