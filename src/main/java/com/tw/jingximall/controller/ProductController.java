package com.tw.jingximall.controller;


import com.tw.jingximall.entity.Product;
import com.tw.jingximall.exception.IdNotFoundException;
import com.tw.jingximall.service.Impl.ProductServiceImpl;
import com.tw.jingximall.util.Tools;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Created by wangjie on 2018/5/9.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;


    //保存商品
    @PostMapping()
    public ResponseEntity<?> saveProduct(HttpServletRequest request, @RequestBody Product product) {
        if (product.getName().equals("")) {
            return new ResponseEntity<String>("input params fomat is illegal", HttpStatus.BAD_REQUEST);
        }
        Product result = productService.addProduct(product);
        if (product == null) {
            return new ResponseEntity<String>("save product failure", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Product>(result, Tools.getHttpHeader(request,result.getId()), HttpStatus.CREATED);
    }


    //更新商品
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestBody Product product) {
        if (productService.findProductById(id) == null) {
            throw new IdNotFoundException(id);
        }
        if (productService.modifyProductInfo(product, id) == 0) {
            return new ResponseEntity<String>("update product failure", HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
    }

    //根据id查找商品
    @GetMapping(value = "{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        Product result = productService.findProductById(id);
        if (productService.findProductById(id) == null) {
            throw new IdNotFoundException(id);
        }
        return new ResponseEntity<Product>(result, HttpStatus.OK);
    }

    //根据name查询，根据name和description模糊查询，以及查询全部
    @GetMapping
    public ResponseEntity<?> getProductByName(@RequestParam(required = false) String name, @RequestParam(required = false) String description) {
        List<Product> list = productService.findProductByName(name);
        if (name == null && description == null) {
            return new ResponseEntity<List<Product>>(productService.findAllProduct(), HttpStatus.OK);
        } else if (description == null) {
            return new ResponseEntity<List<Product>>(productService.findProductByName(name), HttpStatus.OK);
        }
        return new ResponseEntity<List<Product>>(productService.findProductByNameAndDim(name, description), HttpStatus.OK);
    }


}
