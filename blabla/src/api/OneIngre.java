package api;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mi.DishService;


@Path(value = "oneingre")
public class OneIngre {
	private DishService customerService;

	
	public OneIngre() {
		customerService=new DishService();

		// TODO Auto-generated constructor stub
	}
	  @OPTIONS
	  @Path("/getsample")
	  public Response getOptions() {
	    return Response.ok()
	      .header("Access-Control-Allow-Origin", "*")
	      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
	      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	  }
	@GET
	@Path(value = "retoneingre/{id1}")
	@Produces(MediaType.APPLICATION_JSON)
	public IngredClass getIngred(@PathParam("id1") int id) {
		return customerService.retIngred(id);
	}
	
}
