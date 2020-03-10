package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class JDBCConfig {

    @Value("${driveClass}")
    private String driveClass;
    @Value("${url}")
    private String url;
    @Value("${password}")
    private String password;
    @Value("${username}")
    private String username;

    /*获取QueryRunner对象注入ioc容器中*/
    @Bean(name = "runner")
    @Scope("prototype")
    public QueryRunner getQueryRunner(){
        return new QueryRunner();
    }


    /*获取数据源对象*/
    @Bean(name = "dataSource")
    public DataSource getDataSource(){

        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass(driveClass);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        } catch (PropertyVetoException e) {
            throw new RuntimeException();
        }
    }

}
