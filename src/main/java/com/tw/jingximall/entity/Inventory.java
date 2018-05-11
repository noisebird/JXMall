package com.tw.jingximall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by wangjie on 2018/5/9.
 */
@Entity
@Table(name = "inventory")
@Setter
@Getter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int count = 0;
    @Column
    private int lockedCount = 0;
    @JsonIgnore
    @OneToOne(mappedBy = "inventory")
    private Product product;

    public Inventory() {
    }

    public Inventory(int count, int lockedCount) {
        this.count = count;
        this.lockedCount = lockedCount;
    }

}
