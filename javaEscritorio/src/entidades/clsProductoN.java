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
public class clsProductoN {
    private int cod_prod;
    private String nom_prod;
    private double prec_prod;
    private int Stock;

    public clsProductoN() {
    }

    public int getCod_prod() {
        return cod_prod;
    }

    public void setCod_prod(int cod_prod) {
        this.cod_prod = cod_prod;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public double getPrec_prod() {
        return prec_prod;
    }

    public void setPrec_prod(double prec_prod) {
        this.prec_prod = prec_prod;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    @Override
    public String toString() {
        return nom_prod;
    }
    
    
}
