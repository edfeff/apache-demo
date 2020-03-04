package tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;

/**
 * @author wpp
 */
public class Tom {
    public static void main(String[] args) throws LifecycleException, ServletException {
        Tomcat tomcat = new Tomcat();
        String baseDir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(baseDir);
        tomcat.setBaseDir(baseDir);
        tomcat.setPort(8080);
        tomcat.addWebapp("/", baseDir);
        tomcat.enableNaming();
        tomcat.start();
        tomcat.getServer().await();
    }
}
