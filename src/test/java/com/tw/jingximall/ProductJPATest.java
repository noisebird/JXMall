package com.tw.jingximall;

import com.tw.jingximall.entity.Inventory;
import com.tw.jingximall.entity.Product;
import com.tw.jingximall.repository.ProductRepository;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ProductJPATest {
    @Autowired
    private ProductRepository productRepository;

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
    public void should_add_product_then_return_product() {
        Product product =new Product("test1","test",20,new Inventory());
        Product actualProduct=productRepository.saveAndFlush(product);
        assertTrue(actualProduct.getDescription().equals(product.getDescription()));

    }

    @Test
    public void should_find_prduct_by_id_will_return_true() throws Exception {
        assertTrue(productRepository.findProductById(1).getName().equals("test666"));
    }

    @Test
    public void should_update_product_will_reutrn_true() throws Exception {
        assertTrue(productRepository.updateProduct("test666","test",1000,1)==1);
        assertTrue(productRepository.findProductById(1).getPrice()==1000);
    }


}