package com.tw.jingximall.service;

import com.tw.jingximall.entity.Logistics;

/**
 * Created by wangjie on 2018/5/11.
 */
public interface LogisticsService {

    Logistics addLogistics(Logistics logistics);
    //获取物流详情信息接口
    Logistics findLogisticsDetailById(int id);

    //发货接口
    int shipProduct(int id);

    //    签收商品
    int signProduct(int id);
}
