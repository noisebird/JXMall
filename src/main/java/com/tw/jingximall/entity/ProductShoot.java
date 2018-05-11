package com.tw.jingximall.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by wangjie on 2018/5/10.
 */
@Entity
@Table(name="productShoot")
public class ProductShoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productName;
    private String productDescription;
    private int purchaseCount;
    private double purchasePrice;
    private int productId;
//    private int orderId;

    public ProductShoot() {
    }

    public ProductShoot(String productDescription, String productName, int purchaseCount, double purchasePrice, int productId) {
        this.productDescription = productDescription;
        this.productName = productName;
        this.purchaseCount = purchaseCount;
        this.purchasePrice = purchasePrice;
        this.productId = productId;
    }
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH },fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name ="orderId")
    private OrderInfo orderInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

//    public int getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(int orderId) {
//        this.orderId = orderId;
//    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
