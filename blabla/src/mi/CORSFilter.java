package mi;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
//import com.thetransactioncompany.cors.*;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

@Provider
public class CorsFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        CorsFilter corsFilter = new CorsFilter();
     //   https://appfoodang.herokuapp.com
            corsFilter.getAllowedOrigins().add("https://appfoodang.herokuapp.com");
        corsFilter.getAllowedOrigins().add("*");
        context.register(corsFilter);
        return true;
    }  
}