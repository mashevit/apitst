package config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import api.DishResource;
import api.OneIngre;
import api.QIngred;
import api.akj;
import api.anotget;
import api.ingredapi;

/**
 * Used to bootstrap JAX-RS.  Otherwise this class is
 * not directly used.
 *
 */
@ApplicationPath("/rest")
public class RestApplicationConfig extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> classes = new HashSet<Class<?>>();

    public RestApplicationConfig() {
        CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().add("*");
        corsFilter.setAllowedHeaders("Content-Type");
        corsFilter.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");
        singletons.add(corsFilter);
        classes.add(akj.class);
        classes.add(anotget.class);
        classes.add(DishResource.class);
        classes.add(ingredapi.class);
        classes.add(OneIngre.class);
        classes.add(QIngred.class);
        
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
    // intentionally empty
}