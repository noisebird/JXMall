package com.tw.jingximall.service.Impl;

import com.tw.jingximall.entity.Inventory;
import com.tw.jingximall.entity.OrderInfo;
import com.tw.jingximall.entity.Product;
import com.tw.jingximall.entity.ProductShoot;
import com.tw.jingximall.repository.OrderRepository;
import com.tw.jingximall.repository.ProductRepository;
import com.tw.jingximall.service.InventoryService;
import com.tw.jingximall.service.OrderInfoService;
import com.tw.jingximall.service.ProductService;
import com.tw.jingximall.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjie on 2018/5/10.
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    @Override
    public OrderInfo findOrderInfoById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<OrderInfo> findOrderInfoByUserId(int userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public int withDrawnOrder(int id) {
        return orderRepository.updateStatusByWithdrawn(Tools.getDateString(), id);
    }

    @Override
    public int paidOrder(int id) {
        return orderRepository.updateStatusByWithdrawn(Tools.getDateString(), id);
    }

    @Override
    public OrderInfo addOrder( List<ProductShoot> productShoots) {

        List<ProductShoot> list = getProductShoots(productShoots);
        OrderInfo orderInfo = new OrderInfo(Tools.getDateString(),"unpaid",calculateTotalPrice(productShoots));
        orderInfo.addOrderItem(list);
        return orderRepository.saveAndFlush(orderInfo);
    }

    private List<ProductShoot> getProductShoots(List<ProductShoot> productShoots) {
        List<ProductShoot> list = new ArrayList<ProductShoot>();
        for (ProductShoot shoot : productShoots) {
            int id = shoot.getProductId();
            Product product = productService.findProductById(id);
            shoot=new ProductShoot(product.getName(),product.getDescription(),product.getPrice(),shoot.getPurchaseCount());
            list.add(shoot);
        }
        return list;
    }

    public void lockInventory(List<ProductShoot> productShoots, boolean flag) {
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
}
