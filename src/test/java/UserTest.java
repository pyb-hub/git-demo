import com.pyb.domain.User;
import com.pyb.service.IUserService;
import config.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)/*也可以是location="classpath:bean.xml",classpath代表的是类路径*/
public class UserTest {


    @Autowired
    @Qualifier("uProxy1")
    //@Qualifier("userService")
    private IUserService us;

    @Test
    public void testFindAll(){

        /*获取bean中的userService对象，也可以在上面自动获取*/
        /*ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserService us = ac.getBean("userService", IUserService.class);*/
        List<User> users = us.findAll();
        for (User u : users) {
            System.out.println(u);
        }

    }
    @Test
    public void testFindAll1(){

        /*获取bean中的userService对象，也可以在上面自动获取*/
        /*ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        IUserService us = ac.getBean("userService", IUserService.class);*/
        List<User> users = us.findAll();
        for (User u : users) {
            System.out.println(u);
        }

    }
    @Test
    public void testInsert(){

         User user = new User();
        user.setUsername("pp");
        user.setAge(11);
        us.insert(user);
    }
    @Test
    public void testTransfer(){



        us.transferAge(1,2,2);
    }

}
