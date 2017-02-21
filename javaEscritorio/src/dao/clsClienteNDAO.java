/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.clsClienteN;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EdHam
 */
public class clsClienteNDAO {
    public static List<clsClienteN> Listar(String filto) throws Exception
    {
        List<clsClienteN> lista = new ArrayList<clsClienteN>();;
        Connection conn =null;
        CallableStatement stmt = null;
        ResultSet dr = null;
        try {
            String sql="SELECT COD_CLI,NOM_CLI,DIR_CLI,RUC_CLI FROM CLIENTE_N WHERE NOM_CLI LIKE '%"+filto+"%' OR DIR_CLI LIKE '%"+filto+"%' OR RUC_CLI LIKE '%"+filto+"%';";

            conn = ConexionDAO.getConnection();
            stmt = conn.prepareCall(sql);
            dr = stmt.executeQuery();

            while(dr.next())
            {
                
                    clsClienteN entidad = new clsClienteN();
                    entidad.setCod_cli(dr.getInt(1));
                    entidad.setNom_cli(dr.getString(2)); 
                    entidad.setDir_cli(dr.getString(3)); 
                    entidad.setRuc_cli(dr.getString(4));                    
                    lista.add(entidad);
            }

        } catch (Exception e) {
            throw new Exception("Listar "+e.getMessage(), e);
        }
        finally{
            try {
                dr.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
            }
        }
        return lista;
    }

    public static clsClienteN buscar(int id) throws Exception
    {
        clsClienteN entidad = null;
        Connection conn =null;
        CallableStatement stmt = null;
        ResultSet dr = null;
        try {
            String sql="SELECT COD_CLI,NOM_CLI,DIR_CLI,RUC_CLI FROM CLIENTE_N WHERE COD_CLI="+id+";";

            conn = ConexionDAO.getConnection();
            stmt = conn.prepareCall(sql);
            dr = stmt.executeQuery();

            if(dr.next())
            {                
                entidad = new clsClienteN();
                entidad.setCod_cli(dr.getInt(1));
                entidad.setNom_cli(dr.getString(2)); 
                entidad.setDir_cli(dr.getString(3)); 
                entidad.setRuc_cli(dr.getString(4));   
            }

        } catch (Exception e) {
            throw new Exception("Listar "+e.getMessage(), e);
        }
        finally{
            try {
                dr.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
            }
        }
        return entidad;
    }
     
    public  static int insertar(clsClienteN entidad) throws Exception
    {
        int rpta = 0;
        Connection conn =null;
        PreparedStatement stmt = null;
        try {
             
           String sql="INSERT INTO CLIENTE_N(NOM_CLI,DIR_CLI,RUC_CLI) VALUES (?,?,?);";
           
            conn = ConexionDAO.getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, entidad.getNom_cli());
            stmt.setString(2, entidad.getDir_cli());
            stmt.setString(3, entidad.getRuc_cli());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            
            if (rs.next()){
                rpta=rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            throw new Exception("Insertar"+e.getMessage(), e);
        }
        finally{
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
            }
        }
        return rpta;
    } 
    
    public static boolean actualizar(clsClienteN entidad) throws Exception
    {
        boolean rpta = false;
        Connection conn =null;
        CallableStatement stmt = null;
        try {
             String sql="UPDATE CLIENTE_N SET NOM_CLI = ?,DIR_CLI= ?,RUC_CLI=? WHERE COD_CLI = ?;";
            conn = ConexionDAO.getConnection();
            stmt = conn.prepareCall(sql);             
            stmt.setString(1, entidad.getNom_cli());
            stmt.setString(2, entidad.getDir_cli());
            stmt.setString(3, entidad.getDir_cli());
            stmt.setInt(4, entidad.getCod_cli());
                
           rpta = stmt.executeUpdate() == 1;
        } catch (Exception e) {
            throw new Exception("Error Actualizar "+e.getMessage(), e);
        }
        finally{
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
            }
        }
        return rpta;
    }
}
