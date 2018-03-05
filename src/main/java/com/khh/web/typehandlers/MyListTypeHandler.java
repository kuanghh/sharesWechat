package com.khh.web.typehandlers;

import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/7/26.
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(value = List.class)
public class MyListTypeHandler extends BaseTypeHandler<List> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List list, JdbcType jdbcType) throws SQLException {

        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(list);
        String s = jsonArray.toJSONString();
        System.out.println("json转换后的数据是:" + s);
        preparedStatement.setString(i,s);
    }

    @Override
    public List getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String jsonArry = resultSet.getString(s);
        return JSONArray.parseArray(jsonArry, String.class);
    }

    @Override
    public List getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String jsonArry = resultSet.getString(i);
        return JSONArray.parseArray(jsonArry, String.class);
    }

    @Override
    public List getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String jsonArry = callableStatement.getString(i);
        return JSONArray.parseArray(jsonArry, String.class);
    }
}
