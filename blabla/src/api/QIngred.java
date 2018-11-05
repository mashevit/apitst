package api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mi.DishService;

@Path(value = "qingred")
public class QIngred {
	private DishService customerService;

	
public QIngred() {
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
@Produces(MediaType.APPLICATION_JSON)
public List<IngredClass> getMessageQueryParam(@QueryParam("name") String param1){
return customerService.searchIngredi(param1);


    // Store the message
 
}

}
