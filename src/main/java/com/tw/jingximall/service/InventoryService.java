package com.tw.jingximall.service;

/**
 * Created by wangjie on 2018/5/11.
 */
public interface InventoryService {
    //修改实际库存接口
    int modifyActualCount(int count, int id);

    //修改锁定库存接口
    int modifyLockedCount(int lockedCount, int id);
}
