package com.tw.jingximall.repository;

import com.tw.jingximall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wangjie on 2018/5/9.
 */
public interface ProductRepository extends JpaRepository<Product,Long> {

}
