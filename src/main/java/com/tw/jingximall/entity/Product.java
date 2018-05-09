package com.tw.jingximall.entity;

import javax.persistence.*;

/**
 * Created by wangjie on 2018/5/9.
 */
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private  String name;
    @Column
    private double price;
    @Column
    private  String description;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="inventoryId")
    private  Inventory inventory;

    public Product( String name, String description,double price) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


}
