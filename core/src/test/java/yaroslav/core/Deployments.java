package yaroslav.core;

import org.apache.commons.io.FilenameUtils;
import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Node;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yaroslav on 11/29/14.
 */
@ArquillianSuiteDeployment
public class Deployments {
    public static String TEST_CLASSIFIER = "test";
    @Deployment
    public static WebArchive createDeployment() {
        System.out.println("Create core WebArchive for Integration Test");
/*        File[] libs = Maven.resolver().loadPomFromFile("pom.xml").
                importCompileAndRuntimeDependencies().
                resolve().
                withTransitivity().
                asFile();*/

        List<JavaArchive> libs = Maven.resolver().loadPomFromFile("pom.xml").
                importCompileAndRuntimeDependencies().resolve().withTransitivity().asList(JavaArchive.class);

        for (JavaArchive lib : libs) {
            if(lib.getName().equals("model-1.0-SNAPSHOT.jar")){
                lib.delete("/META-INF/persistence.xml");
                Node node = lib.get("/META-INF/test/persistence.xml");
//                lib.add(new ClassLoaderAsset("test-persistence.xml"), "/META-INF/persistence.xml");
                lib.add(node.getAsset(), "/META-INF/persistence.xml");
            }
        }


/*        File[] libs = Maven.resolver().loadPomFromFile("pom.xml").resolve("WildFlyJpa:utils",
                "WildFlyJpa:model:jar:test:1.0-SNAPSHOT").withTransitivity().asFile();*/

        //File[] libs = resolveLibraries("WildFlyJpa:utils", "WildFlyJpa:model");

        //MavenStrategyStage model = Maven.resolver().loadPomFromFile("pom.xml").resolve("WildFlyJpa:model:test:?");

        WebArchive war = ShrinkWrap.create(WebArchive.class).addPackages(true, "yaroslav.core");
        war.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        war.addAsLibraries(libs);
        System.out.println(war.toString(true));
        return war;
    }

}
