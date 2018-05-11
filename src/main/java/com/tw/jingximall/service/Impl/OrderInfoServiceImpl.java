package com.tw.jingximall.service.Impl;

import com.tw.jingximall.entity.OrderInfo;
import com.tw.jingximall.repository.OrderRepository;
import com.tw.jingximall.repository.ProductRepository;
import com.tw.jingximall.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by wangjie on 2018/5/10.
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderInfo findOrderInfoById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<OrderInfo> findOrderInfoByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public int withDrawnOrder(int id) {
        return orderRepository.updateStatusByWithdrawn(getDateString(), id);
    }

    @Override
    public int paidOrder(int id) {
        return orderRepository.updateStatusByWithdrawn(getDateString(), id);
    }

    @Override
    public OrderInfo addOrder(OrderInfo orderInfo) {
        return orderRepository.saveAndFlush(orderInfo);
    }

//  获取当前日期字符串
    private String getDateString() {
        return new Date(System.currentTimeMillis()).toString();
    }
}
