package com.ekino.app.rest.service.v1;

import com.ekino.app.rest.pojos.Lunch;
import info.magnolia.rest.AbstractEndpoint;
import info.magnolia.rest.EndpointDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/demo/v1")
public class DemoEndpoint<D extends EndpointDefinition> extends AbstractEndpoint<D> {
    private static final Logger log = LoggerFactory.getLogger(DemoEndpoint.class);
    private List<Lunch> store = new ArrayList<>();
    public DemoEndpoint(D endpointDefinition) {
        super(endpointDefinition);
    }

    @Path("/hello")
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response hello() {
        return Response.ok().build();
    }


    @Path("/lunch2")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response lunch2() {
        Lunch pojo = new Lunch("Rösti mit Geschnetzeltem", "Ueli Weizen");
        return Response.ok(pojo).build();
    }


    @Path("/lunch")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Lunch lunch() {
        Lunch pojo = new Lunch("Svíčková na smetaně ", "Gambrinus Plzen");
        return pojo;
    }

    @Path("/store-lunch")
    @Consumes({MediaType.APPLICATION_JSON})
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public Response storeLunch(Lunch lunch){
        if(lunch != null){
            try {
                store.add(lunch);
                return Response.ok(lunch).build();
            } catch (Exception e) {
                log.error("Failed to store the lunch");
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }// eof: storeLunch
}