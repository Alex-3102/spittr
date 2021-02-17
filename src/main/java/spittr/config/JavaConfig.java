package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import spittr.Spittle;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author arc3102
 * @date 2021/1/26 22:24
 */
@Configuration
@ComponentScan
public class JavaConfig {
//  注册JDBC模板JdbcTemplate
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

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

    private List<Spittle> createSpittleList(int count) {
        List<Spittle> spittles = new ArrayList<Spittle>();
        for (int i=0; i < count; i++) {
            spittles.add(new Spittle("Spittle " + i, new Date()));
        }
        return spittles;
    }
}
