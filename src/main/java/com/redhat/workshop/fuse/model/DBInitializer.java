package com.redhat.workshop.fuse.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DBInitializer {

    @Value("${app.external-db}")
    Boolean externalDb;

    @Autowired
    private Environment env;



    @PostConstruct
    public void init(){
         if(externalDb){

             String dbURL = env.getProperty("spring.datasource.url");
             String user = env.getProperty("spring.datasource.username");
             String password = env.getProperty("spring.datasource.password");

             try {
                 Connection conn = DriverManager.getConnection(dbURL, user, password);
                 Statement stmt = conn.createStatement();
                 String sql = "CREATE TABLE IF NOT EXISTS orders (id INT PRIMARY KEY AUTO_INCREMENT, item VARCHAR(100), amount INTEGER, description VARCHAR(100), processed BOOLEAN);";
                 stmt.executeUpdate(sql);
                 System.out.println("Created table in given database...");
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
    }

}
