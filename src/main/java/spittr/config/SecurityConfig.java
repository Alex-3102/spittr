package spittr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * @author arc3102
 * @date 2021/2/12 13:13
 */
//@Configuration
//@EnableWebMvcSecurity
public class SecurityConfig  {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //使用内存用户存储
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER").and()
//                .withUser("admin").password("password").roles("USER", "ADMIN");
//    }
}
