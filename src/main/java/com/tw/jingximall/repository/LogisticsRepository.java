package com.tw.jingximall.repository;

import com.tw.jingximall.entity.Logistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangjie on 2018/5/9.
 */
@Repository
public interface LogisticsRepository  extends JpaRepository<Logistics,Long>{
    Logistics findById(int id);

    @Transactional
    @Modifying
    @Query("update Logistics l set l.logisticsStatus='shipping',l.outboundTime=?1 where l.id =?2")
    int updateLogisticsStatus(String outboundTime,int id);

    @Transactional
    @Modifying
    @Query("update Logistics l set l.logisticsStatus='signed',l.signedTime=?1 where l.id =?2")
    int updateLogisticsStatusAsSigned(String signTime,int id);
}
