package yaroslav.core;

import org.jboss.arquillian.junit.Arquillian;

import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class UserServiceIT {
    @EJB
    UserService userService;



    @Test
    public void mockTest(){
        System.out.println("Inside mock test");
        System.out.println("User = "+ userService.get(1));
    }

    @Test
    public void testName() throws Exception {



    }
}