/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import facade.Factory;
import javax.enterprise.deploy.spi.DeploymentConfiguration;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author hvn15
 */
@Path("this")
public class GenericResource {
    
    @Context
    private UriInfo context;
    private Factory f;
    private Gson gson;

    /**
     * Creates a new instance of TestResource
     */
    public GenericResource() {
        f = new Factory();
        f.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));
        gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of
     * com.mycompany.wordpresstest.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("yo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getXml() {
        //TODO return proper representation object
        return gson.toJson(f.getTest());
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("ins")
    @Produces(MediaType.APPLICATION_JSON)
    public String postEmpnum(String content) {
        JsonObject body = new JsonParser().parse(content).getAsJsonObject();
        int age = 0;
        String name = "";
        if (body.has("name")) {
            name = body.get("name").getAsString();
        }
        if (body.has("age")) {
            age = body.get("age").getAsInt();
        }
        return gson.toJson(f.addTest(name, age));
    }
    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    
}
