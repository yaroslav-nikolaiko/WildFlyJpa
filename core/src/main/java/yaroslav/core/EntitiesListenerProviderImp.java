package yaroslav.core;

import yaroslav.model.EntitiesListenerProvider;
import yaroslav.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * Created by ynikolaiko on 11/25/14.
 */
@Stateless
public class EntitiesListenerProviderImp implements EntitiesListenerProvider {
    @EJB
    UserService userService;
    @Override
    public void execute() {
        System.out.println("INSIDE ENTITY LISTENER PROVIDER");
        User user = userService.get(1);
        System.out.println("User inside listener PROVIDER = "+user);
    }
}
