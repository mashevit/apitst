package api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mi.DishService;

@Path(value = "q")
public class akj {
	
	private DishService customerService;

	/*		@Inject
			public DishResource(DishServiceI customerService) {
				this.customerService = customerService;
			}*/

			
	public akj() {
				customerService=new DishService();
				// TODO Auto-generated constructor stub
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DishClass> getMessageQueryParam(@QueryParam("name") String param1){
	return customerService.search(param1);
	    // Store the message
	 
	}
	
	@GET
    @Path(value = "getdfi/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DishClass> getDishesforIngredId(@PathParam("id") int param1){
	return customerService.getDishforIngredId(param1);
	    // Store the message
	 
	}	
	
	
	@GET
    @Path(value = "delingre/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delforIngredId(@PathParam("id") int param1){
	  customerService.delforIngredId(param1);
	    // Store the message
	 
	}	
	
}
