
package rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author mkuchtiak
 */

@Stateless
@Path("/greeting") // cdi/resources/greeting
public class HelloWorldResource {

    @EJB
    private NameStorageBean nameStorage;
    /**
     * Retrieves representation of an instance of helloworld.HelloWorldResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getGreeting() {
        return "<html><body><h1>Hello "+nameStorage.getName()+"!</h1></body></html>";
    }

    /**
     * PUT method for updating an instance of HelloWorldResource
     * @param content representation for the resource
     * returns no content, only update data
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN) 
    public void putName(String content) {
        nameStorage.setName(content);
    }
    
    @POST
    @Consumes(MediaType.TEXT_PLAIN) //{MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON}
    public String postName(String content) {
        nameStorage.setName(content);
        return "<html><body><h1>Hello "+nameStorage.getName()+"!</h1></body></html>";
    }
}
