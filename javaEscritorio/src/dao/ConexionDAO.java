/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author EdHam
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDAO {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url = "jdbc:sqlserver://192.168.1.5\\MSSQL:1433;databaseName=prueba";
    private static String usuario = "sa";
    private static String clave = "123456";
    
    public ConexionDAO() {
    }
    public static Connection getConnection() throws Exception{
        Connection cnn = null;
        try {
             Class.forName(driver);
            cnn=DriverManager.getConnection(url,usuario,clave);
            System.out.println("conexion establecida");
        } catch (ClassNotFoundException | SQLException e) {
             System.out.println("No se pudo establecer la conexion");
        }
             return cnn;
    } 
}
