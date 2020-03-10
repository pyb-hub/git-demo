package com.pyb.proxy.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Client {
    /*基于生产者子类的动态代理*/
    public static void main(String[] args) {

        final Producer producer = new Producer();
        /*创建基于子类的动态代理对象，被代理类不能是final才能被代理*/
        /*不修改原方法，对方法增强
          需要引入第三方jar包cglib
          对象是Enhancer类
        * 固定写法，内部类提供增强的代码方法*/
        Producer p = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            //所有的生产者的方法都要通过这里
            //Object proxy代表要代理的对象：生产者;
            // Method method表示当前通过的方法;
            // Object[] args为方法的参数集合
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                Object invoke = null;
                if ("saleProduct".equals(method.getName())) {
                    /*方法增强,价格打八折*/
                    Float money = (Float) args[0];
                    invoke = method.invoke(producer, money * 0.8f);
                }
                return invoke;
            }
        });

        p.saleProduct(1000f);
    }
}
