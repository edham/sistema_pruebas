/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author EdHam
 */
@Entity
@Table(name = "VENTA_DETALLE_N")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentaDetalleN.findAll", query = "SELECT v FROM VentaDetalleN v"),
    @NamedQuery(name = "VentaDetalleN.findByNumDetVen", query = "SELECT v FROM VentaDetalleN v WHERE v.numDetVen = :numDetVen"),
    @NamedQuery(name = "VentaDetalleN.findByCosto", query = "SELECT v FROM VentaDetalleN v WHERE v.costo = :costo"),
    @NamedQuery(name = "VentaDetalleN.findByCantidad", query = "SELECT v FROM VentaDetalleN v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "VentaDetalleN.findByTotal", query = "SELECT v FROM VentaDetalleN v WHERE v.total = :total")})
public class VentaDetalleN implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_DET_VEN")
    private Integer numDetVen;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COSTO")
    private BigDecimal costo;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    @Column(name = "TOTAL")
    private BigDecimal total;
    @JoinColumn(name = "COD_PROD", referencedColumnName = "COD_PROD")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductoN productoN;
    @JoinColumn(name = "NUN_VEN", referencedColumnName = "NUM_VEN")
    @ManyToOne(fetch = FetchType.LAZY)
    private VentaN ventaN;

    public VentaDetalleN() {
    }

    public VentaDetalleN(Integer numDetVen) {
        this.numDetVen = numDetVen;
    }

    public Integer getNumDetVen() {
        return numDetVen;
    }

    public void setNumDetVen(Integer numDetVen) {
        this.numDetVen = numDetVen;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ProductoN getProductoN() {
        return productoN;
    }

    public void setProductoN(ProductoN productoN) {
        this.productoN = productoN;
    }

    public VentaN getVentaN() {
        return ventaN;
    }

    public void setVentaN(VentaN ventaN) {
        this.ventaN = ventaN;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numDetVen != null ? numDetVen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentaDetalleN)) {
            return false;
        }
        VentaDetalleN other = (VentaDetalleN) object;
        if ((this.numDetVen == null && other.numDetVen != null) || (this.numDetVen != null && !this.numDetVen.equals(other.numDetVen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.VentaDetalleN[ numDetVen=" + numDetVen + " ]";
    }
    
}
