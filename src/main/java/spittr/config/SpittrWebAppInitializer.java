package spittr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author arc3102
 * @date 2021/1/24 20:22
 */
@ComponentScan
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ spittr.config.RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ spittr.config.WebConfig.class };
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocations(new String[]{"classpath:spring.xml"});
        System.out.println("123333333333333333333332asdaasewdwdswwwwwwwwwwwwwwwwwwwwwww");
        return context;
    }

    @Override
    protected String[] getServletMappings() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return new String[] { "/" };
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++123333333333333333333333");
        registration.setMultipartConfig(new MultipartConfigElement("", 2097152, 4194304, 0));
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }

}
