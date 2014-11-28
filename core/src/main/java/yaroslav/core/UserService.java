package yaroslav.core;

import yaroslav.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
        User user = em.find(User.class, 1);

        if(user==null) {
            System.out.println("User is null. Creating User");
            user = new User("admin", 23);
            em.persist(user);
        }else{
            System.out.println(user);
            user.setAge(user.getAge()+1);
        }
    }

    @GET
    @Path("{id}")
    public User get(@PathParam("id")Integer id){
        return em.find(User.class, 1);
    }
}
