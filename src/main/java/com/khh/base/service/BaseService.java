package com.khh.base.service;

/**
 * Created by 951087952@qq.com on 2018/3/11.
 */
public interface BaseService<T> {

    int insertPO(T t, boolean all);

    T selectByPK(String PK);

    int updatePO(T t, boolean all);

    int deleteByPK(String PK);

}
