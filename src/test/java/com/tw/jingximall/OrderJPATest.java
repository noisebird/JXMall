package com.tw.jingximall;

import com.tw.jingximall.entity.OrderInfo;
import com.tw.jingximall.entity.ProductShoot;
import com.tw.jingximall.repository.OrderRepository;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Created by wangjie on 2018/5/10.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrderJPATest {

    @Autowired
    private OrderRepository orderRepository;

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
    private String getDateString(){
       return  new Date(System.currentTimeMillis()).toString();
    }
    @Test
    public void should_save_order_will_success() throws Exception {
        OrderInfo order=new OrderInfo(20, "unpaid", getDateString(), "","" , "",1);
        List<ProductShoot> list=new ArrayList<ProductShoot>();
        list.add(new ProductShoot("test666", "test", 3, 32.2, 1));
        list.add(new ProductShoot("test666", "test", 3, 32.2, 1));
        order.addOrderItem(list);
        OrderInfo order1=orderRepository.saveAndFlush(order);
        assertTrue(order1.getPurchaseItemList().size()==2);
    }

    @Test
    public void should_find_order_by_id_will_success() throws Exception {
        OrderInfo order1=orderRepository.findById(1);
        assertTrue(order1.getStatus().equals("unpaid"));
    }
    @Test
    public void should_find_order_by_userid_will_success() throws Exception {
        List<OrderInfo> order1=orderRepository.findByUserId(1);
        assertTrue(order1.get(0).getStatus().equals("unpaid"));
    }
    @Test
    public void should_modify_order_status_by_paid_will_success() throws Exception {
       int rows=orderRepository.updateStatusByPaid(getDateString(),1);
        OrderInfo order1=orderRepository.findById(1);
        assertTrue(order1.getStatus().equals("paid"));
        assertTrue(rows==1);
    }
    @Test
    public void should_modify_order_status_by_withdrawn_will_success() throws Exception {
        int rows=orderRepository.updateStatusByWithdrawn(getDateString(),1);
        OrderInfo order1=orderRepository.findById(1);
        assertTrue(order1.getStatus().equals("withDrawn"));
        assertTrue(rows==1);
    }
}
