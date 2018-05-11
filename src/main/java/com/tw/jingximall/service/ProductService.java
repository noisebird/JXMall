package com.tw.jingximall.service;

import com.tw.jingximall.entity.Product;

import java.util.List;

/**
 * Created by wangjie on 2018/5/11.
 */
public interface ProductService {

    //通过Id查找商品接口
    Product findProductById(int id);

    //查找全部商品接口
    List<Product> findAllProduct();

    //通过name查找商品接口
    List<Product> findProductByName(String name);

    //通过name和description模糊查找商品接口
    List<Product> findProductByNameAndDim(String name, String description);

    //添加商品接口
    Product addProduct(Product product);

    //修改商品信息接口
    int modifyProductInfo(Product product, int id);
}
