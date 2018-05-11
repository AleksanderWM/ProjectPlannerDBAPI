package entities.service;

import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Aleksander
 * Abstract klasse som de andre facade klasser implementerer for at benytte POST, PUT, GET, DELETE
 * Klassen bennytter entitymanager for at kommunikere med databasen.
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();
/**
 * Kode brugt til at oprette (POST) nye entiteter i databasen. 
 * 
 * @param entity kan være enhver af de forskellige entiteter der findes.
 * Tager den pågældende entitet og persister den, for så at blive oprettet i databasen.
 */
    public void create(T entity) {
        getEntityManager().persist(entity);
    }
/**
 * Kode brugt til at ændre i allerede oprettede objekter i databasen
 * @param entity kan være enhver af de forskellige entiteter der findes.
 * Bagved i de forskellige klasser der implementerer denne, ligger der en merge() som merger det nye indhold ind i den allerede oprettede entitet.
 */
    public void edit(T entity) {
        getEntityManager().merge(entity);

    }
/**
 * Kode brugt til at slette objekter i databasen
 * @param entity kan være enhver af de forskellige entiteter der findes.
 */
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
/**
 * Kode brugt til at hente et specifikt object i databasen ud fra dets id
 * @param id bruges til at finde det korrekte id
 * @return giver det specifikke object tilbage.
 */
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
/**
 * Kode brugt til at hente alle objekter af den valgte entitet.
 * @return giver alle de forskellige objekter tilbage.
 */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }
/**
 * Kode brugt til at tælle alle objekter af den valgte entitet
 * @return returnere antal af objekter
 */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
     public void merge(Object obj, Object update){
    if(!obj.getClass().isAssignableFrom(update.getClass())){
        return;
    }

    Method[] methods = obj.getClass().getMethods();

    for(Method fromMethod: methods){
        if(fromMethod.getDeclaringClass().equals(obj.getClass())
                && fromMethod.getName().startsWith("get")){

            String fromName = fromMethod.getName();
            String toName = fromName.replace("get", "set");

            try {
                Method toMetod = obj.getClass().getMethod(toName, fromMethod.getReturnType());
                Object value = fromMethod.invoke(update, (Object[])null);
                if(value != null){
                    toMetod.invoke(obj, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } 
        }
    }
    }
}
