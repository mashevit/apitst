package api;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import mi.DishServiceI;

	@Path(value = "dish")
	public class DishResource {

		private final DishServiceI customerService;

		@Inject
		public DishResource(DishServiceI customerService) {
			this.customerService = customerService;
		}

		@GET
		@Path(value = "retrieve/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public DishClass getCustomer(@PathParam("id") int id) {
			return customerService.retrieve(id);
		}

	    @POST
	    @Path(value = "delete")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response deleteCustomer(int id) {
	        customerService.delete(id);

	        return Response.status(Status.OK).entity("customer has been successfully deleted").type(MediaType.APPLICATION_JSON).build();
	    }
		
		@POST
		@Path(value = "savec")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response saveCustomer(DishClass customer) {
			customerService.save(customer);
			
			return Response.status(Status.OK).entity("customer has been successfully saved").type(MediaType.APPLICATION_JSON).build();
		}
		
		
		@GET
		@Path(value = "retrieveall")
		@Produces(MediaType.APPLICATION_JSON)
		public List<DishClass> getAll() {
			return customerService.getAll();
		}
		
		@POST
		@Path(value = "savecs")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response saveCustomerCS(DishCSClass customer) {
			customerService.saveCS(customer);
			
			return Response.status(Status.OK).entity("customer has been successfully saved").type(MediaType.APPLICATION_JSON).build();
		}
		
		@GET
		@Path(value = "retrieveallcs")
		@Produces(MediaType.APPLICATION_JSON)
		public List<DishCSClass> getAllcs() {
			return customerService.getAllCS();
		}
		
		
		
}
