package api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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
}
