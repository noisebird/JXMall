package com.tw.jingximall.controller;

import com.tw.jingximall.entity.Inventory;
import com.tw.jingximall.service.Impl.InventoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangjie on 2018/5/9.
 */
@RestController
@RequestMapping("inventories")
public class InventoryController {
    @Autowired
    InventoryServiceImpl inventoryService;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActualCount(@PathVariable int id, @RequestBody Inventory inventory){
        int rows=inventoryService.modifyActualCount(inventory.getCount(),id);
        if(rows==1){
            return new ResponseEntity<Inventory>( HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<Inventory>( HttpStatus.NOT_MODIFIED);
        }

    }


}
