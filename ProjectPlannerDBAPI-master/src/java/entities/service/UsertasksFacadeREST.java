package entities.service;

import entities.Usertasks;
import entities.UsertasksPK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author Aleksander
 */
@Stateless
@Path("usertasks")
public class UsertasksFacadeREST extends AbstractFacade<Usertasks> {

    @PersistenceContext(unitName = "ProjectPlannerDBAPI")
    private EntityManager em;

    private UsertasksPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;userID=userIDValue;taskID=taskIDValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        entities.UsertasksPK key = new entities.UsertasksPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> userID = map.get("userID");
        if (userID != null && !userID.isEmpty()) {
            key.setUserID(userID.get(0));
        }
        java.util.List<String> taskID = map.get("taskID");
        if (taskID != null && !taskID.isEmpty()) {
            key.setTaskID(new java.lang.Integer(taskID.get(0)));
        }
        return key;
    }

    public UsertasksFacadeREST() {
        super(Usertasks.class);
    }
/**
 * Bruges til at POST nye objecter til databasen
 * @param entity tager objektet sendt med i body og opretter denne i databasen
 */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Usertasks entity) {
        super.create(entity);
    }
/**
 * Kode brugt til at ændre i allerede oprettede objekter i databasen
 * @param id Bruges til at finde det rigtige object i databasen
 * @param entity tager objektet sendt med i body og merger den med den i databasen
 */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Usertasks entity) {
        Usertasks entitytoupdate = super.find(id);
        merge(entitytoupdate, entity);
        super.edit(entitytoupdate);
    }
/**
 * Kode brugt til at slette objekter i databasen
 * @param id bruges til at finde det korrekte id at slette
 */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        entities.UsertasksPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }
/**
 * Kode brugt til at hente et specifikt object i databasen ud fra dets id
 * @param id bruges til at finde det korrekte id
 * @return giver det specifikke object tilbage.
 */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Usertasks find(@PathParam("id") PathSegment id) {
        entities.UsertasksPK key = getPrimaryKey(id);
        return super.find(key);
    }
/**
 * Kode brugt til at hente alle objekter af den valgte entitet.
 * @return giver alle de forskellige objekter tilbage.
 */
    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Usertasks> findAll() {
        return super.findAll();
    }
/**
 * Kode brugt til at tælle alle objekter
 * @return returnere antal af objekter
 */
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
