package api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mi.DishService;


@Path(value = "oneingre")
public class OneIngre {
	private DishService customerService;

	
	public OneIngre() {
		customerService=new DishService();

		// TODO Auto-generated constructor stub
	}
	
	@GET
	@Path(value = "retoneingre/{id1}")
	@Produces(MediaType.APPLICATION_JSON)
	public IngredClass getIngred(@PathParam("id1") int id) {
		return customerService.retIngred(id);
	}
	
}
