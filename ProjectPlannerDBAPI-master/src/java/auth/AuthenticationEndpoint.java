/*
 * Inspiration found from the response to a post on stack overflow.
 *https://stackoverflow.com/questions/26777083/best-practice-for-rest-token-based-authentication-with-jax-rs-and-jersey
 */
package auth;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.soap.Brugeradmin;
import java.net.URL;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import entities.Users;
import java.security.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.util.Date; 
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;

/**
 * @author Aleksander & Janus
 */
@Path("/authentication")
public class AuthenticationEndpoint{

/**
 * Metode der udpakker det tilsendte object og kigger på userID og password for så at sende JWT tilbage til bruger
 * @param credentials
 * @return Giver enten en JWT eller en 403 forbidden.
 */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(Users credentials) {
        String username = credentials.getUserID();
        String password = credentials.getPassword();

        try {
            // Authenticater brugeren
            authenticate(username, password); 
            // Uddeller en token til brugeren
            String token = issueToken(username);
            // Return jwt i responsen         
           return Response.ok().entity(token).header("authorization", "Bearer"+token).header("Access-Control-Expose-Headers", "authorization").build();
            
        } catch (Exception e) {
           return Response.status(Response.Status.UNAUTHORIZED).build();
        }      
    }
/**
 * Autheticater brugeren op imod Javabog.dk
 * @param username
 * @param password
 * @throws Exception 
 */
    private void authenticate(String username, String password) throws Exception {
        Brugeradmin ba;

            URL url = new URL("http://javabog.dk:9901/brugeradmin?wsdl");
		QName qname = new QName("http://soap.transport.brugerautorisation/", "BrugeradminImplService");
		Service service = Service.create(url, qname);
		ba = service.getPort(Brugeradmin.class);
            Bruger bruger = ba.hentBruger(username, password);           
            }
    
/**
 * Uddeler en JWT til brugeren indeholde
 * subject = username
 * id = username
 * Selve keyen
 * expiration datoen
 * @param username
 * @return 
 */
    private String issueToken(String username) { 
    long nowMillis = System.currentTimeMillis();
    long ttlMillis = 360000000;
    long expMillis = nowMillis + ttlMillis;
    Date exp = new Date(expMillis);
    Key key = MacProvider.generateKey();
    String token = Jwts.builder()
                                .setSubject(username)
                                .setId(username)
                                .signWith(SignatureAlgorithm.HS256, key)
                                .setExpiration(exp)
                                .compact();     
        return token;
    }
    
 
}