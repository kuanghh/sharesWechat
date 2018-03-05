package com.khh.web.typehandlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;

import java.util.*;
import java.util.Date;

/**
 * 在我们启用了我们自定义的这个TypeHandler之后，数据的读写都会被这个类所过滤
 * Created by 951087952@qq.com on 2017/7/26.
 */
@MappedJdbcTypes(JdbcType.TIMESTAMP)//映射jdbc为TimeStamp的类型
@MappedTypes(value = Date.class)//映射java的类型是Date
public class MyDateStampTypeHandler extends BaseTypeHandler<Date> {


    /**
     * 在setNonNullParameter方法中，我们重新定义要写往数据库的数据
     * @param preparedStatement
     * @param i
     * @param date
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        System.out.println("设置参数进去了");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017,8,20,18,30,30);
        preparedStatement.setTimestamp(i,new Timestamp(calendar.getTimeInMillis()));
    }


    //在另外三个方法中我们将从数据库读出的数据类型进行转换。
    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getDate(s);
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getDate(i);
    }

    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getDate(i);
    }
}
