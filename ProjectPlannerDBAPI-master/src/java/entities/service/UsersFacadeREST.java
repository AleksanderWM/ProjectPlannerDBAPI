package entities.service;

import entities.Users;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
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

/**
 *
 * @author Aleksander
 */
@Stateless
@Path("users")
public class UsersFacadeREST extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "ProjectPlannerDBAPI")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
    }
/**
 * Bruges til at POST nye objecter til databasen
 * @param entity tager objektet sendt med i body og opretter denne i databasen
 */
    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Users entity) {
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
    public void edit(@PathParam("id") String id, Users entity) {
        Users entitytoupdate = super.find(id);
        merge(entitytoupdate, entity);
        super.edit(entitytoupdate);
    }
/**
 * Kode brugt til at slette objekter i databasen
 * @param id bruges til at finde det korrekte id at slette
 */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }
/**
 * Kode brugt til at hente et specifikt object i databasen ud fra dets id
 * @param id bruges til at finde det korrekte id
 * @return giver det specifikke object tilbage.
 */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Users find(@PathParam("id") String id) {
        return super.find(id);
    }
/**
 * Kode brugt til at hente alle objekter af den valgte entitet.
 * @return giver alle de forskellige objekter tilbage.
 */
    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Users> findAll() {
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
