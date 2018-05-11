package auth;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.ws.rs.NameBinding;
/**
 * Klasse der benyttes til at kunne give anotationen @Secured for at der kan tjekkes om der er sendt et token med inden bruger får adgang til databasen.
 * @author Aleksander
 */
@NameBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Secured {


}