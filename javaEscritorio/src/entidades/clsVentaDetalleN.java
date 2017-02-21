/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author EdHam
 */
public class clsVentaDetalleN {
    private int num_det_ven;
    private double costo;
    private int cantidad;
    private double total;
    private clsProductoN objProductoN;
    private int num_ven;

    public clsVentaDetalleN() {
        objProductoN = new clsProductoN();
    }

    
    public int getNum_det_ven() {
        return num_det_ven;
    }

    public void setNum_det_ven(int num_det_ven) {
        this.num_det_ven = num_det_ven;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public clsProductoN getObjProductoN() {
        return objProductoN;
    }

    public void setObjProductoN(clsProductoN objProductoN) {
        this.objProductoN = objProductoN;
    }

    public int getNum_ven() {
        return num_ven;
    }

    public void setNum_ven(int num_ven) {
        this.num_ven = num_ven;
    }

    @Override
    public String toString() {
        return ""+total;
    }
    
    
    
}
