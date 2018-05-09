package com.tw.jingximall.controller;


import com.tw.jingximall.entity.Product;
import com.tw.jingximall.service.ProductSericeImpl;
import org.apache.catalina.connector.Request;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.omg.CORBA.Object;
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
    ProductSericeImpl productService;


//    保存商品
    @PostMapping()
    public ResponseEntity<?> saveProduct(HttpServletRequest request, @RequestBody Product product) {
        if (product.getName().equals("")) {
            return new ResponseEntity<String>("input params fomat is illegal", HttpStatus.BAD_REQUEST);
        }
        Product result = productService.addProduct(product);
        if (product == null) {
            return new ResponseEntity<String>("save product failure", HttpStatus.NO_CONTENT);
        }
        URI url = URI.create(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getRequestURI() + "/" + result.getId());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(url);
        return new ResponseEntity<Product>(result, httpHeaders, HttpStatus.CREATED);
    }




}
