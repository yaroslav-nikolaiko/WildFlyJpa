package yaroslav.model;

import javax.enterprise.context.spi.Context;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PostLoad;

/**
 * Created by ynikolaiko on 11/25/14.
 */
//@Stateless
public class UserListener {
/*   @Inject
   EntitiesListenerProvider entitiesListenerProvider;*/
    /*@Inject
    BeanManager beanManager;*/
/*    @Inject
Instance<EntitiesListenerProvider> entitiesListenerProviderSource;*/
    //@EJB
    //EntitiesListenerProvider entitiesListenerProvider;

    @PostLoad
    public void addTranslation(User user) {
        System.out.println("Post load listener user = "+user);
        try {
        InitialContext ic = new InitialContext();

        EntitiesListenerProvider    entitiesListenerProvider = (EntitiesListenerProvider) ic.lookup("java:global/test/EntitiesListenerProviderImp");

        entitiesListenerProvider.execute();
        } catch (NamingException e) {
            e.printStackTrace();
        }
        //beanManager.getReference()
        //EntitiesListenerProvider entitiesListenerProvider = entitiesListenerProviderSource.get();
        //entitiesListenerProvider.execute();
    }
}
