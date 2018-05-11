package com.tw.jingximall.service.Impl;

import com.tw.jingximall.entity.Inventory;
import com.tw.jingximall.entity.Product;
import com.tw.jingximall.repository.ProductRepository;
import com.tw.jingximall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangjie on 2018/5/9.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        product.setInventory(new Inventory());
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product findProductById(int id) {
        return productRepository.findProductById(id);
    }

    @Override
    public int modifyProductInfo(Product product, int id) {
        return productRepository.updateProduct(product.getName(), product.getDescription(), product.getPrice(), id);
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Override
    public List<Product> findProductByNameAndDim(String name, String description) {
        return productRepository.findByNameAndDescriptionContaining(name, description);
    }
}
