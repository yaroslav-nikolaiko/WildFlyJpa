package yaroslav.core;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolverSystem;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import yaroslav.utils.IntegrationTest;

import java.io.File;

import static org.junit.Assert.*;

//@Category(IntegrationTest.class)
@RunWith(Arquillian.class)
public class UserServiceTest {

    @Deployment
    public static WebArchive createDeployment() {
        System.out.println("Create core WebArchive for Integration Test");
 /*       File[] libs = Maven.resolver().loadPomFromFile("pom.xml")
                .importCompileAndRuntimeDependencies()
                .resolve().withTransitivity()
                .asFile();*/
               // resolve("WildFlyJpa:model").withTransitivity().asFile();
//        PomEquippedResolveStage pomEquippedResolveStage = Maven.resolver().loadPomFromFile("pom.xml");
        Maven.resolver().loadPomFromFile("/home/yaroslav/workspace/eduvation/WilfFly/core/pom.xml").
                importCompileAndRuntimeDependencies().
                resolve().withTransitivity().
                asFile();
        //DependencyResolvers.use(MavenDependencyResolver.class).artifact("WildFlyJpa:utils:1.0-SNAPSHOT").artifact("WildFlyJpa:model:1.0-SNAPSHOT");

        WebArchive war = ShrinkWrap.create(WebArchive.class).addPackages(true, "yaroslav.core");
        war.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        //war.addAsLibraries(libs);
        System.out.println(war.toString(true));
        return war;
    }

    @Test
    public void mockTest(){
        System.out.println("Inside mock test");
    }



}