/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:BookRestService [/book]<br>
 * USAGE:
 * <pre>
 *        JerseyBookClient client = new JerseyBookClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Administrator
 */
public class JerseyBookClient {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/cdi/resources";

    public JerseyBookClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("book");
    }

    public Response createBook() throws ClientErrorException {
        return webTarget.request().post(null, Response.class);
    }

    public Response deleteBook(String id) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete(Response.class);
    }

    public <T> T getAllBooks(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request().get(responseType);
    }

    public <T> T getBook(Class<T> responseType, String id, String type) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{id, type}));
        return resource.request().get(responseType);
    }

    public Response updateBook() throws ClientErrorException {
        return webTarget.request().put(null, Response.class);
    }

    public void close() {
        client.close();
    }
    
}
