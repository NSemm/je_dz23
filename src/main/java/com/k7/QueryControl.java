package com.k7;


import javax.sql.DataSource;

public class QueryControl {
    public JdbcTemplate createConnectionJdbs(String jdbcUrl, String username, String password){
        return new JdbcTemplate(createDataSource(setConnection(jdbcUrl, username, password)));
    }
    private DbConnection setConnection (String jdbcUrl, String username, String password){
        DbConnection dbConnectionContacts = new DbConnection();
        dbConnectionContacts.setConnectionProperties(jdbcUrl, username,password);
        return dbConnectionContacts;
    }
    private DataSource createDataSource(DbConnection dbConnection){
        return dbConnection.getDataSource();
    }
}
