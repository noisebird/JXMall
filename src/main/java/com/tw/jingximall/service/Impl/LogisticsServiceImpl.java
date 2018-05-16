package com.tw.jingximall.service.Impl;

import com.tw.jingximall.entity.Logistics;
import com.tw.jingximall.repository.LogisticsRepository;
import com.tw.jingximall.service.LogisticsService;
import com.tw.jingximall.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * Created by wangjie on 2018/5/9.
 */
@Service
public class LogisticsServiceImpl implements LogisticsService {
    @Autowired
    LogisticsRepository logisticsRepository;

    @Override
    public Logistics addLogistics(Logistics logistics) {
        return logisticsRepository.saveAndFlush(logistics);
    }

    @Override
    public Logistics findLogisticsDetailById(int id) {
        return logisticsRepository.findById(id);
    }

    @Override
    public int shipProduct(int id) {
        return logisticsRepository.updateLogisticsStatus(Tools.getDateString(), id);
    }

    @Override
    public int signProduct(int id) {
        return logisticsRepository.updateLogisticsStatus(Tools.getDateString(), id);
    }

}
