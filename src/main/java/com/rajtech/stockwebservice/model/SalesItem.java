/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rajkumar Ramasamy
 */
@Entity
@Table(name = "sales_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalesItem.findAll", query = "SELECT s FROM SalesItem s")
    , @NamedQuery(name = "SalesItem.findById", query = "SELECT s FROM SalesItem s WHERE s.id = :id")
    , @NamedQuery(name = "SalesItem.findByQty", query = "SELECT s FROM SalesItem s WHERE s.qty = :qty")
    , @NamedQuery(name = "SalesItem.findByPackageWeight", query = "SELECT s FROM SalesItem s WHERE s.packageWeight = :packageWeight")
    , @NamedQuery(name = "SalesItem.findByPackageUnit", query = "SELECT s FROM SalesItem s WHERE s.packageUnit = :packageUnit")
    , @NamedQuery(name = "SalesItem.findByPrice", query = "SELECT s FROM SalesItem s WHERE s.price = :price")
    , @NamedQuery(name = "SalesItem.findByTax", query = "SELECT s FROM SalesItem s WHERE s.tax = :tax")})
public class SalesItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "qty")
    private Integer qty;
    @Column(name = "package_weight")
    private Integer packageWeight;
    @Size(max = 15)
    @Column(name = "package_unit", length = 15)
    private String packageUnit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price", precision = 22)
    private Double price;
    @Column(name = "tax")
    private Integer tax;
    @JoinColumn(name = "products_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Products productsId;
    @JoinColumn(name = "sales_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private SalesMaster salesId;


    public SalesItem() {
    }

    public SalesItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(Integer packageWeight) {
        this.packageWeight = packageWeight;
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public void setPackageUnit(String packageUnit) {
        this.packageUnit = packageUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Products getProductsId() {
        return productsId;
    }

    public void setProductsId(Products productsId) {
        this.productsId = productsId;
    }

    public SalesMaster getSalesId() {
        return salesId;
    }

    public void setSalesId(SalesMaster salesId) {
        this.salesId = salesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalesItem)) {
            return false;
        }
        SalesItem other = (SalesItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rajtech.stockwebservice.model.SalesItem[ id=" + id + " ]";
    }
    
}
