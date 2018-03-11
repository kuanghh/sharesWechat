package com.khh.base.service.impl;

import com.khh.base.dao.BaseDao;
import com.khh.base.service.BaseService;

/**
 * Created by 951087952@qq.com on 2018/3/11.
 * 基础实现类
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    private BaseDao<T> baseDao;

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public int insertPO(T t, boolean all) {
        if(all){
           return baseDao.insert(t);
        }else{
            return baseDao.insertSelective(t);
        }
    }

    @Override
    public T selectByPK(String PK) {
        return baseDao.selectByPrimaryKey(PK);
    }

    @Override
    public int updatePO(T t, boolean all) {
        if(all){
            return baseDao.updateByPrimaryKey(t);
        }else{
            return baseDao.updateByPrimaryKeySelective(t);
        }
    }

    @Override
    public int deleteByPK(String PK) {
        return baseDao.deleteByPrimaryKey(PK);
    }
}
