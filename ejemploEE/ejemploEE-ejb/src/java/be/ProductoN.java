/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "PRODUCTO_N")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoN.findAll", query = "SELECT p FROM ProductoN p"),
    @NamedQuery(name = "ProductoN.findByCodProd", query = "SELECT p FROM ProductoN p WHERE p.codProd = :codProd"),
    @NamedQuery(name = "ProductoN.findByNomProd", query = "SELECT p FROM ProductoN p WHERE p.nomProd = :nomProd"),
    @NamedQuery(name = "ProductoN.findByPreProd", query = "SELECT p FROM ProductoN p WHERE p.preProd = :preProd"),
    @NamedQuery(name = "ProductoN.findByStock", query = "SELECT p FROM ProductoN p WHERE p.stock = :stock")})
public class ProductoN implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_PROD")
    private Integer codProd;
    @Size(max = 50)
    @Column(name = "NOM_PROD")
    private String nomProd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRE_PROD")
    private BigDecimal preProd;
    @Column(name = "STOCK")
    private Integer stock;
    @OneToMany(mappedBy = "productoN", fetch = FetchType.LAZY)
    private List<VentaDetalleN> ventaDetalleNList;

    public ProductoN() {
    }

    public ProductoN(Integer codProd) {
        this.codProd = codProd;
    }

    public Integer getCodProd() {
        return codProd;
    }

    public void setCodProd(Integer codProd) {
        this.codProd = codProd;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public BigDecimal getPreProd() {
        return preProd;
    }

    public void setPreProd(BigDecimal preProd) {
        this.preProd = preProd;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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
        hash += (codProd != null ? codProd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoN)) {
            return false;
        }
        ProductoN other = (ProductoN) object;
        if ((this.codProd == null && other.codProd != null) || (this.codProd != null && !this.codProd.equals(other.codProd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "be.ProductoN[ codProd=" + codProd + " ]";
    }
    
}
