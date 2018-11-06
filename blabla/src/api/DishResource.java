package api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

//import org.glassfish.jersey.process.internal.RequestScoped;

import mi.DishService;


	@Path(value = "dish")
	public class DishResource {
	//@Inject
		private DishService customerService;

/*		@Inject
		public DishResource(DishServiceI customerService) {
			this.customerService = customerService;
		}*/
		  
		public DishResource() {
			customerService=new DishService();
			// TODO Auto-generated constructor stub
		}
		
		
		@GET
		//@Path(value = "query")
		@Produces(MediaType.TEXT_HTML)
		public String getMessageQueryParam(@QueryParam("param1") String param1){
			return param1;

		    // Store the message
		 
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
	    @Path(value = "delete1/{id}")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response deleteCustomer1(@PathParam("id") int id) {
	        customerService.delete(id);

	        return Response.status(Status.OK).entity("customer has been successfully deleted").type(MediaType.APPLICATION_JSON).build();
	    }
	    
	    
		@POST
	    @Path(value = "edit")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response edit(DishClass entity) {
//			if (entity.getName().length() <= 3) {
//				return Response.status(Status.CONFLICT).entity("Customer name is too short").type(MediaType.TEXT_PLAIN).build();
//			}
			customerService.edit(entity);
			//return super.edit(entity);
			return Response.status(Status.OK).entity("customer has been successfully edited").type(MediaType.APPLICATION_JSON).build();
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
		
		
//		@GET
//		@Path("search")
//		@Produces(MediaType.APPLICATION_JSON)
//		public List<DishClass> search(@QueryParam("name") String dname) {
//			return customerService.search(dname);
//		}
		
//		
//		@POST
//		@Path(value = "search")
//		@Consumes(MediaType.APPLICATION_JSON)
//		@Produces(MediaType.APPLICATION_JSON)
//		public List<DishClass> search(DishClass name) {
//			return customerService.search(name.getDishName());
//		}
//		
		

}
