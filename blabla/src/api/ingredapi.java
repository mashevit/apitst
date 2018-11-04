package api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

//import blabla.Indish;
//import blabla.Ingrename;
import mi.DishService;

@Path(value = "ingr")
public class ingredapi {
	
	private DishService customerService;

/*		@Inject
		public DishResource(DishServiceI customerService) {
			this.customerService = customerService;
		}*/

		
		public ingredapi() {
			customerService=new DishService();
			// TODO Auto-generated constructor stub
		}
		
		@GET
		@Path(value = "retrieveallid/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<IngredClass> getAll(@PathParam("id") int id) {
			return customerService.getForId(id);
		}
		
		@POST
		@Path(value = "deloneingr")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public int getOne(IndishClass indishClass) {//id of indish to complete deleting process
			return customerService.delOneIngred(indishClass);
		}
		
		@POST
		@Path(value = "retrievecount/{id}")
		//@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public int getCount(@PathParam("id") int indishClass) {//id of indish to complete deleting process
			return customerService.counBy(indishClass);
		}
		
		@POST
	    @Path(value = "editingre")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response edit(IngredClass entity) {
//			if (entity.getName().length() <= 3) {
//				return Response.status(Status.CONFLICT).entity("Customer name is too short").type(MediaType.TEXT_PLAIN).build();
//			}
			customerService.editingr(entity);
			//return super.edit(entity);
			return Response.status(Status.OK).entity("customer has been successfully edited").type(MediaType.APPLICATION_JSON).build();
		}
		
		@POST
		@Path(value = "addoneingr")
		@Consumes(MediaType.APPLICATION_JSON)
		//@Produces(MediaType.APPLICATION_JSON)
		public void addOne(IndishClass indishClass) {//id of indish to complete deleting process
			customerService.addOneIngred(indishClass);
		}

		@POST
		@Path(value = "newingr")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public int newIngr(IngredClass ingredClass) {//id of indish to complete deleting process
			return customerService.addnewIngre(ingredClass);
		}
}
