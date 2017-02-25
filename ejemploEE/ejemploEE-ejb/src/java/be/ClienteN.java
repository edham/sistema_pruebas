/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EdHam
 */
@Entity
@Table(name = "CLIENTE_N")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteN.findAll", query = "SELECT c FROM ClienteN c"),
    @NamedQuery(name = "ClienteN.findByCodCli", query = "SELECT c FROM ClienteN c WHERE c.codCli = :codCli"),
    @NamedQuery(name = "ClienteN.findByNomCli", query = "SELECT c FROM ClienteN c WHERE c.nomCli = :nomCli"),
    @NamedQuery(name = "ClienteN.findByDirCli", query = "SELECT c FROM ClienteN c WHERE c.dirCli = :dirCli"),
    @NamedQuery(name = "ClienteN.findByRucCli", query = "SELECT c FROM ClienteN c WHERE c.rucCli = :rucCli")})
public class ClienteN implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_CLI")
    private Integer codCli;
    @Size(max = 50)
    @Column(name = "NOM_CLI")
    private String nomCli;
    @Size(max = 50)
    @Column(name = "DIR_CLI")
    private String dirCli;
    @Size(max = 11)
    @Column(name = "RUC_CLI")
    private String rucCli;
    @OneToMany(mappedBy = "clienteN", fetch = FetchType.LAZY)
    private List<VentaN> ventaNList;

    public ClienteN() {
    }

    public ClienteN(Integer codCli) {
        this.codCli = codCli;
    }

    public Integer getCodCli() {
        return codCli;
    }

    public void setCodCli(Integer codCli) {
        this.codCli = codCli;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }

    public String getDirCli() {
        return dirCli;
    }

    public void setDirCli(String dirCli) {
        this.dirCli = dirCli;
    }

    public String getRucCli() {
        return rucCli;
    }

    public void setRucCli(String rucCli) {
        this.rucCli = rucCli;
    }

    @XmlTransient
    public List<VentaN> getVentaNList() {
        return ventaNList;
    }

    public void setVentaNList(List<VentaN> ventaNList) {
        this.ventaNList = ventaNList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCli != null ? codCli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteN)) {
            return false;
        }
        ClienteN other = (ClienteN) object;
        if ((this.codCli == null && other.codCli != null) || (this.codCli != null && !this.codCli.equals(other.codCli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.ClienteN[ codCli=" + codCli + " ]";
    }
    
}
