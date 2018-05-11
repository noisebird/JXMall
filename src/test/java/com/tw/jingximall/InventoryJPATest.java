package com.tw.jingximall;

import com.tw.jingximall.entity.Inventory;
import com.tw.jingximall.repository.InventoryRepository;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

/**
 * Created by wangjie on 2018/5/9.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class InventoryJPATest {

   @Autowired
    private InventoryRepository inventoryRepository;

    @Before
    public void setUp() throws Exception {
        //本地启动mysql，创建employee_db数据库
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:mysql://192.168.99.100:3306/test", "root", "root");
        //清除schema_version中记录所有表结构，视图，存储过程，函数以及所有的数据等都会被清除。
        flyway.clean();
        // 此命令会自动检查数据库脚本是否有变化，如果有变化，则执行脚本，更新数据库版本，如果数据库初始状态是空库，
        // 则会自动创建schema_version 表，用于存储数据库操作的版本记录，
        flyway.migrate();
    }


    @Test
    public void should_find_inventory_will_return_the_correct_result() throws Exception {
        Inventory inventory=inventoryRepository.findById(1);
        assertTrue(inventory.getLockedCount()==107);
    }

    @Test
    public void should_update_inventory_will_add_when_count_is_positive() throws Exception {
        int rows=inventoryRepository.updateInventory(20,1);
        Inventory inventory=inventoryRepository.findById(1);
        assertTrue(rows==1);
        assertTrue(inventory.getCount()==133);
    }

    @Test
    public void should_update_inventory_will_reduce_when_count_is_negative() throws Exception {
        int rows=inventoryRepository.updateInventory(-20,1);
        Inventory inventory=inventoryRepository.findById(1);
        assertTrue(rows==1);
        assertTrue(inventory.getCount()==93);
    }

    @Test
    public void should_update_locked_inventory_will_add_when_lockedCount_is_positive() throws Exception {
        int rows=inventoryRepository.updateLockedInventory(20,1);
        Inventory inventory=inventoryRepository.findById(1);
        assertTrue(rows==1);
        assertTrue(inventory.getCount()==127);
    }
    @Test
    public void should_update_locked_inventory_will_return_effect_rows() throws Exception {
        assertTrue(inventoryRepository.updateLockedInventory(-20,1)==1);
        Inventory inventory=inventoryRepository.findById(87);
    }
}
