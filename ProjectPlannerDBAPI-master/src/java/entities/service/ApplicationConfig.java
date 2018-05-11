package entities.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Aleksander
 * Denne klasse indeholder "stien" til de andre rest klasser og sørger for de bliver "exposed"
 */
@javax.ws.rs.ApplicationPath("")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(entities.service.CORS.class);
        resources.add(auth.AuthenticationEndpoint.class);
        resources.add(auth.AuthenticationFilter.class);
        resources.add(entities.service.GanttLinkFacadeREST.class);
        resources.add(entities.service.GanttTaskFacadeREST.class);
        resources.add(entities.service.ProjectsFacadeREST.class);
        resources.add(entities.service.TasksFacadeREST.class);
        resources.add(entities.service.UsersFacadeREST.class);
        resources.add(entities.service.UsertasksFacadeREST.class);
    }
    
}
