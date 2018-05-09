package com.tw.jingximall.service;

import com.tw.jingximall.entity.Inventory;
import com.tw.jingximall.entity.Product;
import com.tw.jingximall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangjie on 2018/5/9.
 */
@Service
public class ProductSericeImpl {
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        product.setInventory(new Inventory());
        return productRepository.saveAndFlush(product);
    }

    public Product findProductById(int id){
        return productRepository.findProductById(id);
    }


}
