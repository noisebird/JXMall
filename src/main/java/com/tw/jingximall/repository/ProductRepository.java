package com.tw.jingximall.repository;

import com.tw.jingximall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangjie on 2018/5/9.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    //  根据Id查找商品
    Product findProductById(int id);



}
