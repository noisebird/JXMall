package com.tw.jingximall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjie on 2018/5/10.
 */
@Entity
@Table(name="orderInfo")
@Getter
@Setter
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double totalPrice;
    private String status;
    public String createTime;
    private String finishTime;
    private String paidTime;
    private String withdrawnTime;
    private int userId=1;

    @JsonIgnore
    @OneToOne(mappedBy = "orderInfo")
    private Logistics logistics;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "orderInfo")
    private List<ProductShoot> purchaseItemList =new ArrayList<ProductShoot>();

    public OrderInfo() {
    }

    public OrderInfo(double totalPrice, String status, String createTime, String finishTime, String paidTime, String withdrawnTime,int userId) {
        this.totalPrice = totalPrice;
        this.status = status;
        this.createTime = createTime;
        this.finishTime = finishTime;
        this.paidTime = paidTime;
        this.withdrawnTime = withdrawnTime;
        this.userId=userId;
    }
    public void addOrderItem(List<ProductShoot> list){

        for(ProductShoot productShoot:list){
            productShoot.setOrderInfo(this);//用关系维护端来维护关系
            this.purchaseItemList.add(productShoot);
        }

    }
}
