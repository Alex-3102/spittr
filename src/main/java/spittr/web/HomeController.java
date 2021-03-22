package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

/**
 * @author arc3102
 * @date 2021/1/24 21:22
 */
@Controller
@RequestMapping({"/", "homepage"})
public class HomeController {

//    @Resource(name="jdbc/spitterds")
    @Autowired
    private DataSource dataSource;

//    @Resource(name="dataSource")
//    @Autowired
//    private JndiObjectFactoryBean jndiObjectFactoryBean;

    @Autowired
    private ApplicationContext contextBean;

    @RequestMapping(method = RequestMethod.GET)
    public String home() throws Exception {
        for(String p : Arrays.asList(contextBean.getBeanDefinitionNames())){
            System.out.println(">>>>>>" + p);
        }
        System.out.println("+++++++++++++++++++++++++++++++++++\n合计 ");
        System.out.println(Arrays.asList(contextBean.getBeanDefinitionNames()).size());
        System.out.println(dataSource);
        Connection con = dataSource.getConnection();
        System.out.println(con);
//        String sql = "SELECT name FROM test WHERE id= ?";
//        PreparedStatement ps = con.prepareStatement(sql);
//        ps.setInt(1, 1);
//        ResultSet rs = ps.executeQuery();
//        rs.next();
//        System.out.println(rs.getString("name"));
//        System.out.println(jndiObjectFactoryBean);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return "home";
    }
}
