/*package config;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import mi.DishService;
import mi.DishServiceI;
import mi.foodDao;
import mi.foodDaoI;

public class ApplicationBinder extends AbstractBinder{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		
    	// services
        bind(DishService.class).to(DishServiceI.class).in(Singleton.class);
        
        // dao
        bind(foodDao.class).to(foodDaoI.class).in(Singleton.class);
		
	}

}
*///