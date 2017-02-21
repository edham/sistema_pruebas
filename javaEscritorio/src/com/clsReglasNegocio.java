/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import dao.clsClienteNDAO;
import dao.clsProductoNDAO;
import dao.clsVentaNDAO;
import entidades.clsClienteN;
import entidades.clsProductoN;
import entidades.clsVentaN;
import java.util.List;

/**
 *
 * @author EdHam
 */
public class clsReglasNegocio {

//<editor-fold defaultstate="collapsed" desc="clsClienteN">
    public static List<clsClienteN> ListarCliente(String filtro) throws Exception
    {
        return clsClienteNDAO.Listar(filtro);
    }
    
    public static clsClienteN buscarCliente(int id) throws Exception
    {
        return clsClienteNDAO.buscar(id);
    }
    public  static int insertarCliente(clsClienteN entidad) throws Exception
    {
        return clsClienteNDAO.insertar(entidad);
    }
    
    public static boolean actualizarCliente(clsClienteN entidad) throws Exception
    {
        return clsClienteNDAO.actualizar(entidad);
    }    
// </editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="clsProductoN">
    public static List<clsProductoN> ListarProducto(String filtro) throws Exception
    {
        return clsProductoNDAO.Listar(filtro);
    }
    public static clsProductoN buscarProducto(int id) throws Exception
    {
        return clsProductoNDAO.buscar(id);
    }
    public  static int insertarProducto(clsProductoN entidad) throws Exception
    {
        return clsProductoNDAO.insertar(entidad);
    }
    
    public static boolean actualizarProducto(clsProductoN entidad) throws Exception
    {
        return clsProductoNDAO.actualizar(entidad);
    }    
// </editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="clsVentaNDAO">
     public static List<clsVentaN> ListarVenta(String filtro) throws Exception
    {
        return clsVentaNDAO.Listar(filtro);
    }
    public static clsVentaN buscarVenta(int id) throws Exception
    {
        return clsVentaNDAO.buscar(id);
    }
    public  static int insertarVenta(clsVentaN entidad) throws Exception
    {
        return clsVentaNDAO.insertar(entidad);
    }
    
   
// </editor-fold>
}
