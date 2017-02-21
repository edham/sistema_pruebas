/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author EdHam
 */
public class clsVentaN {
    private int num_ven;
    private Date fecha;
    private double sub_total;
    private double igv;
    private double total;
    private clsClienteN objClienteN;
    private List<clsVentaDetalleN> listaItems;
    

    public clsVentaN() {
        listaItems = new ArrayList<clsVentaDetalleN>();
        objClienteN = new clsClienteN();
    }

    public int getNum_ven() {
        return num_ven;
    }

    public void setNum_ven(int num_ven) {
        this.num_ven = num_ven;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public clsClienteN getObjClienteN() {
        return objClienteN;
    }

    public void setObjClienteN(clsClienteN objClienteN) {
        this.objClienteN = objClienteN;
    }

    public List<clsVentaDetalleN> getListaItems() {
        return listaItems;
    }

    public void setListaItems(List<clsVentaDetalleN> listaItems) {
        this.listaItems = listaItems;
    }

    @Override
    public String toString() {
        return "" + num_ven;
    }
    
    
}
