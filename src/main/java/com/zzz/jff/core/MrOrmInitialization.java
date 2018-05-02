package com.zzz.jff.core;

import com.mysql.cj.jdbc.Driver;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @auther zhaowei.zhang
 * @since 2018/4/27 11:47
 */
public class MrOrmInitialization {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/world";

    private DataSource dataSource;

    public MrOrmInitialization() {

    }

    public MrOrmInitialization(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Driver driver = (Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "ziyoudlx");
        properties.setProperty("serverTimezone", "UTC");
        try (
                Connection connection = driver.connect(url, properties);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM city");
        ) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
