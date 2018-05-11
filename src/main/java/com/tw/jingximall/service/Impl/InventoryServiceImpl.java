package com.tw.jingximall.service.Impl;

import com.tw.jingximall.entity.Inventory;
import com.tw.jingximall.repository.InventoryRepository;
import com.tw.jingximall.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangjie on 2018/5/9.
 */
@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;
    @Override
    public int modifyActualCount(int count,int id){
        return inventoryRepository.updateInventory(count, id);
    }

    @Override
    public  int modifyLockedCount(int lockedCount,int id){
        return inventoryRepository.updateLockedInventory(lockedCount,id);
    }
}
