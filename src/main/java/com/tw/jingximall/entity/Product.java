package com.tw.jingximall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by wangjie on 2018/5/9.
 */
@Entity
@Table(name = "product")
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  String name;
    private double price;
    private  String description;
    @OneToOne(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
    @JoinColumn(name="inventoryId")
    private  Inventory inventory;

    public Product() {
    }

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price=price;
    }

    public Product(String name, String description,double price, Inventory inventory) {
        this.name = name;
        this.description = description;
        this.price=price;
        this.inventory = inventory;
    }
}
