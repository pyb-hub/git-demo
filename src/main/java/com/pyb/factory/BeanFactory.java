package com.pyb.factory;

import com.pyb.Utils.TransactionUtil;
import com.pyb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*工厂生产代理对象*/
@Component("beanFactory")
public class BeanFactory {
    /*被代理类*/
    @Autowired
    private  IUserService userService;
    //事务对象
    @Autowired
    private TransactionUtil transactionUtil;

    /*获取代理对象*/
    @Bean(name = "uProxy1")
    public IUserService getUserServiceProxy(){

    IUserService uProxy = (IUserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
            userService.getClass().getInterfaces(),
            new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] args)  {

                    Object rsInvoke =null;
                    try {
                        transactionUtil.beginTransaction();
                        rsInvoke = method.invoke(userService, args);
                        transactionUtil.commitTransaction();
                        return rsInvoke;
                    } catch (Exception e) {
                        transactionUtil.rollbackTransaction();

                        throw new RuntimeException();

                    }finally {
                        transactionUtil.releaseTransaction();
                    }

                }
            });
        return uProxy;
    }
}
