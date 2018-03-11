package com.khh.base.dao;

/**
 * Created by 951087952@qq.com on 2018/3/11.
 * 基础dao
 */
public interface BaseDao<T> {


    int deleteByPrimaryKey(String id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
