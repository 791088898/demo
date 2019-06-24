package com.wasu.service.impl;

import com.wasu.dao.ActLogDao;
import com.wasu.entity.ActLogPo;
import com.wasu.service.ActLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: cs
 * @Date: 2019/6/13 22:13
 */
@Service
@Transactional
@CacheConfig(cacheNames = "actlog")
public class ActLogServiceImpl implements ActLogService {

    @Autowired
    ActLogDao actLogDao;

    @Cacheable(key = "'actlogCache'")
    @Override
    public List<ActLogPo> selectAll() {
        return actLogDao.selectAll();
    }

}
