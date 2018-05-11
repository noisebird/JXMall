package com.tw.jingximall.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wangjie on 2018/5/9.
 */
@Entity
@Table(name = "logistics")
public class Logistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String logisticsStatus;
    private String outboundTime;
    private String signedTime;
    private String deliveryMan="李师傅";
    private int orderId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="orderId",insertable = false,updatable = false)
    private OrderInfo orderInfo;

    public Logistics() {
    }

    public Logistics(String logisticsStatus,int orderId) {
        this.logisticsStatus = logisticsStatus;
    }

    public Logistics(String logisticsStatus, String outboundTime, String signedTime, String deliveryMan) {
        this.logisticsStatus = logisticsStatus;
        this.outboundTime = outboundTime;
        this.signedTime = signedTime;
        this.deliveryMan = deliveryMan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(String logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public String getOutboundTime() {
        return outboundTime;
    }

    public void setOutboundTime(String outboundTime) {
        this.outboundTime = outboundTime;
    }

    public String getSignedTime() {
        return signedTime;
    }

    public void setSignedTime(String signedTime) {
        this.signedTime = signedTime;
    }

    public String getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(String deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
