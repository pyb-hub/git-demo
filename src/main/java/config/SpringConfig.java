package config;


import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.pyb")
@Import(JDBCConfig.class)
@PropertySource("jdbcConfig.properties")/*导入property文件*/
//@EnableTransactionManager //开启事务支持
public class SpringConfig {

}
