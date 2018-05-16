package com.tw.jingximall.service;

import com.tw.jingximall.entity.OrderInfo;
import com.tw.jingximall.entity.ProductShoot;

import java.util.List;

/**
 * Created by wangjie on 2018/5/11.
 */
public interface OrderInfoService {
    //根据订单Id查找订单接口
    OrderInfo findOrderInfoById(int id);

    //通过userId查找订单接口
    List<OrderInfo> findOrderInfoByUserId(int userId);

    //撤销订单接口
    int withDrawnOrder(int id);

    //订单支付接口
    int paidOrder(int id);

    //添加订单接口
    OrderInfo addOrder(List<ProductShoot> productShoots);
}
