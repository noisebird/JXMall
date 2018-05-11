package com.tw.jingximall.repository;

import com.tw.jingximall.entity.Inventory;
import com.tw.jingximall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangjie on 2018/5/9.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findById(int id);

    @Transactional
    @Modifying
    @Query("update Inventory i set i.count=i.count+?1 where i.id=?2")
    int updateInventory(int count, int id);

    @Transactional
    @Modifying
    @Query("update Inventory i set i.lockedCount=i.lockedCount+?1 where i.id=?2")
    int updateLockedInventory(int lockedCount, int id);

}
