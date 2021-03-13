package com.k7;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DbConnection {
    private final HikariConfig config = new HikariConfig();

    public void setConnectionProperties(String jdbcUrl, String username, String password) {
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(8);
        config.setMinimumIdle(4);
    }

    public DataSource getDataSource(){
        DataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
}
