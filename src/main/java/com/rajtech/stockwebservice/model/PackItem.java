/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rajtech.stockwebservice.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rajkumar Ramasamy
 */
@Entity
@Table(name = "pack_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PackItem.findAll", query = "SELECT p FROM PackItem p")
    , @NamedQuery(name = "PackItem.findById", query = "SELECT p FROM PackItem p WHERE p.id = :id")
    , @NamedQuery(name = "PackItem.findByQty", query = "SELECT p FROM PackItem p WHERE p.qty = :qty")
    , @NamedQuery(name = "PackItem.findByTax", query = "SELECT p FROM PackItem p WHERE p.tax = :tax")
    , @NamedQuery(name = "PackItem.findByPrice", query = "SELECT p FROM PackItem p WHERE p.price = :price")})
public class PackItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "qty", precision = 22)
    private Double qty;
    @Column(name = "tax", precision = 22)
    private Double tax;
    @Column(name = "price", precision = 22)
    private Double price;
    @JoinColumn(name = "pack_master_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PackMaster packMasterId;
    @JoinColumn(name = "packages_size_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PackagesSize packagesSizeId;

    public PackItem() {
    }

    public PackItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PackMaster getPackMasterId() {
        return packMasterId;
    }

    public void setPackMasterId(PackMaster packMasterId) {
        this.packMasterId = packMasterId;
    }

    public PackagesSize getPackagesSizeId() {
        return packagesSizeId;
    }

    public void setPackagesSizeId(PackagesSize packagesSizeId) {
        this.packagesSizeId = packagesSizeId;
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
        if (!(object instanceof PackItem)) {
            return false;
        }
        PackItem other = (PackItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rajtech.stockwebservice.model.PackItem[ id=" + id + " ]";
    }
    
}
