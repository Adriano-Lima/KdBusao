package br.com.kdbusao.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoUtil {

    private static ConexaoUtil conexaoUtil;

    public static ConexaoUtil getInstance() {
        if (conexaoUtil == null) {
            conexaoUtil = new ConexaoUtil();
        }
        return conexaoUtil;
    }

    public Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/Kdbusao", "root", "854525/*");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return c;
        }
    } 

}
