/*package config;

import javax.ws.rs.core.Application;

public class InitApplication extends Application{
	public InitApplication() {
	    super();
	    webServiceClasses = new HashSet<>();
	    webServiceClasses.add(PersonRestService.class);
	    webServiceClasses.add(CompanyRestService.class);


	    singletons = new LinkedHashSet<>();
	    singletons.add(this.getCorsFilter());

	}

	private CorsFilter getCorsFilter() {
	    CorsFilter result = new CorsFilter();
	    result.getAllowedOrigins().add("*");
	    result.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");
	    result.setCorsMaxAge(86400);//Max in FF 86400=24h https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Access-Control-Max-Age
	    //
	    return result;
	}
}
*/