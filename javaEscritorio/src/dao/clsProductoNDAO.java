/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.clsProductoN;
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
public class clsProductoNDAO {
    public static List<clsProductoN> Listar(String filto) throws Exception
    {
        List<clsProductoN> lista = new ArrayList<clsProductoN>();;
        Connection conn =null;
        CallableStatement stmt = null;
        ResultSet dr = null;
        try {
            String sql="SELECT COD_PROD,NOM_PROD,PRE_PROD,STOCK FROM PRODUCTO_N WHERE NOM_PROD LIKE '%"+filto+"%';";

            conn = ConexionDAO.getConnection();
            stmt = conn.prepareCall(sql);
            dr = stmt.executeQuery();

            while(dr.next())
            {
                
                    clsProductoN entidad = new clsProductoN();
                    entidad.setCod_prod(dr.getInt(1));
                    entidad.setNom_prod(dr.getString(2)); 
                    entidad.setPrec_prod(dr.getDouble(3)); 
                    entidad.setStock(dr.getInt(4));                    
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

    public static clsProductoN buscar(int id) throws Exception
    {
        clsProductoN entidad = null;
        Connection conn =null;
        CallableStatement stmt = null;
        ResultSet dr = null;
        try {
            String sql="SELECT COD_PROD,NOM_PROD,PRE_PROD,STOCK FROM PRODUCTO_N WHERE COD_PROD="+id+";";

            conn = ConexionDAO.getConnection();
            stmt = conn.prepareCall(sql);
            dr = stmt.executeQuery();

            if(dr.next())
            {                
                entidad = new clsProductoN();
                entidad.setCod_prod(dr.getInt(1));
                    entidad.setNom_prod(dr.getString(2)); 
                    entidad.setPrec_prod(dr.getDouble(3)); 
                    entidad.setStock(dr.getInt(4));                    
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
    
    public  static int insertar(clsProductoN entidad) throws Exception
    {
        int rpta = 0;
        Connection conn =null;
        PreparedStatement stmt = null;
        try {
            
           String sql="INSERT INTO PRODUCTO_N(NOM_PROD,PRE_PROD,STOCK) VALUES (?,?,?);";
           
            conn = ConexionDAO.getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, entidad.getNom_prod());
            stmt.setDouble(2, entidad.getPrec_prod());
            stmt.setInt(3, entidad.getStock());
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
    
    public static boolean actualizar(clsProductoN entidad) throws Exception
    {
        boolean rpta = false;
        Connection conn =null;
        CallableStatement stmt = null;
        try {
             String sql="UPDATE PRODUCTO_N SET NOM_PROD = ?,PRE_PROD= ?,STOCK=? WHERE COD_PROD = ?;";
             
            conn = ConexionDAO.getConnection();
            stmt = conn.prepareCall(sql);             
            stmt.setString(1, entidad.getNom_prod());
            stmt.setDouble(2, entidad.getPrec_prod());
            stmt.setInt(3, entidad.getStock());
            stmt.setInt(4, entidad.getCod_prod());
                
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
