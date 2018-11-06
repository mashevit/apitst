package api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mi.DishService;

@Path(value = "anotget")
public class anotget {
	private DishService customerService;
	public anotget() {
		// TODO Auto-generated constructor stub
		customerService=new DishService();
	}
	
	@GET
    @Path(value = "getdfi/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DishClass> getDishesforIngredId(@PathParam("id") int param1){
	return customerService.getDishforIngredId(param1);
	    // Store the message
	 
	}	
}
