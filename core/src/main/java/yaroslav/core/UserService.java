package yaroslav.core;

import yaroslav.model.Address;
import yaroslav.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

/**
 * Created by ynikolaiko on 11/24/14.
 */
@Stateless
@Path("user")
@Produces({ MediaType.APPLICATION_JSON})
@Consumes({ MediaType.APPLICATION_JSON})
public class UserService {
    @Inject
    EntityManager em;

    public void update(){
        System.out.println("Entering service");
        User user = em.find(User.class, 1L);

        if(user==null) {
            System.out.println("User is null. Creating User");
            user = new User("admin", 23);
            user.setAddresses(Arrays.asList(new Address("Kiev"), new Address("Lviv")));
            em.persist(user);
        }else{
            System.out.println(user);
            user.setAge(user.getAge()+1);
        }
    }

    @GET
    @Path("{id}")
    public User get(@PathParam("id")Long id){
        return em.find(User.class, id);
    }


}
