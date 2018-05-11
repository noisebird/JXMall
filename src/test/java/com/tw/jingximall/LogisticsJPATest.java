package com.tw.jingximall;

import com.tw.jingximall.entity.Logistics;
import com.tw.jingximall.repository.InventoryRepository;
import com.tw.jingximall.repository.LogisticsRepository;
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
public class LogisticsJPATest {
    @Autowired
    private LogisticsRepository logisticsRepository;

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
    public void should_add_Logistics_will_return_success_result() throws Exception {
        Logistics logistics = new Logistics("unpaid", "", "", "李师傅");
        Logistics expectedLoggistics = logisticsRepository.saveAndFlush(logistics);
        assertTrue(expectedLoggistics.getId()==2);
    }

    @Test
    public void should_find_logistics_detail_info_by_info_will_return_success_result() throws Exception {

        Logistics expectedLoggistics = logisticsRepository.findById(1);
        assertTrue(expectedLoggistics.getDeliveryMan().equals("李师傅"));
    }

    @Test
    public void should_ship_logistics_status_will_reurn_success_result() throws Exception {
        int rows=logisticsRepository.updateLogisticsStatus("2018-12-12",1);
        Logistics expectedLoggistics = logisticsRepository.findById(1);
        assertTrue(rows==1);
        assertTrue(expectedLoggistics.getOutboundTime().equals("2018-12-12")&&expectedLoggistics.getLogisticsStatus().equals("shipping"));
    }

    @Test
    public void should_signed_logistics_then_status_will_change() throws Exception {
        int rows=logisticsRepository.updateLogisticsStatusAsSigned("2018-12-12",1);
        Logistics expectedLoggistics = logisticsRepository.findById(1);
        assertTrue(rows==1);
        assertTrue(expectedLoggistics.getSignedTime().equals("2018-12-12")&&expectedLoggistics.getLogisticsStatus().equals("signed"));
    }
}
