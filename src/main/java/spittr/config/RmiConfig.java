package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import spittr.service.SpitterService;

/**
 * @author arc3102
 * @date 2021/2/24 12:07
 */
public class RmiConfig {

    @Bean
    public RmiServiceExporter rmiExporter(SpitterService spitterService) {
        RmiServiceExporter rmiExporter = new RmiServiceExporter();
        rmiExporter.setService(spitterService);
        rmiExporter.setServiceName("SpitterService");
        rmiExporter.setServiceInterface(SpitterService.class);
        return rmiExporter;
    }
}
