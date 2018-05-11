package com.tw.jingximall.controller;

import com.tw.jingximall.entity.Logistics;
import com.tw.jingximall.entity.OrderInfo;
import com.tw.jingximall.entity.Product;
import com.tw.jingximall.entity.ProductShoot;
import com.tw.jingximall.service.Impl.InventoryServiceImpl;
import com.tw.jingximall.service.Impl.OrderInfoServiceImpl;
import com.tw.jingximall.service.Impl.ProductServiceImpl;
import com.tw.jingximall.service.LogisticsService;
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
    InventoryServiceImpl inventoryService;
    @Autowired
    LogisticsService logisticsService;

    //创建订单
    @PostMapping
    public ResponseEntity<?> createOrder(HttpServletRequest request, @RequestBody List<ProductShoot> productShoots) {
        List<ProductShoot> list = getProductShoots(productShoots);
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.addOrderItem(list);
        orderInfo.setCreateTime(getDateString());
        orderInfo.setStatus("unpaid");
        orderInfo.setTotalPrice(calculateTotalPrice(productShoots));
        OrderInfo orderInfo1 = orderInfoService.addOrder(orderInfo);
        if (orderInfo != null) {
            lockInventory(productShoots, true);
        }
        URI url = URI.create(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getRequestURI() + "/" + orderInfo1.getId());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(url);
        return new ResponseEntity<OrderInfo>(orderInfo1, httpHeaders, HttpStatus.CREATED);
    }

    //根据id查找订单
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable int id) {
        OrderInfo orderInfo = orderInfoService.findOrderInfoById(id);
        if (orderInfo == null) {
            return new ResponseEntity<String>("input id is not exit!", HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<String>("input id is not exit!", HttpStatus.NOT_FOUND);
        }
        //支付订单会生成快递单
        if (orderStatus.equals("paid")) {
            orderInfoService.paidOrder(id);
            logisticsService.addLogistics(new Logistics("paid",id));
            return new ResponseEntity<OrderInfo>(HttpStatus.NO_CONTENT);
        } else if (orderStatus.equals("withdrawn")) {
            orderInfoService.withDrawnOrder(id);
            lockInventory(orderInfo.getPurchaseItemList(), false);
            return new ResponseEntity<OrderInfo>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>("input param is not correct!", HttpStatus.BAD_REQUEST);
    }

    private List<ProductShoot> getProductShoots(List<ProductShoot> productShoots) {
        List<ProductShoot> list = new ArrayList<ProductShoot>();
        for (ProductShoot shoot : productShoots) {
            int id = shoot.getProductId();
            Product product = productService.findProductById(id);
            shoot.setProductName(product.getName());
            shoot.setProductDescription(product.getDescription());
            shoot.setPurchasePrice(product.getPrice());
            shoot.setPurchaseCount(shoot.getPurchaseCount());
            list.add(shoot);
        }
        return list;
    }

    private void lockInventory(List<ProductShoot> productShoots, boolean flag) {
        for (ProductShoot shoot : productShoots) {
            int id = shoot.getProductId();
            if (flag) {
                inventoryService.modifyLockedCount(shoot.getPurchaseCount(), id);
            } else {
                inventoryService.modifyLockedCount(-(shoot.getPurchaseCount()), id);
            }

        }

    }

    private double calculateTotalPrice(List<ProductShoot> productShoots) {
        double totalPrice = 0;
        for (ProductShoot shoot : productShoots) {
            int id = shoot.getProductId();
            Product product = productService.findProductById(id);
            totalPrice += product.getPrice() * shoot.getPurchaseCount();

        }
        return totalPrice;
    }

    private String getDateString() {
        return new Date(System.currentTimeMillis()).toString();
    }
}
