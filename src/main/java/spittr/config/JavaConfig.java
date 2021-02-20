package spittr.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import spittr.Spittle;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author arc3102
 * @date 2021/1/26 22:24
 */
@Configuration
@EnableTransactionManagement
@ComponentScan
public class JavaConfig {
//  注册JDBC模板JdbcTemplate

//    @Inject
//    private SessionFactory sessionFactory;

//    @Bean
//    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
//        return new HibernateTransactionManager(sessionFactory);
//    }


//    @Bean
//    public SpittleRepository spittleRepository(){
//        SpittleRepository mockRepository = Mockito.mock(SpittleRepository.class);
//        return mockRepository;
//    }

//    @Bean
//    public LocalValidatorFactoryBean localValidatorFactoryBean(){
//        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
//        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
//        return localValidatorFactoryBean;
//    }

//    @Bean
//    public Validator validator(){
//
//        ValidatorFactory validatorFactory = Validation.byProvider( clzz )
//                .configure()
//                .addProperty( "hibernate.validator.fail_fast", "false" )
//                .buildValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
//
//        return validator;
//    }

//    @Bean
//    public ValidationProviderResolver validationProvider(){
//        return new ();
//    }
//    基于java代码配置JNDI
//    @Bean
//    public JndiObjectFactoryBean jndiObjectFactoryBean() {
//        JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
//        jndiObjectFB.setJndiName("java:comp/env/jdbc/spitterds");
//        jndiObjectFB.setResourceRef(true);
//        System.out.println("sad123asdasdsadasdasdassadsa");
//        jndiObjectFB.setProxyInterface(javax.sql.DataSource.class);
//        System.out.println("sad123asdasdsadasdasdassadsa");
//        return jndiObjectFB;
//    }

//    @Autowired
//    private JndiObjectFactoryBean jndiObjectFactoryBean;

//    @Bean
//    public DataSource dataSource(){
//        return (DataSource) jndiObjectFactoryBean.getObject();
//    }

}
