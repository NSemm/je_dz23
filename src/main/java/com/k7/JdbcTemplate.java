package com.k7;

import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class JdbcTemplate {
    private DataSource dataSource;

    public <T> List<T> query(String sql, List<Object> param, RowMapper<T> mapper) throws SQLException {
        PreparedStatement statement = getConnect().prepareStatement(sql);
        if (!param.isEmpty()) {
            for (int i = 0; i < param.size(); i++) {
                statement.setObject(i + 1, param.get(i));
            }
        }
        ResultSet rs = statement.executeQuery();
        List<T> res = new ArrayList<>();
        while (rs.next()) {
            T o = mapper.map(rs);
            res.add(o);
        }
        statement.close();
        return res;
    }

    public <T> List<T> query(String sql, RowMapper<T> mapper) throws SQLException {
        return query(sql, new ArrayList<Object>(), mapper);
    }

    public <T> List<T> queryOne(String sql, List<Object> param, RowMapper<T> mapper) throws SQLException {
        PreparedStatement statement = getConnect().prepareStatement(sql);
        if (!param.isEmpty()) {
            for (int i = 0; i < param.size(); i++) {
                statement.setObject(i + 1, param.get(i));
            }
        }
        ResultSet rs = statement.executeQuery();
        List<T> res = new ArrayList<>();
        if (rs.next()) {
            T o = mapper.map(rs);
            res.add(o);
        }
        statement.close();
        return res;
    }

    public <T> List<T> queryOne(String sql, RowMapper<T> mapper) throws SQLException {
        return queryOne(sql, new ArrayList<Object>(), mapper);
    }


    public void update(String sql, List<Object> param) throws SQLException {
        PreparedStatement statement = getConnect().prepareStatement(sql);
        if (!param.isEmpty()) {
            for (int i = 0; i < param.size(); i++) {
                statement.setObject(i + 1, param.get(i));
            }
        }
        statement.execute();
        statement.close();
    }

    private Connection getConnect() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }

    private ResultSet queryForUpdate(String sql, List<Object> param) throws SQLException {
        PreparedStatement statement = getConnect().prepareStatement(sql);
        if (!param.isEmpty()) {
            for (int i = 0; i < param.size(); i++) {
                statement.setObject(i + 1, param.get(i));
            }
        }
        ResultSet rs = statement.executeQuery();
        statement.close();
        return rs;
    }

}
