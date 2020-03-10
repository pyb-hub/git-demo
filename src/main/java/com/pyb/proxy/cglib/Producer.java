package com.pyb.proxy.cglib;



public class Producer {

    /*生产者产出产品*/
    private StringBuffer name;
    private Float price;

    public void produce(StringBuffer name,Float price){

        this.name=name;
        this.price=price;
    }
    public void saleProduct(Float m){
        System.out.println("卖出了商品，价格为"+m);
    }

    public void saledService(Float m){
        System.out.println("提供售后服务，价格为"+m);
    }
}
