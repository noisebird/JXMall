package com.tw.jingximall.repository;

import com.tw.jingximall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangjie on 2018/5/9.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    //根据Id查找商品
    Product findProductById(int id);

    //查找所有商品
    List<Product> findAll();

    //  更新商品
    @Modifying
    @Transactional
    @Query("update Product set name=?1,description=?2,price=?3 where id =?4")
    int updateProduct(String name, String description, double price, int id);

    //根据名字查找商品
    List<Product> findProductByName(String name);

    //根据name和description模糊查询
    List<Product> findByNameAndDescriptionContaining(String name, String description);

}
