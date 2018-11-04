package api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import mi.DishService;

@Path(value = "qingred")
public class QIngred {
	private DishService customerService;

	
public QIngred() {
	customerService=new DishService();

	// TODO Auto-generated constructor stub
}

@GET
@Produces(MediaType.APPLICATION_JSON)
public List<IngredClass> getMessageQueryParam(@QueryParam("name") String param1){
return customerService.searchIngredi(param1);


    // Store the message
 
}

}
