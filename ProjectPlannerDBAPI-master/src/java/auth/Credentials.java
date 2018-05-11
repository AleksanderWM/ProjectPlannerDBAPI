package auth;

import java.io.Serializable;

/**
 *
 * @author janus
 */
public class Credentials implements Serializable {
    private String username;
    private String password;
    
    protected String getUsername(){
        return this.username;
    }
    protected String getPassword(){
        return this.password;
    }
    
}
