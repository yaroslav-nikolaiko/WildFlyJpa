package yaroslav.core;

import yaroslav.model.Address;
import yaroslav.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by ynikolaiko on 12/1/14.
 */
@Stateless
@Path("address")
@Produces({ MediaType.APPLICATION_JSON})
@Consumes({ MediaType.APPLICATION_JSON})
public class AddressService {
    @Inject
    EntityManager em;

    @GET
    @Path("{id}")
    public Address get(@PathParam("id")Long id){
        return em.find(Address.class, id);
    }

    @GET
    @Path("update/{id}/{value}")
    public Address get(@PathParam("id")Long id, @PathParam("value") String value){
        Address address = em.find(Address.class, id);
        address.setCity(value);
        return address;
    }
}
