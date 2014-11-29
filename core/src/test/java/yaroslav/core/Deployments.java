package yaroslav.core;

import org.apache.commons.io.FilenameUtils;
import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
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


/*        File[] libs = Maven.resolver().loadPomFromFile("pom.xml").resolve("WildFlyJpa:utils",
                "WildFlyJpa:model:jar:test:1.0-SNAPSHOT").withTransitivity().asFile();*/

        File[] libs = resolveLibraries("WildFlyJpa:utils", "WildFlyJpa:model");

        //MavenStrategyStage model = Maven.resolver().loadPomFromFile("pom.xml").resolve("WildFlyJpa:model:test:?");

        WebArchive war = ShrinkWrap.create(WebArchive.class).addPackages(true, "yaroslav.core");
        war.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        war.addAsLibraries(libs);
        System.out.println(war.toString(true));
        return war;
    }


    static File[] resolveLibraries(String... canonicalForms) {
        List<File> result = new ArrayList<File>();
        PomEquippedResolveStage resolver = Maven.resolver().loadPomFromFile("pom.xml");
        for (String form : canonicalForms) {
            System.out.println("INPUT FORM: "+form);
            File lib = resolver.resolve(form).withoutTransitivity().asSingleFile();
            String extension = FilenameUtils.getExtension(lib.getName());
            String[] group_artifact = form.split(":");
            String groupID = group_artifact[0];
            String artifactID = group_artifact[1];

            //Pattern pattern = Pattern.compile(String.format("^%s-(.*)\\.%s$", artifactID, extension));
            String str = "^"+artifactID+"-(.*)\\."+extension+"$";
            System.out.println("PATTERN = "+str);
            Pattern pattern = Pattern.compile(str);
            System.out.println("LIB NAME = "+lib.getName());
            System.out.println("ARTIFACT ID = "+artifactID);
            System.out.println("GROUP ID = "+groupID);
            System.out.println("EXTENSION = "+extension);
            Matcher matcher = pattern.matcher(lib.getName());
            String version = null;
            if(matcher.find()){
                version = matcher.group(1);
                System.out.println("VERSION = "+version );
                try {
                    lib = Maven.resolver().loadPomFromFile("pom.xml").
                            resolve(String.format(groupID + ":" + artifactID + ":" + extension + ":" + TEST_CLASSIFIER + ":" + version)).
                            withoutTransitivity().asSingleFile();

                } catch (Exception ex){
                    System.out.println("INSIDE EXCEPTION "+ex);
                }
            }
            result.add(lib);
        }
        return result.toArray(new File[result.size()]);
    }
}
