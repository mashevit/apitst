package mi;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

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

}
