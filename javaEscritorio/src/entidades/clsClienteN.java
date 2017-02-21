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
public class clsClienteN {
    private int cod_cli;
    private String nom_cli;
    private String dir_cli;
    private String ruc_cli;

    public clsClienteN() {
    }

    public int getCod_cli() {
        return cod_cli;
    }

    public void setCod_cli(int cod_cli) {
        this.cod_cli = cod_cli;
    }

    public String getNom_cli() {
        return nom_cli;
    }

    public void setNom_cli(String nom_cli) {
        this.nom_cli = nom_cli;
    }

    public String getDir_cli() {
        return dir_cli;
    }

    public void setDir_cli(String dir_cli) {
        this.dir_cli = dir_cli;
    }

    public String getRuc_cli() {
        return ruc_cli;
    }

    public void setRuc_cli(String ruc_cli) {
        this.ruc_cli = ruc_cli;
    }

    @Override
    public String toString() {
        return nom_cli;
    }
    
    
}
