/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.clsClienteN;
import entidades.clsProductoN;
import entidades.clsVentaDetalleN;
import entidades.clsVentaN;
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
public class clsVentaNDAO {
     public static List<clsVentaN> Listar(String filto) throws Exception
    {
        List<clsVentaN> lista = new ArrayList<clsVentaN>();;
        Connection conn =null;
        CallableStatement stmt = null;
        ResultSet dr = null;
        try {
            String sql="SELECT CL.COD_CLI,CL.NOM_CLI,CL.DIR_CLI,CL.RUC_CLI,"
                    + "VE.NUM_VEN,VE.FECHA,VE.SUBTOTAL,VE.IGV,VE.TOTAL FROM VENTA_N AS VE "
                    + "INNER JOIN CLIENTE_N AS CL ON VE.COD_CLI=CL.COD_CLI WHERE "
                    + "CL.NOM_CLI LIKE '%"+filto+"%' OR CL.DIR_CLI LIKE '%"+filto+"%' OR CL.RUC_CLI LIKE '%"+filto+"%';";

            conn = ConexionDAO.getConnection();
            stmt = conn.prepareCall(sql);
            dr = stmt.executeQuery();

            while(dr.next())
            {
                clsClienteN objClienteN = new clsClienteN();
                objClienteN.setCod_cli(dr.getInt(1));
                objClienteN.setNom_cli(dr.getString(2)); 
                objClienteN.setDir_cli(dr.getString(3)); 
                objClienteN.setRuc_cli(dr.getString(4));   
                
                clsVentaN objVentaN = new clsVentaN();
                objVentaN.setNum_ven(dr.getInt(5));
                objVentaN.setFecha(dr.getTimestamp(6)); 
                objVentaN.setSub_total(dr.getDouble(7)); 
                objVentaN.setIgv(dr.getDouble(8));                    
                objVentaN.setTotal(dr.getDouble(9));           
                objVentaN.setObjClienteN(objClienteN);
                lista.add(objVentaN);
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

    public static clsVentaN buscar(int id) throws Exception
    {
        clsVentaN objVentaN = null;
        Connection conn =null;
        CallableStatement stmt = null;
        ResultSet dr = null;
        try {
            String sql="SELECT CL.COD_CLI,CL.NOM_CLI,CL.DIR_CLI,CL.RUC_CLI,"
                    + "VE.NUM_VEN,VE.FECHA,VE.SUBTOTAL,VE.IGV,VE.TOTAL FROM VENTA_N AS VE "
                    + "INNER JOIN CLIENTE_N AS CL ON VE.COD_CLI=CL.COD_CLI WHERE VE.NUM_VEN="+id+";";

            conn = ConexionDAO.getConnection();
            stmt = conn.prepareCall(sql);
            dr = stmt.executeQuery();

            if(dr.next())
            {                
                clsClienteN objClienteN = new clsClienteN();
                objClienteN.setCod_cli(dr.getInt(1));
                objClienteN.setNom_cli(dr.getString(2)); 
                objClienteN.setDir_cli(dr.getString(3)); 
                objClienteN.setRuc_cli(dr.getString(4));   
                
                objVentaN = new clsVentaN();
                objVentaN.setNum_ven(dr.getInt(5));
                objVentaN.setFecha(dr.getTimestamp(6)); 
                objVentaN.setSub_total(dr.getDouble(7)); 
                objVentaN.setIgv(dr.getDouble(8));                    
                objVentaN.setTotal(dr.getDouble(9));           
                objVentaN.setObjClienteN(objClienteN);
                
                sql="SELECT PR.COD_PROD,PR.NOM_PROD,PR.PRE_PROD,PR.STOCK,"
                        + "DE.NUM_DET_VEN,DE.COSTO,DE.CANTIDAD,DE.TOTAL,"
                        + "DE.NUN_VEN FROM VENTA_DETALLE_N AS DE INNER JOIN PRODUCTO_N "
                        + "PR ON DE.COD_PROD=PR.COD_PROD WHERE DE.NUN_VEN="+objVentaN.getNum_ven()+";";
                CallableStatement stmt_detalle = conn.prepareCall(sql);
                ResultSet dr_detalle = stmt_detalle.executeQuery();
                   while(dr_detalle.next())
                   {  
                        clsProductoN objProducto = new clsProductoN();
                        objProducto.setCod_prod(dr.getInt(1));
                        objProducto.setNom_prod(dr.getString(2)); 
                        objProducto.setPrec_prod(dr.getDouble(3)); 
                        objProducto.setStock(dr.getInt(4));                    

                        clsVentaDetalleN objVentaDetalleN = new clsVentaDetalleN();
                        objVentaDetalleN.setNum_det_ven(dr_detalle.getInt(5));
                        objVentaDetalleN.setCosto(dr_detalle.getDouble(6));
                        objVentaDetalleN.setCantidad(dr_detalle.getInt(7));
                        objVentaDetalleN.setTotal(dr_detalle.getDouble(8));
                        objVentaDetalleN.setNum_ven(dr_detalle.getInt(9));
                        objVentaDetalleN.setObjProductoN(objProducto);
                        
                        objVentaN.getListaItems().add(objVentaDetalleN);

                   }
                stmt_detalle.close();
                dr_detalle.close();
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
        return objVentaN;
    }
     
    public  static int insertar(clsVentaN entidad) throws Exception
    {
        int rpta = 0;
        Connection conn =null;
        PreparedStatement stmt = null;
        try {
//            NUM_VEN
           String sql="INSERT INTO VENTA_N(FECHA,SUBTOTAL,IGV,TOTAL,COD_CLI) VALUES (GETDATE(),?,?,?,?);";
           
            conn = ConexionDAO.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, entidad.getSub_total());
            stmt.setDouble(2, entidad.getIgv());
            stmt.setDouble(3, entidad.getTotal());
            stmt.setInt(4, entidad.getObjClienteN().getCod_cli());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            
            if (rs.next()){
                rpta=rs.getInt(1);
                for(clsVentaDetalleN obejto:entidad.getListaItems())
                {
                    sql="INSERT INTO VENTA_DETALLE_N(COSTO,CANTIDAD,TOTAL,COD_PROD,NUN_VEN)"
                            + " VALUES(?,?,?,?,?);";
                    PreparedStatement detalleInserta = conn.prepareStatement(sql);
                    detalleInserta.setDouble(1, obejto.getCosto());
                    detalleInserta.setInt(2,obejto.getCantidad());
                    detalleInserta.setDouble(3, obejto.getTotal());
                    detalleInserta.setInt(4, obejto.getObjProductoN().getCod_prod());
                    detalleInserta.setInt(5, rpta);
                    detalleInserta.executeUpdate();                    
                    detalleInserta.close();
                    
                     sql="UPDATE PRODUCTO_N SET STOCK=STOCK-? WHERE COD_PROD = ?;";
                    PreparedStatement detalleUpdate = conn.prepareStatement(sql);
                    detalleUpdate.setInt(1,obejto.getCantidad());
                    detalleUpdate.setInt(2,obejto.getObjProductoN().getCod_prod());
                    detalleUpdate.executeUpdate();                    
                    detalleUpdate.close();
                    
                }
            }
            rs.close();
           conn.commit();
        } catch (Exception e) {
             if (conn != null) {
                    conn.rollback();
                }
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
}
