package com.tw.jingximall.entity;

import javax.persistence.*;

/**
 * Created by wangjie on 2018/5/9.
 */
@Entity
@Table(name="inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int count=0;
    @Column
    private int lockedCount=0;

    @OneToOne(targetEntity = Product.class)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Product product;

    public Inventory() {
    }

    public Inventory(int count, int lockedCount) {
        this.count = count;
        this.lockedCount = lockedCount;
    }

    public int getLockedCount() {
        return lockedCount;
    }

    public void setLockedCount(int lockedCount) {
        this.lockedCount = lockedCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
