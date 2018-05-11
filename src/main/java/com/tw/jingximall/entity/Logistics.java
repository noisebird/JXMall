package com.tw.jingximall.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wangjie on 2018/5/9.
 */
@Entity
@Table(name = "logistics")
@Setter
@Getter
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
}
