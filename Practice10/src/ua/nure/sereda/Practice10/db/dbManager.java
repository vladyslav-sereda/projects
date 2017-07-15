package ua.nure.sereda.Practice10.db;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * Created by Vladyslav.
 */
public class dbManager {
    MysqlDataSource dataSource = new MysqlDataSource();

    dbManager(){
        dataSource.setDatabaseName("practice10");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setPort(3306);
        dataSource.setURL("jdbc:mysql://localhost:3306/practice10");
    }


}
