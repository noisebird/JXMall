package com.tw.jingximall.controller;

import com.tw.jingximall.entity.Logistics;
import com.tw.jingximall.entity.Product;
import com.tw.jingximall.entity.ProductShoot;
import com.tw.jingximall.exception.IdNotFoundException;
import com.tw.jingximall.service.Impl.InventoryServiceImpl;
import com.tw.jingximall.service.Impl.LogisticsServiceImpl;
import com.tw.jingximall.service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wangjie on 2018/5/9.
 */
@RestController
@RequestMapping("logisticsRecords")
public class LogisticsController {
    @Autowired
    InventoryServiceImpl inventoryService;
    @Autowired
    LogisticsServiceImpl logisticsService;
    @Autowired
    ProductServiceImpl productService;


    @GetMapping("/{id}")
    public ResponseEntity<?> findLogisticsById(@PathVariable int id) {
        Logistics logistics = logisticsService.findLogisticsDetailById(id);
        if(logistics==null){
            throw new IdNotFoundException(id);
        }
        return new ResponseEntity<Logistics>(logistics, HttpStatus.OK);
    }

    @PutMapping("/{id}/orders/{orderId}")
    public ResponseEntity<?> changeLogisticsStatus(@PathVariable int id, @PathVariable int orderId, @RequestParam String logisticsStatus) {

        if(logisticsStatus.equals("shipping")){
            int rows = logisticsService.shipProduct(id);
            return new ResponseEntity<Logistics>(HttpStatus.NO_CONTENT);
        }else if(logisticsStatus.equals("signed")){
            int rows = logisticsService.signProduct(id);
            Logistics logistics=logisticsService.findLogisticsDetailById(id);
            reduceInventoryCount(logistics.getOrderInfo().getPurchaseItemList());
            return new ResponseEntity<Logistics>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>("input param is not validate",HttpStatus.BAD_REQUEST);
    }

    private void reduceInventoryCount(List<ProductShoot> list){
        for(ProductShoot productShoot:list){
            int id=productShoot.getProductId();
            int counts=productShoot.getPurchaseCount();
            Product product=productService.findProductById(id);
            int inventoryId=product.getInventory().getId();
            inventoryService.modifyActualCount(-(counts),inventoryId);
        }

    }
}
