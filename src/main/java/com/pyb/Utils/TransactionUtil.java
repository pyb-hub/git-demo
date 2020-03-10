package com.pyb.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/*事务管理的工具类*/
@Component
public class TransactionUtil {

    @Autowired
    private ConnectionUtil connectionUtil;

    public void beginTransaction(){

        try {
            connectionUtil.getThreadConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void commitTransaction(){

        try {
            connectionUtil.getThreadConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void rollbackTransaction(){

        try {
            connectionUtil.getThreadConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /*把连接和线程分开*/
    public void releaseTransaction(){

        try {
            connectionUtil.getThreadConnection().close();
            connectionUtil.removeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
