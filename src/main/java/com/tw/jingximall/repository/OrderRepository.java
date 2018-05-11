package com.tw.jingximall.repository;

import com.tw.jingximall.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangjie on 2018/5/10.
 */
public interface OrderRepository extends JpaRepository<OrderInfo,Long> {

//根据id查找
    OrderInfo findById(int id);
    List<OrderInfo> findByUserId(int userId);
    @Transactional
    @Modifying
    @Query("update OrderInfo o set o.status='paid',o.paidTime=?1 where id=?2")
    int updateStatusByPaid(String paidTime,int id);

    @Transactional
    @Modifying
    @Query("update OrderInfo o set o.status='withDrawn',o.withdrawnTime=?1 where id=?2")
    int updateStatusByWithdrawn(String paidTime,int id);
}
