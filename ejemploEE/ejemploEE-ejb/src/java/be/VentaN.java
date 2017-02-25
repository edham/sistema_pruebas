/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author EdHam
 */
@Entity
@Table(name = "VENTA_N")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaN.findAll", query = "SELECT v FROM VentaN v"),
    @NamedQuery(name = "VentaN.findByNumVen", query = "SELECT v FROM VentaN v WHERE v.numVen = :numVen"),
    @NamedQuery(name = "VentaN.findByFecha", query = "SELECT v FROM VentaN v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "VentaN.findBySubtotal", query = "SELECT v FROM VentaN v WHERE v.subtotal = :subtotal"),
    @NamedQuery(name = "VentaN.findByIgv", query = "SELECT v FROM VentaN v WHERE v.igv = :igv"),
    @NamedQuery(name = "VentaN.findByTotal", query = "SELECT v FROM VentaN v WHERE v.total = :total")})
public class VentaN implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_VEN")
    private Integer numVen;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SUBTOTAL")
    private BigDecimal subtotal;
    @Column(name = "IGV")
    private BigDecimal igv;
    @Column(name = "TOTAL")
    private BigDecimal total;
    @JoinColumn(name = "COD_CLI", referencedColumnName = "COD_CLI")
    @ManyToOne(fetch = FetchType.LAZY)
    private ClienteN clienteN;
    @OneToMany(mappedBy = "ventaN", fetch = FetchType.LAZY)
    private List<VentaDetalleN> ventaDetalleNList;

    public VentaN() {
    }

    public VentaN(Integer numVen) {
        this.numVen = numVen;
    }

    public Integer getNumVen() {
        return numVen;
    }

    public void setNumVen(Integer numVen) {
        this.numVen = numVen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getIgv() {
        return igv;
    }

    public void setIgv(BigDecimal igv) {
        this.igv = igv;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ClienteN getClienteN() {
        return clienteN;
    }

    public void setClienteN(ClienteN clienteN) {
        this.clienteN = clienteN;
    }

    @XmlTransient
    public List<VentaDetalleN> getVentaDetalleNList() {
        return ventaDetalleNList;
    }

    public void setVentaDetalleNList(List<VentaDetalleN> ventaDetalleNList) {
        this.ventaDetalleNList = ventaDetalleNList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numVen != null ? numVen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaN)) {
            return false;
        }
        VentaN other = (VentaN) object;
        if ((this.numVen == null && other.numVen != null) || (this.numVen != null && !this.numVen.equals(other.numVen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.VentaN[ numVen=" + numVen + " ]";
    }
    
}
