package com.tw.jingximall.controller;

import com.tw.jingximall.entity.Logistics;
import com.tw.jingximall.entity.OrderInfo;
import com.tw.jingximall.entity.Product;
import com.tw.jingximall.entity.ProductShoot;
import com.tw.jingximall.exception.IdNotFoundException;
import com.tw.jingximall.service.Impl.InventoryServiceImpl;
import com.tw.jingximall.service.Impl.OrderInfoServiceImpl;
import com.tw.jingximall.service.Impl.ProductServiceImpl;
import com.tw.jingximall.service.LogisticsService;
import com.tw.jingximall.util.Tools;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjie on 2018/5/10.
 */

@RestController
@RequestMapping("/orders")
public class OrderInfoController {
    @Autowired
    OrderInfoServiceImpl orderInfoService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    LogisticsService logisticsService;

    //创建订单
    @PostMapping
    public ResponseEntity<?> createOrder(HttpServletRequest request, @RequestBody List<ProductShoot> productShoots) {
        OrderInfo producedOrderInfo = orderInfoService.addOrder(productShoots);
        if (producedOrderInfo != null) {
            orderInfoService.lockInventory(productShoots, true);
        }
        return new ResponseEntity<OrderInfo>(producedOrderInfo, Tools.getHttpHeader(request,producedOrderInfo), HttpStatus.CREATED);
    }

    //根据id查找订单
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable int id) {
        OrderInfo orderInfo = orderInfoService.findOrderInfoById(id);
        if (orderInfo == null) {
             throw new IdNotFoundException(id);
        }
        return new ResponseEntity<OrderInfo>(orderInfo, HttpStatus.OK);
    }

    //通过用户Id查找订单
    @GetMapping
    public ResponseEntity<?> getOrderByUserId(@RequestParam int userId) {
        List<OrderInfo> orderInfo = orderInfoService.findOrderInfoByUserId(userId);
        return new ResponseEntity<List<OrderInfo>>(orderInfo, HttpStatus.OK);
    }

    //修改订单状态，包括，支付订单，撤销订单
    @PutMapping("/{id}")
    public ResponseEntity<?> changeOrderStatus(@PathVariable int id, @RequestParam String orderStatus) {
        OrderInfo orderInfo = orderInfoService.findOrderInfoById(id);
        if (orderInfo == null) {
            throw new IdNotFoundException(id);
        }
        //支付订单会生成快递单
        if (orderStatus.equals("paid")) {
            orderInfoService.paidOrder(id);
            logisticsService.addLogistics(new Logistics("paid",id));
            return new ResponseEntity<OrderInfo>(HttpStatus.NO_CONTENT);
        } else if (orderStatus.equals("withdrawn")) {
            orderInfoService.withDrawnOrder(id);
            orderInfoService.lockInventory(orderInfo.getPurchaseItemList(), false);
            return new ResponseEntity<OrderInfo>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>("input param is not correct!", HttpStatus.BAD_REQUEST);
    }


}
