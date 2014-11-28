package yaroslav.utils;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by ynikolaiko on 11/24/14.
 */
public class DBFactory {
    @Produces
    @PersistenceContext(unitName = "TestUnit" )
    EntityManager em;
}
