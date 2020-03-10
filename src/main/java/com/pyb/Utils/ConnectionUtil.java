package com.pyb.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*获取当前线程的连接*/
@Component
public class ConnectionUtil {

    /*定义当前线程*/
    private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    @Autowired
    private DataSource dataSource;
    /*获取当前线程的连接*/
    public Connection getThreadConnection() {


            try {
                Connection connection;
                /*获取线程上的连接*/
                connection = threadLocal.get();
                /*判断线程有没有连接*/
                if (connection == null) {
                    /*从连接池获取连接，存入当前线程中*/
                    connection = dataSource.getConnection();
                    threadLocal.set(connection);
                }
                /*返回当前线程上的连接*/
                return connection;
            } catch (SQLException e) {
                throw new RuntimeException();
            }

    }
    public void removeConnection(){
        /*把线程上连接解除，防止多线程干扰*/
        threadLocal.remove();
    }
}
