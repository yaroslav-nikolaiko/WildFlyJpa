package yaroslav.core;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Created by ynikolaiko on 11/24/14.
 */
@javax.ejb.Singleton
@Startup
public class StartUpBean {
    @Inject
    EntityManager em;
    @EJB
    UserService userService;

    @PostConstruct
    public void init() {
        userService.update();
    }

}
