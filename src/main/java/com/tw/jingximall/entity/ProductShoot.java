package com.tw.jingximall.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by wangjie on 2018/5/10.
 */
@Entity
@Table(name="productShoot")
@Getter
@Setter
public class ProductShoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productName;
    private String productDescription;
    private int purchaseCount;
    private double purchasePrice;
    private int productId;
    public ProductShoot() {
    }

    public ProductShoot(String productName, String productDescription, double purchasePrice, int purchaseCount) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.purchaseCount = purchaseCount;
        this.purchasePrice = purchasePrice;
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

}
