package com.pyb.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
    /*基于生产者实现接口的动态代理*/
    public static void main(String[] args) {

        final Producer producer = new Producer();
        /*创建基于接口的动态代理对象，被代理类一定要实现接口才能被代理*/
        /*不修改原方法，对方法增强
        * 固定写法，内部类提供增强的代码方法*/

        Iproducer p = (Iproducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(),
                producer.getClass().getInterfaces(),
                //所有的生产者的方法都要通过这里
                //Object proxy代表要代理的对象：生产者;
                // Method method表示当前通过的方法;
                // Object[] args为方法的参数集合
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object invoke = null;
                        if ("saleProduct".equals(method.getName())){
                            /*方法增强,价格打八折*/
                            Float money = (Float) args[0];

                            invoke = method.invoke(producer, money * 0.8f);

                        }
                        return invoke;
                    }
                });

        p.saleProduct(1000f);
        System.out.println(p);
    }
}
