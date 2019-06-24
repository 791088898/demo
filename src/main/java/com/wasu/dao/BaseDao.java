package com.wasu.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: cs
 * @Date: 2019/6/13 22:10
 */
public interface BaseDao<T> {
    List<T> selectAll();
}
